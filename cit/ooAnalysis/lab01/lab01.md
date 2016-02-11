---
title: OO Analysis and Design Lab Week 1
author: Aurélien Fontaine
date: 4/02/2016
abstract : The purpose of this lab is to understand event Analysis and System Sequence Diagrams
geometry: margin=3cm
---

#Question 1
##(i)
_Identify the additional events to which the automated system must now respond._

The new events are :

- Record student's grades at the end of each term
- Generate grades records
- Send record of his notes to each student
- Send record of all the students that follow his course to each professor


##(ii)
_Modify the event table below to incorporate theseevents. Imagine that each
system input associated with an event arrives individually._

\begin{center}
    \begin{tabular}{|l|l|l|l|l|l|}
        \hline
        Event & Event & System & Actor & System & Actor \\
        number & description & input & providing & output & receiving \\
        & & & input & & output \\
        \hline
        5 & Record student's grades & Student's grades & Professor & & \\
        \hline
        6 & Generate student's report & 1 week after the exams & &
        Student's report & Students \\
        \hline
        7 & Generate professor report & 1 week after the exams & &
        Professor's report & Professor \\
        \hline
    \end{tabular}
\end{center}

##(iii)
_List other events which might be included in
a University Registration system of extended scope._

##(iv)
_Modify Use Case diagram to incorporate additional
requirements._

##(v)
_Prepare a use case narrative for the additional events in the Public
Registration system._

#Question 2

3. Adds student to the section only if there are seats available and the student
have all the preconditions
