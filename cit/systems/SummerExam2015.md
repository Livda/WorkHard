%Summer exam 2015
%[Aurélien Fontaine](mailto:aurelien.fontaine@mycit.ie), DCOM2
%20 april 2016

#Question 1
##a) In the context of deadlock, explain the terms safe state and unsafe state. (_2 marks_)

__Safe :__ at least one sequence where there is no deadlock

__Unsafe :__ possibilty of deadlock

##b) Calculate the current available ressouces (_2 marks_)

| __R1__ | __R2__ | __R3__ |
|:------:|:------:|:------:|
|    8   |    7   |    9   |

:Total ressource vector

|        | __R1__ | __R2__ | __R3__ |
|:------:|:------:|:------:|:------:|
| __P1__ |    2   |    0   |    3   |
| __P2__ |    1   |    3   |    0   |
| __P3__ |    1   |    1   |    2   |
| __P4__ |    2   |    2   |    2   |

:Current ressource allocation

Available = (2 1 2)

\newpage
##c) Is the system currently in a safe state ? (_6 marks_)

|        | __R1__ | __R2__ | __R3__ |
|:------:|:------:|:------:|:------:|
| __P1__ |    4   |    6   |    6   |
| __P2__ |    3   |    4   |    4   |
| __P3__ |    3   |    2   |    5   |
| __P4__ |    2   |    6   |    4   |

:Total ressources claims

P3 -> Available = (3 2 4)

P2 -> Available = (4 5 6)

P4 -> Available = (6 7 6)

P1 -> Available = (8 7 9)

Done.

The system is in a __safe__ state.

##d) Draw a timeline to show the execution of each of there processus fot the _Shortest Reaming Time_ scheduling algorithm. Calculate the turnaround and normalised turnaround times. (_8 marks_)

| __Process Time__ | __Arrival Time__ | __Service Time__|
|:----------------:|:----------------:|:---------------:|
|        A         |        0         |        2        |
|        B         |        2         |        7        |
|        C         |        4         |        4        |
|        D         |        6         |        1        |

```
        0    5    10   15
        |----|----|----|-->

A        --                 0 -> 2
B          -------          2 -> 9
C                  ----     10 -> 14
D                 -         9 -> 10
```

|              |  A  |  B  |  C  |  D  | Total |
| :----------: | :-: | :-: | :-: | :-: | :---: |
| Service time |  2  |  7  |  4  |  1  |  14   |
| Waiting time |  0  |  0  |  6  |  3  |   9   |

: Service and waiting time for all processus

_Turnaround time :_ Service time + Waiting time = $14 + 9 = 23$

_Normalised turnaround time :_ Turnaround time / Service time = $23 / 14 = 1.64$


##e) How does the Linux kill command work? (__3 marks__)
The command `kill` send a signal to the application / processus. With the option
`-9` it kill the processus, with the `-6`, it suspend the processus.

To use it, you have to call it like that : `kill [options] PID`. The `PID` is the
PID of the processus targeted.

##f) Apart from kill, name and briefly describe two Linux commands for managing processes. (__4 marks__)
There is number of commands :

- `ps` which show a picture of the current processus running at the call of the
command,
- `pstree` which shows running processes as a tree,
- `top` or `htop` which show dynamictly all the processus running on the
computer,
- `fg` or `bg` with `jobs` which put a processus on background running or to retake it in
frontground,
- etc.

#Question 2
##a) Name four primary assets of a computer system that require protection. (__4 marks__)

##b) Name and briefly describe four phases of a typical virus. (__8 marks__)

##c) What is the main difference in the way Linux and Windows support virtual memory? (__2 marks__)

##d) With the aid of a diagram, describe how a Translation Lookaside Buffer works. (__11 marks__)

#Question 3
##a) Describe the factors that need to be considered when calculating the access time of a disk scheduling algorithm. (__6 marks__)

##b) For the following track request queue, describe the sequence of tracks visited for each of the following disk scheduling algorithms. Assume that the head starts at track 151 and is moving toward track 199. Also assume that the drive has 200 tracks numbered 0-199. (__6 marks__)

_Request queue: 67, 146, 55, 161, 88, 91, 121, 181, 133_
###i) Look
###ii)Circular cicle

##c) With reference to RAID, what is meant by the terms redundancy, data striping and mirroring? (__6 marks__)

##d) (d) Outline the main features of RAID 2. How does RAID 2 check for errors? (__4 marks__)

##e) Name three items from a Linux passwd file record. (__3 marks__)

#Question 4
##a) Outline two methods where multiple operating systems can be made available on a single computer. What is the first Linux process to run? (__3 marks__)

##b) What is a Linux daemon? With reference to run-level directories, describe how daemons usually get started. (__6 marks__)

A Linux daemon is a service running in the backgroud. These can vary depending
on the particular distribution and the run-level. The latter refers to a set of
services that start-up at boot time.

##c) With the aid of a diagram, describe how the NFS service works. (_10 marks__)

##d) Give three reasons why concurrency cannot be avoided in a modern operating system. (__6 marks__)

#Question 5
##a) Define mutual exclusion and state the conditions for mutual exclusion to occur. (__12 marks__)

##b) The following pseudo-code is an attempt to satisfy mutual exclusion. Why does this attempt fail? (__4 marks__)

~~~~ {#mycode .c .numberLines}
/* PROCESS 0 */
while (turn != 0) /* do nothing */;
/* critical section*/;
turn = 1;


/* PROCESS 1 */
while (turn != 1) /* do nothing */;
/* critical section*/;
turn = 0;
~~~~

##c) What is the purpose of a semaphore?  Name the two types of semaphore. (__4 marks__)

##d) Name the three operations for accessing a semaphore. Outline how one of these can be implemented using pseudo-code. (__5 marks__)
