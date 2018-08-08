#!/bin/bash
PID=$(ps -ef | grep confession-b-1.0.0.jar | grep -v grep | awk '{ print $2 }')

#PID=$(lsof -i:4202 | grep "(LISTEN)" | grep -v grep | awk '{ print $2 }')


if [ -z "$PID" ]
then
    echo Application is already stopped
else
    echo kill $PID
    kill $PID
fi
