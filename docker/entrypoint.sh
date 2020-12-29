#!/bin/sh

nohup java $JAVA_OPTS -jar clicker.jar $BOOT_OPTS $@ > ./back.log 2>&1