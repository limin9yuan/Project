#!/usr/bin/env bash

ftp -n -v 192.168.1.223<<EOF
user wangxin 1qazxsw23edcvfr4
binary
hash
mdelete /app_test/amy-eureka-service1/*
rmdir /app_test/amy-eureka-service1
mkdir /app_test/amy-eureka-service1
cd /app_test/amy-eureka-service1
lcd /Users/wangxin/dev/projects/amy-springcloud-project/amy/amy-eureka-service1/target/
prompt
put amy-eureka-service1-1.0-SNAPSHOT.jar
lcd /Users/wangxin/dev/projects/amy-springcloud-project/amy/amy-eureka-service1/bin/
put start_eureka_service1.bat
bye
EOF
echo "commit to ftp successfully"
