<biblio>
{
  for $f in doc("refbib.xml")/bib/livre
  where
    $f/@annee > 1992 and
    $f/edition = "Addison-Wesley"
  return
    for $l in $f
    return
      <livre annee="{$l/@annee}">
      {
        $l/titre
      }
      </livre>
}
</biblio>

(:
$ java -cp saxon8.jar net.sf.saxon.Query -t -s refbib.xml req3.xq
Saxon 8.8J from Saxonica
Java version 1.8.0_102
Compiling query from req3.xq
Compilation time: 89 milliseconds
Processing file:/home/malabar/Documents/WorkHard/5INFO/BDA/xquery/refbib.xml
Building tree for file:/home/malabar/Documents/WorkHard/5INFO/BDA/xquery/refbib.xml using class net.sf.saxon.tinytree.TinyBuilder
Tree built in 3 milliseconds
Tree size: 115 nodes, 386 characters, 5 attributes
<?xml version="1.0" encoding="UTF-8"?>
<biblio>
   <livre annee="1994">
      <titre>TCP/IP Illustrated</titre>
   </livre>
   <livre annee="1995">
      <titre>Advanced Programming in the Unix environment</titre>
   </livre>
</biblio>Execution time: 78 milliseconds

:)
