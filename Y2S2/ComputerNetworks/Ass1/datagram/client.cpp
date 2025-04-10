#include <iostream>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <cstring>

using namespace std;

int main() {
    int client_socket = socket(AF_INET, SOCK_DGRAM, 0);
    if (client_socket == -1) {
        cerr << "Error creating socket" << endl;
        return -1;
    }

    struct sockaddr_in server_address;
    server_address.sin_family = AF_INET;
    server_address.sin_addr.s_addr = inet_addr("127.0.0.1");
    server_address.sin_port = htons(8080);

    char client_name[1024];
    client_name[0] = 'A';
    client_name[1] = '\0';
    // cout << "Enter your name: ";
    // cin.getline(client_name, sizeof(client_name));
    
    if (sendto(client_socket, client_name, strlen(client_name), 0, (struct sockaddr *)&server_address, sizeof(server_address)) == -1) {
        cerr << "Error sending data" << endl;
        close(client_socket);
        return -1;
    }

    while (true) {
        char msg[1024];
        cout << "[message]: ";
        cin.getline(msg, sizeof(msg));

        if (!strcmp(msg, "/exit")) {
            break;
        }
        
        if (sendto(client_socket, msg, strlen(msg), 0, (struct sockaddr *)&server_address, sizeof(server_address)) == -1) {
            cerr << "Error sending data" << endl;
            break;
        }
    }

    close(client_socket);
    return 0;
}
