##Operating Systems
##Lab 3 - Introduction du awk

#Exercise 1
#awk '{print $1 " " $2}'

#Exercise 2
#cat /etc/passwd | awk -F ":" '{print $1 " " $6}'

#Exercise 3
#ls -l | awk '{print $9 " " $1}'

#Exercise 4
#ls -l /bin | awk '{print $9 " " $3}'

#Exercise 5
#ls -ls | awk '{blocs += $1; bytes += $6; print "Blocs " blocs " Bytes " bytes}'

#Exercise 6
# ls -ls | awk '
# BEGIN {print "Bytes and Blocs"}
# {blocs += $1;
# bytes += $6;}
# END {print "Total : " bytes " bytes & " blocs " blocs"}'

#Exercise 7
# cat /etc/passwd | grep home | awk -F ":" '
# BEGIN {
#     print "Who is here ?";
#     print "-------------"}
# {print $1 " " $3}
# END {print "Thank you and Goodbye"}'

#Exercise 8
# echo "Enter a Directory"
# read x
# ls -ls $x | awk '
# {blocs += $1; bytes += $6}
# END {frag = ((bytes/4000)/blocs)*100 ;
#     print "Internal Fragmentation " frag "%"}'
