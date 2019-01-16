#!/bin/bash

filename="./pick-5-backend/settings.gradle"

eval docker-compose stop 
eval docker-compose up -d mongo 

arr=($(grep 'app'  $filename | awk '{print $2}'))

for i in ${arr[@]} ;
    do
       app=$(echo "$i" | sed 's/://' | sed "s/'//" | sed "s/'//")
       echo $app
       eval ./refresh-app.sh $app
    done


