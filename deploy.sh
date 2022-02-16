#!/bin/bash

REPOSITORY_DIR=/home/ec2-user/app
APP_NAME=checkareer-core-spring
PID_PATH=$REPOSITORY_DIR/PID

echo "copy executable jar file"

cp $REPOSITORY_DIR/$APP_NAME/build/libs/*.jar $REPOSITORY_DIR

echo "check current pid"

if [ -f $PID_PATH ]; then
	PID=$(cat $PID_PATH)
	echo "stop current pid $PID"
	kill -15 $PID
	sleep 10
fi

echo "new application deploy"

echo $(ls -tr $REPOSITORY_DIR | grep .jar | tail -n 1)

JAR_NAME=$(ls -tr $REPOSITORY_DIR/ | grep .jar | tail -n 1)

echo "jar name : $REPOSITORY_DIR/$JAR_NAME"

nohup java -jar \
   $REPOSITORY_DIR/$JAR_NAME 2>&1 & echo $! > $PID_PATH