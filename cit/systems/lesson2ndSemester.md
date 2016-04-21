---
title: Lesson Operating System
date: Second semester 2015/2016
geometry: margin=3cm
---

#Lecturer informations
__Name :__ Gerard Mac Sweeney

__Office :__ B224L

__Mail :__ [gerard.macsweeney@cit.ie](mailto:gerard.macsweeney@cit.ie)

#Assessment

__Continuous Assessment:__ _30 %_ - two labs exercises

__Final Exam:__ _70 %_

#Review of Operating Systems Fundamentals

##Introduction
Operating systems have 2 main roles :

###An interface
This is a suite of programs which together provide an _interface_ between the
user and the hardware. The OS manages the complex interactions required by a
running program when dealing with processor(s) (CPU), main memory (RAM),
secondary storage (disks, tapes), printers, keyboards, screens, network cards,
etc. It also presents the user/application programmer with a simplified (but
effective) view of these complex interactions.

###A manager
The OS also _manages_ the allocation of machine resources to running programs.
These resources include the processor, memory and storage. It must manage this
allocation fairly and efficiently.

##Goals of an OS
- Make the computer convenient to use,
- Manage the use of hardware resources efficiently & fairly and co-ordinate its
use by programs,
- Allow easy expansion,
- Provides a workable interface for use,
- Operates and controls use of I/O devices and allocate resources of the system
to the programs. For example CPU time, main memory space, disk space, I/O
devices, etc,
- OS must manage conflicts fairly and efficiently and controls access to and use
of the system; security from damage; protection of processes.

##Operating System Services
The OS provides services to users and programmers. The user accesses these
services through either systems programs or application programs. The
programmers (both systems programmers and application programmers) call on these
services.

In general, the services provided include:

###Program Development
The OS offers a variety of services and facilities such as editors and
debuggers. These assist developers in creating programs and although strictly
not part of the core of the OS – they are generally supplied with the operating
system.

###I/O operations
All input and output from/to devices is handled by OS. The OS provides a uniform
interface to these.

###Controlled access to files
The OS will open, close, create, delete, and move files about on disk/tape. On a
system with multiple users, it may provide protection to individual users’ data.

###System Access
For shared or public systems, the OS controls access to the system as a whole.

###Error detection
The OS watches for errors in: - h/w (e.g. power failure, disk head crash); I/O
devices (e.g. printer out of paper); - programs (e.g. arithmetic overflow,
division by zero).

###Resource allocation
Any resources required by programs are requested from OS.

###Accounting
The OS keeps track of resource use for payment, protection or statistical
purposes. Protection accounting allows system to record damage done. Statistics
are used in trying to improve service.

###Protection
As well as ensuring private data is kept private (e.g. mailboxes, personnel records) the OS protects programs from each other (especially other OS programs).

##Computer System Overview
###Basic Elements
####The Processor
The Central Processor Unit (CPU) controls the operation of the computer and
performs data processing tasks. The processor can be divided into two major
components the __arithmetic logic unit__ (A.L.U.) and the __control unit__(C.U.).

####Main Memory
This stores data and programs. It is volatile – often referred to as RAM

####I/O Modules
These move data between the computer and external devices.

####System Bus
The enables communication among processor, memory and external devices

####Storage
This provides non-volatile permanent storage.

\newpage
###Top Level View of the System
![Computer components: Top-level view](http://voer.edu.vn/file/11893)

The Processor consists of:

- __ALU__ – Arithmetic Logic Unit which performs operations (add, sub, etc.);
- __CU__ – Control Unit which co-ordinates operation of the various CPU components;

Other components include:

- A __Clock__ that synchronizes the CPU and the entire system.
- __Registers__ which store information to control operations, these include:
    - __IR__ – Instruction Register which contains the instruction last fetched;
    - __PC__ – Program Counter which contains the memory address of the next
    instruction to be fetched;
    - __MAR__ – Memory Address Register which contains the address for the next
    read/write;
    - __MBR__ – Memory Buffer Register which contains the data to be written to
    memory OR contains the data to be read  from memory;
    - __I/OAR__ – I/O Address Register which specifies an i/o device;
    - __I/OBR__ – I/O Buffer Register which is used for exchange of data to/from
    the i/o module;

###Memory Hierarchy
![Hierarchy of a general computer](http://cse1.net/recaps/img/4-hierarchy.jpg)

[Interesting link to see](http://computer.howstuffworks.com/virtual-memory.html)

__Cache Memory__ is smaller and faster than main memory – here frequently
accessed data can be stored for rapid access. It is possible to have multiple
layers of cache.

![Cache memory](https://static.lwn.net/images/cpumemory/cpumemory.1.png)
The processor first checks the cache - if not found in cache, the block of
memory containing the needed information is moved to the cache.

###Virtual memory
Virtual memory is a technique to enable disk space to enhance main memory.
Programs which require more memory than available can be run. It means that
more applications can run concurrently. Managing memory is a significant part of
an operating systems’ role. The operating system determines the amount of memory
available for each activity and for how long that memory is available.

####The Fetch Execute Cycle
Each instruction / data is fetched from memory and executed followed by the next
instruction, etc. This is how the processor gets things done.
