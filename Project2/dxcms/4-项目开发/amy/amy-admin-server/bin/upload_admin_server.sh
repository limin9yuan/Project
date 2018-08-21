#!/usr/bin/env bash

ftp -n -v 192.168.1.223<<EOF
user wangxin 1qazxsw23edcvfr4
binary
hash
mdelete /app_test/amy-admin-server/*
rmdir /app_test/amy-admin-server
mkdir /app_test/amy-admin-server
cd /app_test/amy-admin-server
lcd /Users/wangxin/dev/projects/amy-springcloud-project/amy/amy-admin-server/target
prompt
put amy-admin-server-1.0-SNAPSHOT.jar
lcd /Users/wangxin/dev/projects/amy-springcloud-project/amy/amy-admin-server/bin
put start_admin_server.bat
bye
EOF
echo "commit to ftp successfully"
