#!/bin/bash

REPOSITORY_DIR=/home/ec2-user/app
APP_NAME=checkareer-core-spring
PID_PATH=$REPOSITORY_DIR/PID

echo "change directory"

cd $REPOSITORY_DIR/$APP_NAME/web

echo "git pull"

git pull

echo "app build"

../gradlew clean build

echo "copy executable jar file"

cp $REPOSITORY_DIR/$APP_NAME/build/libs/*.jar /var/www/checkareer-core/releases
mv /var/www/checkareer-core/releases/web-0.0.1-SNAPSHOT.jar /var/www/checkareer-core/releases/checkareer-core-spring-0.0.1-SNAPSHOT.jar

systemctl restart checkareer-core
systemctl status checkareer-core
