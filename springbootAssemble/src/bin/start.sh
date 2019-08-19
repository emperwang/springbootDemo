#!/bin/bash
BASE=`pwd`
JAR_NAME=${BASE}/../boot/fcaps-controller.jar
MPATH=${BASE}/../config
LIB=${BASE}/../lib
# 设置配置文件
CONFIG_FILE=${BASE}/../config/CollectorResourceConfig.properties
# application.yml  配置文件外置
APP_FILE=${BASE}/../config/
LOG=logs/runtime.log
for i in `ls ${LIB}`
do
        MPATH=${MPATH}:$i
done
JAVA_ARG="-Xmx512m -Xms512m -server -Dconfig.path=${CONFIG_FILE} -cp "${MPATH}

nohup java ${JAVA_ARG} -jar ${JAR_NAME} -Dspring.profile.active=pro --spring.config.location=${APP_FILE} > ${LOG} 2>&1 &
