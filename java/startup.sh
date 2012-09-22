#!/bin/sh

CLASSPATH_DIR=$1
RUN_JAR=$2
HOST=$3
LIBS=`ls $CLASSPATH_DIR`
CLASSPATH=

for _JAR in $LIBS
do
     CLASSPATH=$CLASSPATH:$CLASSPATH_DIR/$_JAR
done

java -Dzookeeper.server=$HOST -classpath $CLASSPATH:$RUN_JAR com.renren.api.server.ThriftServer & 
