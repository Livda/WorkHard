(: Afficher les objets mis en vente par chaque personne, en indiquant le nom du vendeur également :)
for $person in doc("personnes.xml")/personnes/perso_tuple
return
  <objets owner="$person/nom">
    {
      for $obj in doc("objets.xml")/objets/obj_tuple[propose_par = $person/idperso]
      $obj
    }
  </objets>
