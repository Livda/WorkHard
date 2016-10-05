open Grammar 
open LL1

type 'ulex parse_tree = Node of vn * 'ulex parse_tree list | Leaf of 'ulex

let parse : grammar -> ('ulex -> vt)  ->  'ulex list  -> 'ulex parse_tree = 
 fun g ->
  let null  = null g in 
  let d = deriv g in

  fun terminal_of_ulex input ->
   let rec parse_v v input =
    match v, input with
    | Vt vt1, vt2 :: r when vt1 = terminal_of_ulex vt2 -> Leaf vt2, r
    | Vn vn, vt :: _ ->
     if vn = axiom g && vt_equal (terminal_of_ulex vt) (end_of_stream g) && not (null vn) then failwith "No derivation";
     let children, input' = 
      parse_vlist (match d vn (terminal_of_ulex vt) with None -> failwith "No derivation" | Some vlist -> vlist) input 
	in 
	Node(vn, children), input'
      | _ -> failwith "No derivation"
    and parse_vlist vlist input =
      let children, input' = 
	List.fold_left (fun (children, input) v -> let tree, input' = parse_v v input in (tree :: children, input')) ([], input) vlist
      in
      List.rev children, input'
    in
    let tree, input = parse_v (Vn (axiom g)) input in
    if List.length input <> 1 then failwith "No derivation";
    tree

let print_parse_tree : ('ulex -> string) -> 'ulex parse_tree -> unit =
  fun str_of_ulex t ->
    let print fmt t =
      let rec print_node pref t =
	let (s, sons) =
	  match t with
	  | Leaf ul -> (str_of_ulex ul, [])
	  | Node(vn, children) -> (vn_to_string vn, children)
	in
	Format.pp_print_string fmt s;
	let w = String.length s in
	let pref' = pref ^ String.make (w + 1) ' ' in
        match sons with
	| []   -> ()
        | [t'] -> Format.pp_print_string fmt "---"; print_node (pref' ^ "  ") t'
        | _    -> Format.pp_print_string fmt "-"; print_sons pref' "+-" sons
	    
      and print_sons pref start = function
	| []  -> assert false
	| [s] -> Format.pp_print_string fmt "`-"; print_node (pref ^ "  ") s
	| s :: sons ->
          Format.pp_print_string fmt start; print_node (pref ^ "| ") s;
          Format.pp_force_newline fmt (); Format.pp_print_string fmt pref;
          print_sons pref "|-" sons
      in
      print_node "" t;
      Format.pp_force_newline fmt ()
    in
    print Format.std_formatter t
