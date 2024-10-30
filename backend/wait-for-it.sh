#!/usr/bin/env bash

HOST=$1
PORT=$2
shift 2

for i in `seq 1 60`;
do
    nc -z $HOST $PORT > /dev/null 2>&1
    result=$?
    if [ $result -eq 0 ]; then
        exec "$@"
    fi
    sleep 1
done
echo "Failed to connect to $HOST:$PORT"
exit 1
