#include <iostream>
#include <cstring>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <fcntl.h>
#include <arpa/inet.h>
#include <vector>

using namespace std;

class Server {
public:
    Server(const char* ip, int port, int numClients = 5, int timeout = 300);
    ~Server();
    void run();

private:
    int server_fd;
    int port;
    int numClients;
    int timeout;
    sockaddr_in server_addr;
    vector<int> client_fds;

    void setupServer(const char* ip, int port, int numClients, int timeout);
    void handleClient(int client_fd);
    void checkClients();
};

Server::Server(const char* ip, int port, int numClients, int timeout) {
    setupServer(ip, port, numClients, timeout);
}

Server::~Server() {
    close(server_fd);
    for (int fd : client_fds) {
        close(fd);
    }
}

void Server::setupServer(const char* ip, int port, int numClients, int timeout) {
    this->port = port;
    this->numClients = numClients;
    this->timeout = timeout;

    server_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (server_fd == -1) {
        cerr << "Failed to create socket" << endl;
        exit(EXIT_FAILURE);
    }

    fcntl(server_fd, F_SETFL, O_NONBLOCK);

    memset(&(this->server_addr), 0, sizeof(this->server_addr));
    (this->server_addr).sin_family = AF_INET;
    (this->server_addr).sin_port = htons(port);
    inet_pton(AF_INET, ip, &(this->server_addr).sin_addr);

    // to reuse the address
    int opt = 1;
    setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt));

    if (bind(server_fd, (sockaddr*)&(this->server_addr), sizeof(this->server_addr)) == -1) {
        cerr << "Failed to bind socket" << endl;
        exit(EXIT_FAILURE);
    }

    if (listen(server_fd, numClients) == -1) {
        cerr << "Failed to listen on socket" << endl;
        exit(EXIT_FAILURE);
    }

    cout << "Server setup complete, listening on port " << port << endl;
}

void Server::run() {
    while (true) {
        sockaddr_in client_addr;
        socklen_t client_addr_len = sizeof(client_addr);
        int client_fd = accept(server_fd, (sockaddr*)&client_addr, &client_addr_len);

        if (client_fd >= 0) {
            // Set the client socket to non-blocking mode
            fcntl(client_fd, F_SETFL, O_NONBLOCK);
            cout << "Accepted a connection" << endl;
            client_fds.push_back(client_fd); // Store the client file descriptor
        }

        // Check if clients are still active and handle communication
        checkClients();

        // Add a small delay to avoid busy-waiting
        usleep(10000); // 10 milliseconds
    }
}

void Server::checkClients() {
    for (int client_fd : client_fds) {
        handleClient(client_fd);
    }
}

void Server::handleClient(int client_fd) {
    const int buffer_size = 1024;
    char buffer[buffer_size];

    memset(buffer, 0, buffer_size);
    ssize_t bytes_received = recv(client_fd, buffer, buffer_size - 1, 0);

    if (bytes_received < 0) {
        if (errno != EWOULDBLOCK && errno != EAGAIN) {
            cerr << "Failed to receive data" << endl;
            close(client_fd);
            auto it = std::find(client_fds.begin(), client_fds.end(), client_fd);
            if (it != client_fds.end()) {
                client_fds.erase(it);
            }
        }
    } else if (bytes_received == 0) {
        cout << "Client disconnected" << endl;
        close(client_fd);
        auto it = std::find(client_fds.begin(), client_fds.end(), client_fd);
        if (it != client_fds.end()) {
            client_fds.erase(it);
        }
    } else {
        cout << "Received: " << buffer << endl;
        ssize_t bytes_sent = send(client_fd, buffer, bytes_received, 0);
        if (bytes_sent < 0 && errno != EWOULDBLOCK && errno != EAGAIN) {
            cerr << "Failed to send data" << endl;
            close(client_fd);
            auto it = std::find(client_fds.begin(), client_fds.end(), client_fd);
            if (it != client_fds.end()) {
                client_fds.erase(it);
            }
        }
    }
}

int main(int argc, char** argv) {
    if (argc < 3) {
        cerr << "Syntax: " << argv[0] << " <IP> <Port> <Number-of-Clients>(optional) <Timeout-Time>(optional)" << endl;
        return EXIT_FAILURE;
    }

    const char* ip = argv[1];
    int port = stoi(argv[2]);
    int numClients = (argc > 3) ? stoi(argv[3]) : 5;
    int timeout = (argc > 4) ? stoi(argv[4]) : 300;

    Server server(ip, port, numClients, timeout);
    server.run();

    return EXIT_SUCCESS;
}
