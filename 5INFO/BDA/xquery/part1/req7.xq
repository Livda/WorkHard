for $livre in doc("refbib.xml")/bib/livre
return
  if (exists($livre/auteur[2]))
  then
    <livre titre="{$livre/titre}">
    {
      $livre/auteur[1],
      $livre/auteur[2],
      if (exists($livre/auteur[3]))
      then
          <auteur>et_al</auteur>
      else ()
    }
    </livre>
  else
    if(exists($livre/auteur[1]))
      then
        <livre titre="{$livre/titre}">
        {
          $livre/auteur[1]
        }
        </livre>
      else ()

(:
$ java -cp saxon8.jar net.sf.saxon.Query -t -s refbib.xml req7.xq
Saxon 8.8J from Saxonica
Java version 1.8.0_102
Compiling query from req7.xq
Compilation time: 99 milliseconds
Processing file:/home/malabar/Documents/WorkHard/5INFO/BDA/xquery/refbib.xml
Building tree for file:/home/malabar/Documents/WorkHard/5INFO/BDA/xquery/refbib.xml using class net.sf.saxon.tinytree.TinyBuilder
Tree built in 4 milliseconds
Tree size: 115 nodes, 386 characters, 5 attributes
<?xml version="1.0" encoding="UTF-8"?>
<livre titre="TCP/IP Illustrated">
   <auteur>
      <nom>Stevens</nom>
      <prenom>W.</prenom>
   </auteur>
</livre>
<livre titre="Advanced Programming in the Unix environment">
   <auteur>
      <nom>Stevens</nom>
      <prenom>W.</prenom>
   </auteur>
</livre>
<livre titre="Advanced Programming in the Unix environment">
   <auteur>
      <nom>Stevens</nom>
      <prenom>W.</prenom>
   </auteur>
</livre>
<livre titre="Data on the Web">
   <auteur>
      <nom>Abiteboul</nom>
      <prenom>Serge</prenom>
   </auteur>
   <auteur>
      <nom>Buneman</nom>
      <prenom>Peter</prenom>
   </auteur>
   <auteur>et_al</auteur>
</livre>Execution time: 84 milliseconds

:)
