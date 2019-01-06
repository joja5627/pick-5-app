#!/bin/bash

arr=( "$@" )

cd ./pick-5-backend/

for i in "${arr[@]}";
    do 
        eval ./gradlew :"$i":assemble 
    done

cd ../docker-compose/

for i in "${arr[@]}";
    do 
        eval docker-compose stop "$i"
        eval docker-compose rm -f "$i"
        eval docker-compose up --build -d "$i"
    done


