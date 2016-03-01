#!/bin/bash
## Operating Systems
## Lab 4 - sed, Stream Editor

## Exercise 1
# sed -e 's/bash/sh/g' ~/Documents/passwd > ~/Documents/temp
# mv ~/Documents/temp ~/Documents/passwd

## Exercise 2
# sed -e 's/1000:1000/1000:101/g' ~/Documents/passwd

## Exercise 3
# rm ~/Documents/passwd
# cp /etc/passwd ~/Documents/
# sed -e 's/\/home/~\/Documents\/users/g' ~/Documents/passwd > ~/Documents/temp
# mv ~/Documents/temp ~/Documents/passwd
# mv ~/Documents/home ~/Documents/users

## Exercise 4
while true; do
    echo "1) Display files and Permissions only"; echo
    echo "2) Calculate number of bytes"; echo
    echo "3) Exit"; echo
    echo -n "Please enter your choice : "
    read x
    clear
    case $x in
        1) ls -l | sed -e '1d' | awk '{print $9 " " $1}';;
        2) ls -l | awk '{$byte += $5}
                        END {print "There are " $bytes " bytes here"}';;
        3) echo "Good bye my friend"; exit 0;;
        *) echo "Wrong input. Please enter a number between 1 and 3.";
            echo;;
    esac
done
