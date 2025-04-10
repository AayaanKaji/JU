#include <iostream>
#include <cstring>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <fcntl.h>
#include <vector>

using namespace std;

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

    vector<int> client_sockets;

    while (true) {
        fd_set read_fds;
        FD_ZERO(&read_fds);
        FD_SET(server, &read_fds);

        int max_fd = server;

        for (int client_socket : client_sockets) {
            FD_SET(client_socket, &read_fds);
            max_fd = max(max_fd, client_socket);
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
                // set new client to non-blockig mdde
                fcntl(new_client_socket, F_SETFL, O_NONBLOCK);
                client_sockets.push_back(new_client_socket);
                cout << "New clinet connected" << endl;
            }
        }

        // check for data from existing clients
        for (auto it = client_sockets.begin(); it != client_sockets.end(); ) {
            int client_socket = *it;
            if (FD_ISSET(client_socket, &read_fds)) {
                char buffer[1024];
                ssize_t bytes_read = recv(client_socket, buffer, sizeof(buffer), 0);
                if (bytes_read == -1) {
                    cerr << "Error reading from client" << endl;
                } else if (bytes_read == 0) {
                    cout << "Client closed connection" << endl;
                    close(client_socket);
                    it = client_sockets.erase(it);
                    continue;
                } else {
                    cout << "Received data from client: " << string(buffer, bytes_read) << endl;
                }
            }
            it++;
        }
    }

    // close all client sockets
    for (int client_socket : client_sockets) {
        close(client_socket);
    }

    close(server);

    return 0;
}