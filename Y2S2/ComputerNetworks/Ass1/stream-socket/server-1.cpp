#include <iostream>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <cstring>

using namespace std;

int main() {
    int server_fd, new_socket, valread;
    struct sockaddr_in address;
    int options = 1;
    int addrlen = sizeof(address);
    const char *msg = "Hello from server.";
    char buffer[1024] = {};

    // create a socket: IPv4, TCP, default protocol
    server_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (server_fd == 0) {
        cerr << "Socket creation error" << endl;
        return -1;
    }
    
    // Forcefully attaching socket to the port 8080
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &options, sizeof(options))) {
        cerr << "Setsockopt error" << endl;
        return -1;
    }

    // binding the socket
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(8080);

    // Forcefully attaching socket to the port 8080
    if (bind(server_fd, (struct sockaddr *)&address, sizeof(address))<0) {
        cerr << "Bind failed" << endl;
        return -1;
    }

    // listen for connections
    if (listen(server_fd, 3) < 0) {
        cerr << "Listen failed" << endl;
        return -1;
    }

    // accept connections
    new_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen);
    if (new_socket < 0) {
        cerr << "Accecpt failed" << endl;
        return -1;
    }

    // handle comms
    valread = read(new_socket, buffer, 1024);
    cout << buffer << endl;
    send(new_socket, msg, strlen(msg), 0);
    
    return 0;
}