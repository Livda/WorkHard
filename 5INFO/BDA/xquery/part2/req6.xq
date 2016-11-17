(: Afficher les noms des persones (s'il en existe) qui ont enchéri sur tous les articles proposés :)
for $person_id in doc("personnes.xml")/personnes/perso_tuple/idperso
let $encheres := doc("encheres.xml")/encheres/ench_tuple[idperso = $person_id]
