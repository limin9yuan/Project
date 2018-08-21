#!/usr/bin/env bash

home_path=/Users/wangxin/dev/projects/amy-springcloud-project/amy

sh $home_path/amy-admin-server/bin/upload_admin_server.sh

sh $home_path/amy-eureka-server/bin/upload_eureka_server.sh

sh $home_path/amy-config-server/bin/upload_config_server.sh

sh $home_path/amy-eureka-service1/bin/upload_eureka_service1.sh

sh $home_path/amy-eureka-service2/bin/upload_eureka_service2.sh

