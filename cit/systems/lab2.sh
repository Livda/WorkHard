#!/bin/bash
## Operating Systems
## Lab 2 - Users manipulation

if [[ $EUID -ne 0 ]]; then
   echo "This script must be run as root" 1>&2
   exit 1
fi

#Exercise 1

# echo "Enter a user name"
# read name
# useradd -m $name
# echo "Enter a password for this user"
# read pass
# passwd $name $pass
# echo "Enter a new group name"
# read group
# groupadd $group
# groupmod -A $name $group

# Exercise 2
