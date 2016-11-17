(: Pour les cas où une personne ayant un indice de confiance pire (c'est à dire plus élevé par ordre alphabétique) que C offre un objet ayant un prix de réserve supérieur à 1000, afficher les nom et indices de confiance de la personne, ainsi que la description de l'objet et son prix de réserve :)
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
