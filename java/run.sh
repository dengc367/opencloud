#!/bin/sh


PIDS=`ps aux | grep ThriftServer | grep -v grep | awk '{ print $2 }'| xargs` 
if [ "x$PIDS" != "x" ]; then
kill -9 $PIDS
fi

sleep 60

cd /opt/opencloud-server && ./startup.sh ./lib opencloud-server.jar 10.32.16.81:7911

