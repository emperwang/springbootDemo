#!/bin/bash

BASE=`pwd`
# 把config配置文件设置到环境变量
JAR_NAME=${BASE}/../boot/spring-boot-assembly.jar
CONFIG_PATH=${BASE}/../config
LIB=${BASE}/../lib

# 把jar包设置到环境变量中
for i in `ls ${LIB}/*.jar`
do
        CONFIG_PATH=$CONFIG_PATH:$i
done

JAVA_ARGS="-cp "${CONFIG_PATH}
# 后续需要修改此配置文件路径设置
java ${JAVA_ARGS} -jar ${JAR_NAME} --spring.config.location=${BASE}/../config/application.yml
