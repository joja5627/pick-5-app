#!/bin/bash

arr=( "$@" )


function refresh_image (){
    eval docker-compose stop "$1"
    eval docker-compose rm -f "$1"
    eval docker-compose up --build -d "$1"
}

for i in "${arr[@]}";
    do
        cd ./pick-5-backend/
        eval ./gradlew :"$i":assemble
        cd ..
        refresh_image "$i"
    done
