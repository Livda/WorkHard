for $person in doc("personnes.xml")/personnes/perso_tuple[confiance > 'C'],
    $obj in doc("objets.xml")/objets/obj_tuple[propose_par = $person/idperso]
where
  $obj/prix_de_reserve > 1000
return
  <strange_query>
    <person>
      {$person/nom, $person/confiance}
    </person>
    <objet>
      {$obj/description, $obj/prix_de_reserve}
    </objet>
  </strange_query>
