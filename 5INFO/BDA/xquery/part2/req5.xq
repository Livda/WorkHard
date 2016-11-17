(: Afficher les articles dont au moins une enchère est supérieure au prix de réserve, en indiquant pour chaque objet sa description, le nom de son vendeur et la date de clôture de la vente :)

for $objet in doc("objets.xml")/objets/obj_tuple,
    $vendeur in doc("personnes.xml")/personnes/perso_tuple[idperso = $objet/propose_par]
let $enchere := doc("encheres.xml")/encheres/ench_tuple[ench > $objet/prix_de_reserve and noobj = $objet/noobj]
return
  if (count($enchere) > 0)
  then
  <article>
    {
      $objet/description,
      $vendeur/nom,
      $objet/date_fin
    }
  </article>
  else ()
