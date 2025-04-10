#include <iostream>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <cstring>

using namespace std;

int main() {
    int client_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (client_socket == -1) {
        cerr << "Error creating socket" << endl;
        return -1;
    }

    struct sockaddr_in server_address;
    server_address.sin_family = AF_INET;
    server_address.sin_addr.s_addr = inet_addr("127.0.0.1");
    server_address.sin_port = htons(8080);

    if (connect(client_socket, (struct sockaddr *)&server_address, sizeof(server_address))) {
        cerr << "Connection failed" << endl;
        return -1;
    }

    while (true) {
        char msg[1024];
        cout << "[message]: ";
        cin.getline(msg, sizeof(msg));
        
        if (send(client_socket, msg, strlen(msg), 0) == -1) {
            cerr << "Error sending data" << endl;
            return -1;
        }
    }

    close(client_socket);

    return 0;
}