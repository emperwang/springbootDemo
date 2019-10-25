#!/bin/bash
BASE=$(cd $(dirname $0);pwd)
JAR_NAME=spring-boot-assembly.jar
JAR_PATH=${BASE}/../boot/${JAR_NAME}
LIB=${BASE}/../lib
AGENT_JAR=${BASE}/../MyPerf4J-ASM-2.8.0.jar
CONFIG_FILE=${BASE}/../MyPerf4J.properties
CONFIG=${BASE}/../config/
LOG=${BASE}/../logs/runtime.log
JAVA_ARG="-Xmx512m -Xms512m -server -Dloader.path=.,${LIB},${CONFIG} -javaagent:${AGENT_JAR} -DMyPerf4JPropFile=${CONFIG_FILE}"


function usage(){
        echo
        echo "usage: $0 [start | stop | restart | status]"
        echo
}

function start(){
                echo
        echo "starting app..."
                echo
        nohup java ${JAVA_ARG} -jar ${JAR_PATH} -Dspring.profile.active=pro > ${LOG} 2>&1&
}


function stop(){
        PID=`ps -ef | grep -v grep | grep "$JAR_NAME"  | awk '{print $2}'`
        if [ $PID -gt 0 ];then
                echo
                echo "stopping  app $JAR_NAME .."
                echo
                kill -9 $PID
         else
                echo
                echo "$JAR_NAME already stopped ... "
                echo
         fi
}
function status(){
         PID=`ps -ef | grep -v grep | grep "$JAR_NAME"  | awk '{print $2}'`
         if [ $PID > 0 ];then
                echo
                echo "$JAR_NAME is running ..."
                echo
         else
                echo
                echo "$JAR_NAME stopped ... "
                echo
         fi
}

function restart(){
        stop
        sleep 1
        start
}
function main(){
        if [ $# != 1 ]; then
                usage
                exit 1
        fi
        case $1 in
        'start')
                start
                ;;
        'stop')
                stop
                ;;
        'restart')
                restart
                ;;
        'status')
                status
                ;;
        *)
                usage
                ;;
        esac
}
main $1