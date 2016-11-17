<auteurs>
{for $f in doc("refbib.xml")/bib/livre
return
  for $a in $f//auteur
  return
    <nom>
    {$a/prenom/text(),$a/nom/text()}
    </nom>}
</auteurs>

(:
$ java -cp saxon8.jar net.sf.saxon.Query -t -s refbib.xml req2.xq
Saxon 8.8J from Saxonica
Java version 1.8.0_102
Compiling query from req2.xq
Compilation time: 84 milliseconds
Processing file:/home/malabar/Documents/WorkHard/5INFO/BDA/xquery/refbib.xml
Building tree for file:/home/malabar/Documents/WorkHard/5INFO/BDA/xquery/refbib.xml using class net.sf.saxon.tinytree.TinyBuilder
Tree built in 2 milliseconds
Tree size: 115 nodes, 386 characters, 5 attributes
<?xml version="1.0" encoding="UTF-8"?>
<auteurs>
   <nom>W.Stevens</nom>
   <nom>W.Stevens</nom>
   <nom>W.Stevens</nom>
   <nom>SergeAbiteboul</nom>
   <nom>PeterBuneman</nom>
   <nom>DanSuciu</nom>
</auteurs>Execution time: 73 milliseconds
:)
