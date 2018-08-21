#!/usr/bin/env bash

ftp -n -v 192.168.1.223<<EOF
user wangxin 1qazxsw23edcvfr4
binary
hash
mdelete /app_test/amy-turbine-server/*
rmdir /app_test/amy-turbine-server
mkdir /app_test/amy-turbine-server
cd /app_test/amy-turbine-server
lcd /Users/wangxin/dev/projects/amy-springcloud-project/amy/amy-turbine-server/target
prompt
put amy-turbine-server-1.0-SNAPSHOT.jar
lcd /Users/wangxin/dev/projects/amy-springcloud-project/amy/amy-turbine-server/bin
put start_turbine_server.bat
bye
EOF
echo "commit to ftp successfully"
