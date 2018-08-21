#!/usr/bin/env bash

ftp -n -v 192.168.1.223<<EOF
user wangxin 1qazxsw23edcvfr4
binary
hash
mdelete /app_test/amy-eureka-server/*
rmdir /app_test/amy-eureka-server
mkdir /app_test/amy-eureka-server
cd /app_test/amy-eureka-server
lcd /Users/wangxin/dev/projects/amy-springcloud-project/amy/amy-eureka-server/target/
prompt
put amy-eureka-server-1.0-SNAPSHOT.jar
lcd /Users/wangxin/dev/projects/amy-springcloud-project/amy/amy-eureka-server/bin/
put start_eureka_server.bat
bye
EOF
echo "commit to ftp successfully"
