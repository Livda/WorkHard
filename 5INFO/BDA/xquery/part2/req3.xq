(: Pour tous les vélos, lister par ordre croissnat du numéro de l'objet ses numéro et description :)
for $velo in doc("objets.xml")/objets/obj_tuple[matches(description, ".*[Vv]elo.*")]
order by $velo/noobj ascending
return
  <velo>
    {$velo/noobj, $velo/description}
  </velo>
