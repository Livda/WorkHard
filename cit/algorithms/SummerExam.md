%Summer exam 2015 - Algorithm
%[Aurélien Fontaine](mailto:aurelien.fontaine@mycit.ie), DCOM2
%\today

#Question 1 : Mandatory question
##Section A : (Linked List)

###(i) _Draw a sketch diagram to represent a linked list containing three nodes. Please note each node can contain a single integer value._ (2 marks)
![Linked list](http://people.engr.ncsu.edu/efg/210/s99/Notes/LLdefs.gif)

###(ii) _Compare a linked list with a fixed size array data structure under the following headings: Memory Overhead and Data Access_ (5 marks)

+-------------+-----------------------+-------------------+
|             |       Linked list     |       Array       |
+=============+=======================+===================+
|   Memory    | Just the size         | Take the size of  |
|  Overhead   | of the value needed   | as defined at the |
|             |                       | initalization     |
+-------------+-----------------------+-------------------+
|    Data     | Could be long if the  | Very fast with an |
|   Access    | element researched is |       index       |
|             |       far away        |                   |
+-------------+-----------------------+-------------------+

###(ii) _Use the Employee class to create an employee object. Finally create a Node object that will store, as its data, the employee object you created._ (8 marks)
_Appendix A provides the code for a Node that is capable of storing an Object as its data. Write a simple Employee class in Java that will store an employee’s name and annual salary. This class should include a constructor that accepts a String (employee name) and a double (salary) as arguments. The class should also provide a toString method (which overides the default toString method). The toString method should return a single String containing the name of the employee and their salary._

~~~{.myCode .java .numberLines}
public class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary){
        this.name = name;
        this.salary = salary;
    }

    public String toString(){
        return name + ": " + salary;
    }
}

//main method
public static void main(String[] args){
    Employee e = new Employee("Poor John", 1.00);
    Node n = new Node(e);
}
~~~

###(iv) _The code below shows a skeleton method called printEmployees. This method takes in a single Node reference variable as an argument. The reference variable points to the first Node in a linked list of nodes. Each node stores an Employee object as its data. Complete the method below so that it will print out the contents of all employees stored in the linked list._ (10 marks)

~~~{.myCode .java .numberLines}
public void printEmployee(Node head){
    boolean isTail = (head.getNext() == null);
    if(!isTail){
        System.out.print(head.getItem().toString() + " , ");
        //here a recursive function
        printEmployee(head.getNext());
    } else {
        System.out.println("");
    }
}
~~~

##Section B : (Recursion)
###(i) _Briefly describe the concept of recursion. In your answer identify two limitations of recursion and explain the motivation you may have for adopting a recursive solution to a problem over a more conventional iterative solution (such as for loops, while loops, etc)._ (5 marks)

The concept of recursion is too call a function into this function. The goal is to divide the big problem on smaller problems and to delegate the rest to an other function.

###(ii) _The objective of this question is to write a recursive method called power that will calculate the result of raising one number to the power of another._ (10 marks)

~~~{.myCode .java .numberLines}
public long power(long base, int power){
    //base cases
    if (power < 0) return -1; //negative power doesn't exists
    if (power = 0) return 1;
    if (power < 2) return base;

    //recurcive case
    return base * power(base, power-1);
}
~~~

#Question 2 : (Binary Trees and Comparable Interface)
##(i) _Compare the performance of a linked list and binary tree data structure under the following two headings: a.Insertion and deletion of data b.Searching for a specific element_ (6 marks)

##(ii) _Create a binary tree from the following values 22, 3, 44, 26, 25, 56, 76, 2, 97, 33, 98, 100. Note the order in which the numbers appear indicate the order in which they are added to the binary tree (the first number in the list is added first and so on)_ (3 marks)

```
                    22
                   /   \
                  3    44
                 /    /  \
                2    26  56          #EasyTree
                    /  \   \
                   25  33  76
                             \
                             97
                               \
                               98
                                 \
                                 100
```

##(iii) _Explain in detail the operation of the following two methods. Use the tree you created in part (ii) to help illustrate your answer._ (8 marks)

~~~{.myCode .java .numberLines}
//Preorder Traversal
public synchronized void preorderTraversal()
{ preorderHelper( root); }  // root is a TreeNode

//Recursive method to perform preorder traversal
private void preorderHelper(TreeNode node)
{
    if ( node == null ) return;
    System.out.print (node.data + " ");
    preorderHelper ( node.left );
    preorderHelper ( node.right );
}
~~~

It's a preorder printing. That mean, for a node, the value of the node is print, then its left node then
its right node. And it's working recurcivly. On the tree made in __(ii)__, the result of this function is :

22 3 2 44 26 25 33 56 76 97 98 100

~~~{.myCode .java .numberLines}
//Inorder Traversal
public synchronized void inorderTraversal()
{ inorderHelper( root); }

// Recursive method to perform inorder traversal
private void inorderHelper (TreeNode node)
{
if ( node == null )
    return;
    inorderHelper ( node.left );
    System.out.print (node.data + " ");
    inorderHelper ( node.right );
}
~~~

It's an inorder printing. That mean, for a node, the the left node is print, then its the values of this node then its right node. And it's working recurcivly. On the tree made in __(ii)__, the result of this function is :

2 3 22 25 26 44 56 76 97 98 100

##(iv) _By altering the code presented in part (iii) write code to perform a __post-order__ traversal. Clearly explain the operation of your code._ (5 marks)

~~~{.myCode .java .numberLines}
//Post-order Traversal
public synchronized void postOrderTraversal()
{ postOrderHelper( root); }

// Recursive method to perform post-order traversal
private void postOrderHelper (TreeNode node)
{
if ( node == null )
    return;
    postOrderHelper ( node.left );
    postOrderorderHelper ( node.right );
    System.out.print (node.data + " ");
}
~~~

##(v) _Write a java class called Student._ (8 marks)
_This class should have a constructor that will set the value of three instance variables; the first name
and surname of the student (both Strings) and the age of a Student (age is a double data type). It should also have appropriate getter and setter method. The class should implement the Comparable interface. The compareTo method should compare students based on their age value.

Write a main method that will create two student objects and print the names of the students in order of ascending age._

~~~{.myCode .java .numberLines}
public class Student implements Comparable {
    private String name;
    private String surname;
    private double age;

    public String getName(){ return this.name; }
    public void setName(String name){ this.name = name; }
    public String getSurname(){ return this.surname; }
    public void setSurname(String surname){ this.surname = surname; }
    public double getAge(){ return this.age; }
    public void setAge(double age){ this.age = age; }

    public Student(String name, String surname, double age){
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public int compareTo(Student s){
        double age1 = this.getAge();
        double age2 = s.getAge();
        if (age1 > age2) return 1;
        if (age1 < age2) return -1;
        return 0;
    }

    public static void main(String[] args){
        Student s1 = new Student("Jack","The Ripper", 128);

        //if you dont know her, just go check Wikipedia
        //this woman is the mother of the compilation
        Student s2 = new Student("Grace", "Hopper", 110);

        List<Student> list = new ArrayList<Student>();
        list.add(s1);
        list.add(s2);
        Collections.sort(list);

        for(Student s : list){
            System.out.print(s.getName + " ");
        }
    }
}
~~~

#Question 3 : (Queues)
##(i) _Describe the concept of a queue data structure. Provide a drawing to help illustrate the operation of a queue_ (5 marks)

A queue is a particular kind of abstract data type or collection in which the entities in the collection are kept in order and the principal (or only) operations on the collection are the addition of entities to the rear terminal position, known as enqueue, and removal of entities from the front terminal position, known as dequeue. This makes the queue a First-In-First-Out (FIFO) data structure.
![Queue](https://netmatze.files.wordpress.com/2014/08/queue.png)

Tanks [Wikipedia](https://en.wikipedia.org/wiki/Queue_%28abstract_data_type%29)

##(ii) _Some of the methods specified in the Queue interface (show in question __(iii)__ below) throw a QueueEmptyException. Write the QueueEmptyException class. Please note the QueueEmptyException will extend a RunTimeException_ (5 marks)

~~~{.myCode .java .numberLines}
public class QueueEmptyException extends RunTimeException {

    public QueueEmptyException(){
        super();
    }

    public QueueEmptyException(String message){
        super(message);
    }
}
~~~

##(iii) _Write a java class to provide a reference-based implementation for the following interface. You can make reference to the Node class contained in Appendix A._ (12 marks)

~~~{.myCode .java .numberLines}
public class Queue implements QueueInterface {
    private Node head;

    public boolean isEmpty(){
        return head == null;
    }

    public void enqueue(Object newItem){
        Node newNode = new Node(newItem);
        if (this.isEmpty()){
            this.head = newNode;
        } else {
            Node current = head;
            while (current.getNext() != null){
                Node tmp = current.getNext();
                current = tmp;
            }
            current.setNext(newNode);
        }
    }

    public Object dequeue() throws QueueEmptyException {
        if (this.isEmpty) throws new QueueEmptyException();
        Node newHead = this.head.getNext();
        Node oldHead = this.head;
        this.head = newHead;
        return oldHead;
    }

    public void dequeueAll() {
        this.head = null;
    }

    public Object peek() throws QueueEmptyException {
        if (this.isEmpty) throws new QueueEmptyException();
        return head;
    }
}
~~~

##(iv) _“For applications in which a fixed-sized queue does not present a problem, you can use an array as the data structure to represent a queue”._ (8 marks)

_Explain the problem of rightward drift that occurs with a fixed size array implementation of a queue. Clearly describe the concept of a circular queue and how it can be used to overcome the problem of rightward drift. Explain how the enqueue and dequeue methods are performed for a circular queue. Support our answer with drawings of a circular queue._

![Circular Queue](https://qph.is.quoracdn.net/main-qimg-b997af4ada9bf82e2602d7a5a8019b53?convert_to_webp=true)
