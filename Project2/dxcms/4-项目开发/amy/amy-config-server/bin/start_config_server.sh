#!/usr/bin/env bash
java -jar amy-config-server-1.0-SNAPSHOT.jar --spring.profiles.active=config1 --log.app.name=config1

java -jar amy-config-server-1.0-SNAPSHOT.jar --spring.profiles.active=config2 --log.app.name=config2
