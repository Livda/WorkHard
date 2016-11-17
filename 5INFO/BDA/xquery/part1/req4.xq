(: Afficher tous les livres en les triant par ordre alphabétique croissant de titre :)
for $f in doc("refbib.xml")/bib/livre
order by $f/titre
return $f

(:
$ java -cp saxon8.jar net.sf.saxon.Query -t -s refbib.xml req4.xq
Saxon 8.8J from Saxonica
Java version 1.8.0_102
Compiling query from req4.xq
Compilation time: 79 milliseconds
Processing file:/home/malabar/Documents/WorkHard/5INFO/BDA/xquery/refbib.xml
Building tree for file:/home/malabar/Documents/WorkHard/5INFO/BDA/xquery/refbib.xml using class net.sf.saxon.tinytree.TinyBuilder
Tree built in 3 milliseconds
Tree size: 115 nodes, 386 characters, 5 attributes
<?xml version="1.0" encoding="UTF-8"?>
<livre annee="1992">
      <titre>Advanced Programming in the Unix environment</titre>
      <auteur>
      <nom>Stevens</nom>
      <prenom>W.</prenom>
   </auteur>
      <edition>Addison-Wesley</edition>
      <prix>64.35</prix>
   </livre>
<livre annee="1995">
     <titre>Advanced Programming in the Unix environment</titre>
     <auteur>
      <nom>Stevens</nom>
      <prenom>W.</prenom>
   </auteur>
     <edition>Addison-Wesley</edition>
     <version>Second edition</version>
     <prix>69.95</prix>
   </livre>
<livre annee="2000">
      <titre>Data on the Web</titre>
      <auteur>
      <nom>Abiteboul</nom>
      <prenom>Serge</prenom>
   </auteur>
      <auteur>
      <nom>Buneman</nom>
      <prenom>Peter</prenom>
   </auteur>
      <auteur>
      <nom>Suciu</nom>
      <prenom>Dan</prenom>
   </auteur>
      <edition>Morgan Kaufmann Publishers</edition>
      <prix>39.95</prix>
   </livre>
<livre annee="1994">
      <titre>TCP/IP Illustrated</titre>
      <auteur>
      <nom>Stevens</nom>
      <prenom>W.</prenom>
   </auteur>
      <edition>Addison-Wesley</edition>
      <prix>65.95</prix>
  </livre>
<livre annee="1999">
      <titre>The Economics of Technology and Content for Digital TV</titre>
      <editeur>
           <nom>Gerbarg</nom>
      <prenom>Darcy</prenom>
           <affiliation>CITI</affiliation>
      </editeur>
      <edition>Kluwer Academic Publishers</edition>
      <prix>129.95</prix>
   </livre>Execution time: 84 milliseconds
:)
