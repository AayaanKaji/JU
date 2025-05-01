#!/bin/bash

# To use this script with telnet, execute it as follows:
# ./get-page.sh <hostname> <port> | telnet

HOSTNAME="127.0.0.1"
PORT="8002"

if [ $# -ne 2 ]; then
    echo "Usage: $0 <hostname> <port>"
else
    HOSTNAME=$1
    PORT=$2
fi

echo "open $HOSTNAME $PORT"
sleep 2
echo "GET /AayaanKaji/Q01_Intro/first.html HTTP/1.0"
echo
echo
sleep 2