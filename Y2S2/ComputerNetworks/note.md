# Socket Programming in C

* [source](https://beej.us/guide/bgnet/html/split/index.html)

## Socket

* We can use file descriptor to communicate with another program over the internet.
* By calling socket(), returns socket descriptor. then use send(), recv()

### Stream Sockets

* Reliable, two-way connected, ordered comunication streams
* uses TCP/IP protocol

### Datagram

* Connectionless, may arrive out of order
* uses UDP/IP
* Connectionless cause we dont need to maintain an open connection, rather just build a packet and paste a header with destinaton info.
* Usage: tftp, dhcpcd

## IP

### IPV4

* 32 bits, four bytes or octets separeted by '.'
* 192.0.2.111
* loop back address: 127.0.0.1

### IPV6

* 128 bits, hexademal, two bt=yte chunk separeted by ':'
* loop back address: 0000:0000:0000:0000:0000:0000:0000:0001 or, ::1
* examples: 
	2001:0db8:c9d2:0012:0000:0000:0000:0051
	2001:db8:c9d2:12::51

	2001:0db8:ab00:0000:0000:0000:0000:0000
	2001:db8:ab00::
* IPV4 to IPV6: 192.0.2.33 -> ::ffff:192.0.2.33

## Subnets

* In IPV4, first (1 -> class A)/ (2 -> class B)/ (3 -> class C) bytes are network portion and the remaing portion is host minus couple that were resered.
* The network portion of the IP address is described by something called the netmask, which you bitwise-AND with the IP address to get the network number out of it. The netmask usually looks something like 255.255.255.0. (E.g. with that netmask, if your IP is 192.0.2.12, then your network is 192.0.2.12 AND 255.255.255.0 which gives 192.0.2.0.)
* We were running out of Classes. It was allowed for the netmask to be an arbitrary number of bits, not just 8, 16, or 24, say 255.255.255.252, which is 30 bits of network, and 2 bits of host allowing for four hosts on the network. (Note that the netmask is ALWAYS a bunch of 1-bits followed by a bunch of 0-bits.)
* Next style was, you just put a slash after the IP address, and then follow that by the number of network bits in decimal. Like this: 192.0.2.12/30. Or, for IPv6, something like this: 2001:db8::/32 or 2001:db8:5413:4028::9db9/64.

## Port Numbers

* Other than IP, TCP/UDP uses a local address for conection
* 16 bits
* in UNIX it's in etc/services file
* port under 1024 are special, requiring OS privieges to use
* HTTP -> 80, telnet -> 23, SMTP -> 25

## Byte Order

### Big-Endian/ Network Byte Order

* big end first
* Representaion of 2 byte hex: b34f -> b3 follwed by 4f

### Little-Endian

* b34f -> 4f follwed by b3


* Host Byte Order: is the order followed in host machine

### Convert Endianness

* There are two types of numbers that you can convert: short (two bytes) and long (four bytes), works for unsigned too.

* Say you want to convert a short from Host Byte Order to Network Byte Order. Start with “h” for “host”, follow it with “to”, then “n” for “network”, and “s” for “short”: h-to-n-s, or htons() (read: “Host to Network Short”).
```
Function 	Description
htons() 	host to network short
htonl() 	host to network long
ntohs() 	network to host short
ntohl() 	network to host long
```

## Structs

* a packet descriptor is of 'int' type

### addrinfo

* used to prep the socket address structures for subsequent use
* host, service  name lookups

``` c
struct addrinfo {
    int              ai_flags;     // AI_PASSIVE, AI_CANONNAME, etc.
    int              ai_family;    // AF_INET, AF_INET6, AF_UNSPEC
    int              ai_socktype;  // SOCK_STREAM, SOCK_DGRAM
    int              ai_protocol;  // use 0 for "any"
    size_t           ai_addrlen;   // size of ai_addr in bytes
    struct sockaddr *ai_addr;      // struct sockaddr_in or _in6
    char            *ai_canonname; // full canonical hostname

    struct addrinfo *ai_next;      // linked list, next node
};
``` 
* getaddrinfo() returns a pointer to new linked list.

### sockaddr

``` c
struct sockaddr {
    unsigned short    sa_family;    // address family, AF_xxx
    char              sa_data[14];  // 14 bytes of protocol address
}; 
```

* sa_data contains dest address, port no.

### sockaddr_in

* a pointer to a struct sockaddr_in can be cast to a pointer to a struct sockaddr and vice-versa

``` c
// (IPv4 only--see struct sockaddr_in6 for IPv6)
// (in -> internet)

struct sockaddr_in {
    short int          sin_family;  // Address family, AF_INET
    unsigned short int sin_port;    // Port number
    struct in_addr     sin_addr;    // Internet address
    unsigned char      sin_zero[8]; // Same size as struct sockaddr
};
```
* sin_family means sa_family = AF_INET
* sin_zero, to pad the structure, should be set to all zero using memset()
* sin_port must be in network byte order

``` c
// (IPv6 only--see struct sockaddr_in and struct in_addr for IPv4)

struct sockaddr_in6 {
    u_int16_t       sin6_family;   // address family, AF_INET6
    u_int16_t       sin6_port;     // port number, Network Byte Order
    u_int32_t       sin6_flowinfo; // IPv6 flow information
    struct in6_addr sin6_addr;     // IPv6 address
    u_int32_t       sin6_scope_id; // Scope ID
};

struct in6_addr {
    unsigned char   s6_addr[16];   // IPv6 address
};
```

### sockaddr_storage

``` c
struct sockaddr_storage {
    sa_family_t  ss_family;     // address family

    // all this is padding, implementation specific, ignore it:
    char      __ss_pad1[_SS_PAD1SIZE];
    int64_t   __ss_align;
    char      __ss_pad2[_SS_PAD2SIZE];
};
```

* designed to hold both IPV4 and IPV6, then cast it to a needed type

## IP Address, Part Deux

### inet_pton

``` c
struct sockaddr_in sa; // IPv4
struct sockaddr_in6 sa6; // IPv6

inet_pton(AF_INET, "10.12.110.57", &(sa.sin_addr)); // IPv4
inet_pton(AF_INET6, "2001:db8:63b3:1::3490", &(sa6.sin6_addr)); // IPv6
```

* converts the ip in numbers-and-dot to in_addr or in_addr6
* pton stands fpr presentable to network
* returns -1 on error

### inet_ntop

``` c
// IPv4:

char ip4[INET_ADDRSTRLEN];  // space to hold the IPv4 string
struct sockaddr_in sa;      // pretend this is loaded with something

inet_ntop(AF_INET, &(sa.sin_addr), ip4, INET_ADDRSTRLEN);

printf("The IPv4 address is: %s\n", ip4);


// IPv6:

char ip6[INET6_ADDRSTRLEN]; // space to hold the IPv6 string
struct sockaddr_in6 sa6;    // pretend this is loaded with something

inet_ntop(AF_INET6, &(sa6.sin6_addr), ip6, INET6_ADDRSTRLEN);

printf("The address is: %s\n", ip6);
```

* to work with DNS use getaddrinfo()


### Private/Disconnected Networks

* Firewall translates internal ip to external: NAT (Network Address Translation)
* 10.x.x.x is one of a few reserved networks that are only to be used either on fully disconnected networks, or on networks that are behind firewalls.Some common ones are 10.x.x.x and 192.168.x.x, where x is 0-255, generally. Less common is 172.y.x.x, where y goes between 16 and 31.

## [IPV4 to IPV6](https://beej.us/guide/bgnet/html/split/jumping-from-ipv4-to-ipv6.html)

