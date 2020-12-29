#!/bin/bash

echo "-------------------------------------------------------------------------------"
echo "                          [START] 400_start_back.sh                            "
echo "-------------------------------------------------------------------------------"

nohup ./mvnw spring-boot:run $@ > ./back.log.txt 2>&1 &
exit_status=1
while [ "$exit_status" != "0" ]; do
  echo "Wait server online..."
  sleep 5
  curl -X GET localhost:8080/ 2>&1 > /dev/null
  exit_status=$?
done

echo "-------------------------------------------------------------------------------"
echo "                  BACK STARTED SUCCESSFULLY ON PORT 8080                          "
echo "-------------------------------------------------------------------------------"
