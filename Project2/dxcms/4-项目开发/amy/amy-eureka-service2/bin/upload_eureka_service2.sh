#!/usr/bin/env bash

ftp -n -v 192.168.1.223<<EOF
user wangxin 1qazxsw23edcvfr4
binary
hash
mdelete /app_test/amy-eureka-service2/*
rmdir /app_test/amy-eureka-service2
mkdir /app_test/amy-eureka-service2
cd /app_test/amy-eureka-service2
lcd /Users/wangxin/dev/projects/amy-springcloud-project/amy/amy-eureka-service2/target/
prompt
put amy-eureka-service2-1.0-SNAPSHOT.jar
lcd /Users/wangxin/dev/projects/amy-springcloud-project/amy/amy-eureka-service2/bin/
put start_eureka_service2.bat
bye
EOF
echo "commit to ftp successfully"
