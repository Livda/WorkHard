(: Modifier la requête précédente pour afficher en plus le nombre d'enchères et l'enchère la plus élevée (s'il y en a au moins une) sur chacun de ces vélos :)
for $velo in doc("objets.xml")/objets/obj_tuple[matches(description, ".*[Vv]elo.*")]
order by $velo/noobj ascending
return
  <velo>
    {
      $velo/noobj, $velo/description,
      let $encheres := doc("encheres.xml")/encheres/ench_tuple[noobj = $velo/noobj]/ench
      let $nb_encheres := count($encheres)
      return
        if ($nb_encheres > 0)
        then
          <encheres>
            <nb>
              {$nb_encheres}
            </nb>
            <max>
              {max($encheres)}
            </max>
          </encheres>
        else ()
    }
  </velo>
