#!/bin/sh

## please don't check the directory of the script

# the project base
PROJECT_BASE=`pwd`
PROJECT_SERVER_BASE=$PROJECT_BASE/opencloud-server
PROJECT_CLIENT_BASE=$PROJECT_BASE/opencloud-client
PROJECT_ZOOKEEPER_BASE=$PROJECT_BASE/opencloud-zookeeper
PROJECT_THRIFT_BASE=$PROJECT_BASE/opencloud-thrift

echo "compile the project opencloud"
(cd $PROJECT_THRIFT_BASE; mvn install) && (cd $PROJECT_ZOOKEEPER_BASE; mvn install) && mvn -U clean package
#mvn install:install-file -DgroupId=com.renren.open -DartifactId=opencloud-thrift -Dversion=1.0-SNAPSHOT -Dpackaging=jar -Dfile=opencloud-thrift/target/opencloud-thrift-1.0-SNAPSHOT.jar
#mvn install:install-file -DgroupId=com.renren.open -DartifactId=opencloud-zookeeper -Dversion=1.0-SNAPSHOT -Dpackaging=jar -Dfile=opencloud-zookeeper/target/opencloud-zookeeper-1.0-SNAPSHOT.jar
#mvn -U clean package


SERVER_USER_NAME=root
SERVER_ADDRESS='10.32.16.81'
SERVER_LISTERNING_PORT=7911
SERVER_BIN_DIR=/opt/opencloud-server

SERVER_HOST=$SERVER_USER_NAME@$SERVER_ADDRESS
SERVER_HOST_PORT=$SERVER_ADDRESS:$SERVER_LISTERNING_PORT
SERVER_HOST_BIN_DIR=$SERVER_HOST:$SERVER_BIN_DIR

SERVER_SSH="ssh -q $SERVER_HOST"
if [ `$SERVER_SSH test -d $SERVER_BIN_DIR; echo $?` -eq 0 ]; then
    $SERVER_SSH mv -bf $SERVER_BIN_DIR ${SERVER_BIN_DIR}_backup.`date +%Y%m%d%M%S`    
fi
$SERVER_SSH mkdir -p $SERVER_BIN_DIR

PROJECT_SERVER_TARGET_DIR=$PROJECT_SERVER_BASE/target
scp -r $PROJECT_SERVER_TARGET_DIR/lib $SERVER_HOST_BIN_DIR
scp $PROJECT_SERVER_TARGET_DIR/opencloud-server.jar $SERVER_HOST_BIN_DIR
scp startup.sh $SERVER_HOST_BIN_DIR 
cat <<-EOF > ./run.sh
	#!/bin/sh

	
	PIDS=\`ps aux | grep ThriftServer | grep -v grep | awk '{ print \$2 }'| xargs\` 
	if [ "x\$PIDS" != "x" ]; then
		kill -9 \$PIDS
	fi
	
	sleep 60

	cd $SERVER_BIN_DIR && ./startup.sh ./lib opencloud-server.jar $SERVER_HOST_PORT

EOF
chmod +x run.sh
scp run.sh $SERVER_HOST_BIN_DIR
$SERVER_SSH sh $SERVER_BIN_DIR/run.sh


