#include <iostream>
#include <cstring>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <fcntl.h>
#include <vector>

using namespace std;

struct ClientInfo {
    int socket;
    char name[1024];
};

int main() {
    int server = socket(AF_INET, SOCK_STREAM, 0);

    // set server to non-blocking mode
    fcntl(server, F_SETFL, O_NONBLOCK);

    struct sockaddr_in server_address;
    server_address.sin_family = AF_INET;
    server_address.sin_addr.s_addr = INADDR_ANY;
    server_address.sin_port = htons(8080);

    if (bind(server, (struct sockaddr *)&server_address, sizeof(server_address)) == -1) {
        cerr << "Binding failed" << endl;
        return -1;
    }

    if (listen(server, 5) == -1) {
        cerr << "Listen failed" << endl;
        return -1;
    }

    vector<ClientInfo> clients;

    while (true) {
        fd_set read_fds;
        FD_ZERO(&read_fds);
        FD_SET(server, &read_fds);

        int max_fd = server;

        for (const auto& client : clients) {
            FD_SET(client.socket, &read_fds);
            max_fd = max(max_fd, client.socket);
        }

        // perform non-blocking polling
        if (select(max_fd+1, &read_fds, nullptr, nullptr, nullptr) == -1) {
            cerr << "Error in polling" << endl;
            break;
        }

        // check for new connection
        if (FD_ISSET(server, &read_fds)) {
            int new_client_socket = accept(server, nullptr, nullptr);
            if (new_client_socket != -1) {
                // set new client to non-blockig mode
                fcntl(new_client_socket, F_SETFL, O_NONBLOCK);
                clients.push_back({new_client_socket, {}});
            }
        }

        // check for data from existing clients
        for (auto it = clients.begin(); it != clients.end(); ) {
            auto& client = *it;
            if (FD_ISSET(client.socket, &read_fds)) {
                // first time passing name
                if (strlen(client.name) == 0) {
                    char client_name[1024];
                    ssize_t bytes_read = recv(client.socket, client_name, sizeof(client_name), 0);
                    if (bytes_read <= 0) {
                        cerr << "Error reading from new client" << endl;
                        close(client.socket);
                    } else {
                        strncpy(client.name, client_name, sizeof(client.name) - 1);
                        client.name[sizeof(client.name)] = '\0';
                        cout << client_name << " connected" << endl;
                    }
                }
                else {
                    char buffer[1024];
                    ssize_t bytes_read = recv(client.socket, buffer, sizeof(buffer), 0);
                    if (bytes_read == -1) {
                        cerr << "Error reading from client" << endl;
                    } else if (bytes_read == 0) {
                        cout << client.name << " closed connection" << endl;
                        close(client.socket);
                        it = clients.erase(it);
                        continue;
                    } else {
                        cout << "[" << client.name << "]: " << string(buffer, bytes_read) << endl;
                    }
                }
            }
            it++;
        }
    }

    // close all client sockets
    for (const auto& client : clients) {
        close(client.socket);
    }

    close(server);

    return 0;
}