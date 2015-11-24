open Grammar

type 'ulex parse_tree = Node of vn * 'ulex parse_tree list | Leaf of 'ulex

val parse : grammar -> ('ulex -> vt)  -> 'ulex list -> 'ulex parse_tree

val print_parse_tree : ('ulex -> string) -> 'ulex parse_tree -> unit
