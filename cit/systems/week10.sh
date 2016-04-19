#!/bin/bash

#Partitioning a device using fdisk
#List all the devices with theur device files
fdisk -l

#Manage new partitions on a new disk
fdisk /dev/sdb

#Make file system
mke2fs /dev/sdb

#Edit fstab file
in /etc/fstab

/dev/sdb /media/newDisk ext2 defaults 0 0

#Create the directory corresponding
mkdir /media/newDisk

#Mount the new partition
mount /media/newDisk
