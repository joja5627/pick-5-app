#!/bin/bash

filename="./pick-5-backend/settings.gradle"

arr=($(grep "app"  $filename | awk '{print $2}'))
for i in ${arr[@]} ;
    do
        "$i" | sed 's/://' | sed "s/'//" | sed "s/'//"
    done
