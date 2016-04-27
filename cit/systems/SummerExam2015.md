%Summer exam 2015
%[Aurélien Fontaine](mailto:aurelien.fontaine@mycit.ie), DCOM2
%20 April 2016

#Question 1
##a) In the context of deadlock, explain the terms safe state and unsafe state. (_2 marks_)

__Safe :__ at least one sequence where there is no deadlock

__Unsafe :__ possibility of deadlock

##b) Calculate the current available resources (_2 marks_)

| __R1__ | __R2__ | __R3__ |
|:------:|:------:|:------:|
|    8   |    7   |    9   |

:Total resource vector

|        | __R1__ | __R2__ | __R3__ |
|:------:|:------:|:------:|:------:|
| __P1__ |    2   |    0   |    3   |
| __P2__ |    1   |    3   |    0   |
| __P3__ |    1   |    1   |    2   |
| __P4__ |    2   |    2   |    1   |

:Current resource allocation

Available = (2 1 3)

\newpage
##c) Is the system currently in a safe state ? (_6 marks_)

|        | __R1__ | __R2__ | __R3__ |
|:------:|:------:|:------:|:------:|
| __P1__ |    4   |    6   |    6   |
| __P2__ |    3   |    4   |    4   |
| __P3__ |    3   |    2   |    5   |
| __P4__ |    2   |    6   |    4   |

:Total resources claims

P3 -> Available = (3 2 5)

P2 -> Available = (4 5 5)

P4 -> Available = (6 7 6)

P1 -> Available = (8 7 9)

Done.

The system is in a __safe__ state.

##d) Draw a time-line to show the execution of each of there processes for the _Shortest Remaining Time_ scheduling algorithm. Calculate the turnaround and normalised turnaround times. (_8 marks_)

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
B          --     -----     2 -> 4 / 9 -> 14
C            -- --          4 -> 6 / 7 -> 9
D              -            6 -> 7
```

|              |  A  |  B  |  C  |  D  | Total |
| :----------: | :-: | :-: | :-: | :-: | :---: |
| Service time |  2  |  7  |  4  |  1  |  14   |
| Waiting time |  0  |  5  |  1  |  0  |   6   |

: Service and waiting time for all processes

###Total
_Turnaround time :_ Service time + Waiting time = $14 + 6 = 20$

_Normalised turnaround time :_ Turnaround time / Service time = $20 / 14 = 1.42$

###Task A
_Turnaround time :_ $2 + 0 = 2$

_Normalised turnaround time :_ $2 / 2 = 1$

###Task B
_Turnaround time :_ $7 + 5 = 15$

_Normalised turnaround time :_ $15 / 7 = 2.14$

###Task C
_Turnaround time :_ $4 + 1 = 5$

_Normalised turnaround time :_ $5 / 4 = 1.25$


###Task D
_Turnaround time :_ $1 + 0 = 1$

_Normalised turnaround time :_ $1 / 1 = 1$

##e) How does the Linux kill command work? (_3 marks_)
The command `kill` send a signal to the application / processes. With the option
`-9` it kill the processes, with the `-6`, it suspend the processes.

To use it, you have to call it like that : `kill [options] PID`. The `PID` is the
PID of the processes targeted.

##f) Apart from kill, name and briefly describe two Linux commands for managing processes. (_4 marks_)
There is number of commands :

- `ps` which show a picture of the current processes running at the call of the
command,
- `pstree` which shows running processes as a tree,
- `top` or `htop` which show dynamicly all the processes running on the
computer,
- `fg` or `bg` with `jobs` which put a processes on background running or to
retake it in front-ground,
- etc.

#Question 2
##a) Name four primary assets of a computer system that require protection. (_4 marks_)
Hardware, software, data and communication lines.

##b) Name and briefly describe four phases of a typical virus. (_8 marks_)

1) __Dormant__ - Virus is idle and waiting for a trigger.
2) __Propagation__ - Copies itself to other programs.
3) __Triggering__ - An event activates the virus.
4) __Execution__ - Virus function is performed.

##c) What is the main difference in the way Linux and Windows support virtual memory? (_2 marks_)
Linux use a swap partition. Windows uses virtual memory manager and page files.

##d) With the aid of a diagram, describe how a Translation Lookaside Buffer works. (_11 marks_)
![Translation Lookaside Buffer](http://pds18.egloos.com/pds/201008/25/54/b0104454_4c74d19e68d6a.jpg)

Contains recent page table entries. Given the address examines TLB.
If present, read address is calculated.
Else check page table.
    If present, calculate address.
    Else, page fault. Load page.
Update page table and buffer.

#Question 3
##a) Describe the factors that need to be considered when calculating the access time of a disk scheduling algorithm. (_6 marks_)
The access time is the sum of the __seek time__(time it take to mode the head),
the __latency time__ (time for disk to spin) and the __transfer time__(electronic
transfer of data to / from memory). The only way to reduce access time is to
reduce the seek time because the other ones are fixed.

The algorithm are more or less efficient depending on the distribution of the
resource required, but generally the FIFO scheduling algorithm is less efficient
than the C-Look scheduling algorithm.

##b) For the following track request queue, describe the sequence of tracks visited for each of the following disk scheduling algorithms. Assume that the head starts at track 151 and is moving toward track 199. Also assume that the drive has 200 tracks numbered 0-199. (_6 marks_)

_Request queue: 67, 146, 55, 161, 88, 91, 121, 181, 133_

###i) Look
151, 161, 181, 146, 133, 121, 91, 88, 67, 55

###ii)Circular circle
151, 161, 181, 199, 0, 55, 67, 88, 91, 121, 133, 146

##c) With reference to RAID, what is meant by the terms redundancy, data striping and mirroring? (_6 marks_)
__Redundancy :__ The same information is present in multiple places or can be
restored by multiple ways.

__Data striping :__ Segmentation of data to byte/bit level.

__Mirroring :__ Is the duplication of all the information. Two disks will
contain the same data, so we can access one or the other to get the same data.

##d) Outline the main features of RAID 2. How does RAID 2 check for errors? (_4 marks_)
This system uses parity bits to check for errors and to detect them (if
possible). Parity bits are calculated as follows. Assume an array has four bits
(a bit can be 1 or 0). The sum of the four bits is calculated and if the sum is
even than the parity bit is 0 – if the sum is odd then the parity bit is 1. For
example, the parity bit for 0110 is even because 0 + 1 + 1 + 0 = 10 i.e. even,
parity bit is 0.

##e) Name three items from a Linux passwd file record. (_3 marks_)
```
malabar:x:1000:1000:malabar,,,:/home/malabar:/bin/bash
  1     2  3    4       5           6           7
```

1) username
2) password, now it's `x` because the hash of the password is
externalize in the `shadow` file
3) User ID
4) Group ID
5) User ID info
6) Home directory
7) Command / Shell

#Question 4
##a) Outline two methods where multiple operating systems can be made available on a single computer. What is the first Linux process to run? (_3 marks_)
Virtual Machine or Dual Boot.

The first process to run is `init`. Nowadays, many Linux distributions use
`systemd` which is more efficient and can handle more problems.

##b) What is a Linux daemon? With reference to run-level directories, describe how daemons usually get started. (_6 marks_)
A Linux daemon is a service running in the background and usually load at boot
time. A run level is selected, runs all services in the appropriate run-level
directory uses symbolic links.

\newpage
##c) With the aid of a diagram, describe how the NFS service works. (_10 marks_)
![NFS service](http://www.redhatlinuxsysadmin.com/redhat-linux-system-administration/module5/images/nfs.gif)

__Network File System__ - A server runs NFS server software. It exports a
directory to clients. Client runs NFS client software. Client has access to
exported directory subject to set permissions.

##d) Give three reasons why concurrency cannot be avoided in a modern operating system. (_6 marks_)

1) Multiple application running at the same time
2) Windowing is naturally concurrent
3) Multi-processors uses
4) Greater efficiency - makes more use of resources so system won't be idle
5) Distributed systems

#Question 5
##a) Define mutual exclusion and state the conditions for mutual exclusion to occur. (_12 marks_)
Only __one process at a time__ is allowed in the __critical section__ for a resource.

- A process that halts in its non-critical section must do so without interfering with other processes.
- No deadlock or starvation.
- A process must not be delayed access to a critical section when there is no other process using it.
- No assumptions are made about relative process speeds or number of processes.
- A process remains inside its critical section for a finite time only.

##b) The following pseudo-code is an attempt to satisfy mutual exclusion. Why does this attempt fail? (_4 marks_)

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

- Processes must strictly alternate in use of their critical sections,
- If one process fails, the other process is permanently blocked,
- Each process should have its own key to the critical section so if one process
is eliminated, the other can still access its critical section.

##c) What is the purpose of a semaphore?  Name the two types of semaphore. (_4 marks_)
This is an integer (stored in the operating system kernel) used to signal
between processes.

The are __binary__ and __counting__ semaphores.

##d) Name the three operations for accessing a semaphore. Outline how one of these can be implemented using pseudo-code. (_5 marks_)

There is the _initialisation_ (`init`), the _increment_ (`V`) and the _test_
(`P`).

~~~~{#mycode .c .numberLines}
function init (semaphore sem, int val)
{
    //we can't have an interruption during this method
    disable_interrupt;
    sem.counter = val;
    //now the interruption is allowed
    enable_interrupt;
}
~~~~
