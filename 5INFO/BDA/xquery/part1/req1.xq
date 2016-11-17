for $f in doc("refbib.xml")/bib/livre
where $f/titre="Data on the Web"
return
  for $a in $f//auteur
  return ($a/prenom, $a/nom)

(:
$  java -cp saxon8.jar net.sf.saxon.Query -t -s refbib.xml req1.xq
Saxon 8.8J from Saxonica
Java version 1.8.0_102
Compiling query from req1.xq
Compilation time: 139 milliseconds
Processing file:/home/malabar/Documents/WorkHard/5INFO/BDA/xquery/refbib.xml
Building tree for file:/home/malabar/Documents/WorkHard/5INFO/BDA/xquery/refbib.xml using class net.sf.saxon.tinytree.TinyBuilder
Tree built in 3 milliseconds
Tree size: 115 nodes, 386 characters, 5 attributes
<?xml version="1.0" encoding="UTF-8"?>
<prenom>Serge</prenom>
<nom>Abiteboul</nom>
<prenom>Peter</prenom>
<nom>Buneman</nom>
<prenom>Dan</prenom>
<nom>Suciu</nom>Execution time: 417 milliseconds
:)
