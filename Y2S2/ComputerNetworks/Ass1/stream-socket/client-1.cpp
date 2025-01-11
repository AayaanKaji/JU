#include <iostream>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <cstring>

using namespace std;

int main() {
    // create a socket: IPv4, TCP, default protocol
    int sock = socket(AF_INET, SOCK_STREAM, 0);

    // server address
    struct sockaddr_in serv_addr;
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(8080);
        // convert address from text to binary
    inet_pton(AF_INET, "127.0.0.1", &serv_addr.sin_addr);

    // establish connection
    connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr));

    // send
    const char *msg = "Hello from client";
    send(sock, msg, strlen(msg), 0);

    // receive
    char buffer[1024] = {};
    read(sock, buffer, 1024);
    cout << buffer << endl;

    close(sock);

    return 0;
}