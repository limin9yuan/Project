#!/usr/bin/env bash

ftp -n -v 192.168.1.223<<EOF
user wangxin 1qazxsw23edcvfr4
binary
hash
mdelete /app_test/amy-config-server/*
rmdir /app_test/amy-config-server
mkdir /app_test/amy-config-server
cd /app_test/amy-config-server
lcd /Users/wangxin/dev/projects/amy-springcloud-project/amy/amy-config-server/target
prompt
put amy-config-server-1.0-SNAPSHOT.jar
lcd /Users/wangxin/dev/projects/amy-springcloud-project/amy/amy-config-server/bin
put start_config_server.bat
bye
EOF
echo "commit to ftp successfully"
