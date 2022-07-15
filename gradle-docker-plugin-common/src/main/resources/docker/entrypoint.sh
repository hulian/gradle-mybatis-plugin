#!/bin/bash
#默认JVM内存
JVM_OPTS="-Xmx512m"
if [ -z "$JVM_OPTS" ];then
    JVM_OPTS="$JVM_OPTS"
fi
echo "命令行参数:$*"
echo "环境变量 JVM_OPTS:$JVM_OPTS"
# shellcheck disable=SC2068
java "$JVM_OPTS" -jar app.jar $@
