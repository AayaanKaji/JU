# Linux Commnads

* -type=[soa, txt, any]

## Ifconfig

* <specific_network_interface_name>: like eth0, eno1, eno2 Displays details about it
* -a flag: info about all in/active interfaces
* eth0 down or, ifdown eth0: to deactivate that interface
* eth0 up or, ifup eth0: to activate
* <interface> <ip_address> : to set the ip address
* <interface> netmask <ip_address> : to set the network mask

## traceroute:

* prints the route a package takes to reach host
* <ip_address> <DNS>
* -g gate Option: Route the packet through the gate
* $ traceroute -g 192.168.43.45 google.com
* -4 flag: for ipv4
* -6: for ipv6
* -p port Option: Set the destination port to use. Default is 33434.
* $traceroute  -p 20292 google.com

## ping

* -i : modify packet interval
  * ping -i <time_length> <ip_address>
* -f : flood with ping
* -s <size> : ping size
* -w <time_length> : time limit to send a ping
* -q : quite result 

## dig

* used to gather DNS information
* It stands for Domain Information Groper
* syntax: dig [server] [name] [type]
  - [server] – The hostname or IP address the query is directed to
  - [name] – The DNS (Domain Name Server) of the server to query
  - [type] – The type of DNS record to retrieve. By default (or if left blank), dig uses the A record type
  - Common DNS record types:
    ```
      A – Address record which directly maps a hostname to an IP address
      MX – Mail Exchange which maps message transfer agents for the domain
      SIG – Signature record which is used in encryption protocols
    ```

## telnet

* short for teletype net
* connects to remote m/c using poort 23
* doesn't use encryption unlike ssh
* to use telnet
  * allow it to access port 23 through firewall
    ```bash
      sudo ufw allow 23/tcp
    ```
* The syntax for the telnet command is:
```
telnet [options] [remote_server_address] [port]
```

## nslookup

* to query internet doamin name servers
* interactive + noninteractive mode
* default port 53

## netstat

* for network statics
* displays which ports are open or hav eestablished connections
* 



  
