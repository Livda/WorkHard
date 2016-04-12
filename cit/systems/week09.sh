#!/bin/bash

#Exercise 1
#echo -n "Enter file name "
#read fileName
#if test -d $fileName
#then
#	echo "is directory"
#fi
#if test -f $fileName
#then
#	echo "is regular file"
#fi

#Exercise 2
#echo -n "Read directory "
#read dir
#cd $dir
#dirNumber=0
#fileNumber=0
#ls > tmp
#while read file
#do
#	echo $file
#	if test -d $file
#	then
#		((dirNumber++))
#	fi
#	if test -f $file
#	then
#		((fileNumber++))
#	fi
#done < tmp
#rm tmp
#((fileNumber--))
#echo ""
#echo "Number of directories $dirNumber"
#echo "Number of files $fileNumber"

#Exercise 3
#for i in {1..12}
#do
#	echo "Times table of $i"
#	for j in {1..10}
#	do
#		((num = $j * $i))
#		echo "$i * $j = $num"
#	done
#	echo
#done

#Exercise 4
#for i in {1..4}
#do
#	echo "Insert username $i"
#	read username
#	useradd $username
#done

#Exercise 5
#at 13:00
#/media/backup
#rcat start
