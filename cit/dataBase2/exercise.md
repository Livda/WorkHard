---
title: Data Base System 2 - Exercise
author: Aurélien Fontaine
date : 15 feb 2016
geometry: margin=3cm
---

#Wording

In an online web sales company, the following information is stored. Customers
(re)order certain quantities of products on different days. Products identified
by a Product Id are described in a text field and have a set price.  Each
customer is given a customer number. We also store the customer name along with
their address and bank details.

`
Orders (Cust_no, Prod_id, Cust_name, Description, Qty, Price, BankAC,
OrderDate, Address);`

1. Devise a table (1NF) with dummy data for the application i.e. make up the
data.
2. List dependencies (attribute groupings with potential identifiers)
3. Designate a primary key for the single table above if you were to use it as
a database
4. List any processing problems with the 1NF table above.
5. Redesign the table (normalize it) to remove these problems. Designate a key
for table you add to the design.

#Answers

##The devised table

\begin{center}
    \begin{tabular}{|l|l|l|l|l|l|l|l|l|}
        \hline
        Cust\_no & Prod\_id & Cust\_name & Description & Qty & Price & BankAC & OrderDate & Address \\
        \hline
    \end{tabular}
\end{center}

On each line, we put, all the details of each order. No line will be identical.

##The dependencies
The costumer dependencies :

- Cust_no
- Cust_name
- Address
- BankAC

The product dependencies :

- Prod_id
- Description
- Price

The order dependencies :

- A costumer
- Some products
- OrderDate
- The quantity per product

##The primary keys

\begin{center}
    \begin{tabular}{|l|l|c|}
        \hline
        Customer & Product & Order \\
        \hline
        Cust\_no & Prod\_id & Cust\_no + Prod\_id + OrderDate \\
        \hline
    \end{tabular}
\end{center}

##The problems of the 1NF table

There is many repetitions one the table. If we need to change anything, there is
multiple rows to change, which can cause some inconstancy in the data.

##The new division

The primary keys are underline.

\begin{center}
    \begin{tabular}{|l|l|l|l|}
        \hline
        \underline{Cust\_no} & Cust\_name & Address & BankAC \\
        \hline
    \end{tabular}

    \vspace{1cm}

    \begin{tabular}{|l|l|l|}
        \hline
        \underline{Prod\_id} & Description & Price \\
        \hline
    \end{tabular}

    \vspace{1cm}

    \begin{tabular}{|l|l|l|l|}
        \hline
        \underline{Cust\_no} & \underline{Prod\_id} & \underline{OrderDate} & Qty\\
        \hline
    \end{tabular}
\end{center}

If we take count of the variable price, the price don't belong to the product
any more, but to the transaction. That's the only modification for this data
base.
