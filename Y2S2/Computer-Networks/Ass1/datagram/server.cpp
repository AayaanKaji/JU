#include <iostream>
#include <cstring>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <fcntl.h>
#include <vector>

using namespace std;

struct ClientInfo {
    sockaddr_in address;
    char name[1024];
};

int main() {
    int server = socket(AF_INET, SOCK_DGRAM, 0);
    if (server < 0) {
        cerr << "Error creating socket" << endl;
        return -1;
    }

    fcntl(server, F_SETFL, O_NONBLOCK);

    struct sockaddr_in server_address;
    server_address.sin_family = AF_INET;
    server_address.sin_addr.s_addr = INADDR_ANY;
    server_address.sin_port = htons(8080);

    if (bind(server, (struct sockaddr *)&server_address, sizeof(server_address)) == -1) {
        cerr << "Binding failed" << endl;
        return -1;
    }

    vector<ClientInfo> clients;
    char buffer[1024];

    while (true) {
        fd_set read_fds;
        FD_ZERO(&read_fds);
        FD_SET(server, &read_fds);

        struct timeval timeout = {1, 0}; // 1 second timeout
        int activity = select(server + 1, &read_fds, nullptr, nullptr, &timeout);

        if (activity == -1) {
            cerr << "Error in polling" << endl;
            break;
        }

        if (FD_ISSET(server, &read_fds)) {
            sockaddr_in client_address;
            socklen_t client_address_len = sizeof(client_address);
            ssize_t bytes_received = recvfrom(server, buffer, sizeof(buffer), 0, (struct sockaddr *)&client_address, &client_address_len);
            if (bytes_received > 0) {
                buffer[bytes_received] = '\0';
                
                // Check if client is already known
                bool known_client = false;
                for (auto& client : clients) {
                    if (client.address.sin_addr.s_addr == client_address.sin_addr.s_addr && client.address.sin_port == client_address.sin_port) {
                        known_client = true;
                        cout << "[" << "$" << "]: " << buffer << endl;
                        break;
                    }
                }

                if (!known_client) {
                    ClientInfo new_client;
                    new_client.address = client_address;
                    strncpy(new_client.name, buffer, sizeof(new_client.name) - 1);
                    new_client.name[sizeof(new_client.name) - 1] = '\0';
                    clients.push_back(new_client);
                    // cout << new_client.name << " connected" << endl;
                }
            }
        }
    }

    close(server);
    return 0;
}
