#!/bin/bash

filename="./pick-5-backend/settings.gradle"

eval docker-compose stop
eval docker-compose up -d --build --force-recreate mongo 
eval docker-compose up -d --build --force-recreate pick-5-frontend
cd ./pick-5-backend
eval ./gradlew assemble
cd ..
eval docker-compose up -d --build --force-recreate
# eval ./pick-5-backend
# arr=($(grep 'app'  $filename | awk '{print $2}'))

# for i in ${arr[@]} ;
#     do
#        app=$(echo "$i" | sed 's/://' | sed "s/'//" | sed "s/'//")
#        echo $app
#        eval ./refresh-app.sh $app
#     done

# eval docker-compose up -d --build --force-recreate nginx 


