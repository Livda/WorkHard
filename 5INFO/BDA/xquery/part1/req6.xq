<resultats>
{
  for $titre in distinct-values(doc("refbib.xml")/bib/livre/titre)
  return
    <prixmin title="{$titre}">
      {
        let $list :=  doc("refbib.xml")/bib/livre[titre=$titre]/prix
        return
          <prix>{min($list)}</prix>
      }
    </prixmin>
}
</resultats>

(:
$ java -cp saxon8.jar net.sf.saxon.Query -t -s refbib.xml req6.xq
Saxon 8.8J from Saxonica
Java version 1.8.0_102
Compiling query from req6.xq
Compilation time: 101 milliseconds
Processing file:/home/malabar/Documents/WorkHard/5INFO/BDA/xquery/refbib.xml
Building tree for file:/home/malabar/Documents/WorkHard/5INFO/BDA/xquery/refbib.xml using class net.sf.saxon.tinytree.TinyBuilder
Tree built in 3 milliseconds
Tree size: 115 nodes, 386 characters, 5 attributes
<?xml version="1.0" encoding="UTF-8"?>
<resultats>
   <prixmin title="TCP/IP Illustrated">
      <prix>65.95</prix>
   </prixmin>
   <prixmin title="Advanced Programming in the Unix environment">
      <prix>64.35</prix>
   </prixmin>
   <prixmin title="Data on the Web">
      <prix>39.95</prix>
   </prixmin>
   <prixmin title="The Economics of Technology and Content for Digital TV">
      <prix>129.95</prix>
   </prixmin>
</resultats>Execution time: 83 milliseconds
:)
