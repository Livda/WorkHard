open Grammar
open Equation

(** [is_vn vn v] returns whether v is the non-terminal vn *)
let is_vn : vn -> v -> bool =
  fun vn v -> 
  match v with
  | Vt x -> false
  | Vn y -> vn_equal y vn
		     
(** Accessibility *)
let accv : vn -> var =
  fun vn -> ("Acc",vn)
	      
let acc_vn : grammar -> vn -> bexpr =  
  fun g vn ->  
  let l = gather_sprod_using_vn g vn in
  if vn_equal vn (axiom g)
  then Bool true
  else
    List.fold_left (fun e sp -> Or(BVar(accv sp.ntl),e)) (Bool false) l

let acc_sys : grammar -> equation list =
  fun g -> make_bool_system g "Acc" acc_vn

let acc : grammar -> vn -> bool =
  fun g ->
  let sol = solve (acc_sys g) in
  fun vn -> fst sol ("Acc",vn)

let check_acc : grammar -> bexpr =
  fun g ->  VNSet.fold (fun vn c -> And(BVar(accv vn),c)) (nterminals g) (Bool true)
		       
(** Generative *)

let genv : vn -> var =
  fun vn -> ("Gen",vn)
	      
let rec gen_prod : v list -> bexpr = 
  fun l ->
  match l with 
  | [] -> Bool true
  | Vt _ :: l -> gen_prod l
  | Vn v :: l -> And(BVar (genv v),gen_prod l)

let gen_vn : grammar -> vn -> bexpr = 
  fun g vn -> 
  let ls = production g vn in
  List.fold_left (fun acc vs -> Or(acc,gen_prod vs)) (Bool false) ls

let gen_sys : grammar -> equation list =
  fun g -> make_bool_system g "Gen" gen_vn

let gen : grammar -> vn -> bool =
  fun g  ->
  let sol = solve (gen_sys g) in 
  fun vn -> fst sol ("Gen",vn) 

let check_gen : grammar -> bexpr =
  fun g -> VNSet.fold (fun vn c -> And(BVar (genv vn),c)) (nterminals g) (Bool true)

(** Null *)

let nullv : vn -> var  =
  fun vn -> ("Null",vn)

(* Done *)
let rec null_prod :  v list -> bexpr = 
  fun  l -> 
  match l with 
  |[] -> Bool true
  |Vt _ :: l -> Bool false
  |Vn v :: l -> And(BVar (nullv v), null_prod l)

(* Done *)
let null_vn   : grammar -> vn -> bexpr = 
  fun g vn ->  
  let ls = production g vn in
  List.fold_left (fun acc vs -> Or(acc,null_prod vs)) (Bool false) ls

let null_sys : grammar -> equation list = 
  fun g -> make_bool_system g "Null" null_vn

let null : grammar -> vn -> bool =
  fun g  ->
  let sol = solve (null_sys g) in
  fun vn -> fst sol  (nullv vn)

(** First *)
let firstv : vn -> var =
  fun vn -> ("First",vn)

(* DONE *)
let rec first_prod : v list -> sexpr =
  fun  l ->
  match l with
  |[] -> Set VTSet.empty
  |Vt t :: l -> Set (VTSet.singleton t)
  |Vn n :: l -> Union(SVar (firstv n), (Cond(BVar(nullv n), first_prod l)))
(* DONE *)
let first_vn : grammar ->  vn -> sexpr = 
  fun g vn ->
  let ls = production g vn in
  List.fold_left (fun acc vs -> Union(acc, first_prod vs)) (Set VTSet.empty) ls
		 
let first_sys : grammar -> equation list = 
  fun g -> make_set_system g "First"  first_vn

let first : grammar ->  vn -> VTSet.t =
  fun g  ->
  let sol = solve ((null_sys g) @(first_sys g)) in
  fun vn -> snd sol  ("First",vn) 

(** Follow *)
let followv : vn -> var =
  fun vn ->  ("Follow",vn)

(*DONE*)	       
let follow_vn : grammar ->  vn -> sexpr =
  fun g vn ->
  let ls = gather_sprod_using_vn g vn in
  List.fold_left (fun acc vs -> Union(acc,
				      Union ((first_prod vs.wordr),
					     Cond((null_prod vs.wordr),
						  SVar(followv vs.ntl)))))
		 (Set VTSet.empty)
		 ls

let follow_sys : grammar -> equation list =
  fun g -> make_set_system g "Follow" follow_vn

let follow : grammar -> vn -> VTSet.t =
  fun g ->
  let sol = solve ((null_sys g) @(first_sys g)@(follow_sys g)) in  
  fun vn -> snd sol ("Follow",vn) 

(** Checking LL1 *)

(*TODO*)
let ll1_vn : grammar -> vn -> bexpr =
  fun g vn ->
  let rec change_in_list_Union v =
    match v with
    |a -> a :: []
    |Union(a,b) -> (change_in_list_Union a) @ (change_in_list_Union b) in
  let rec change_in_list_Or v =
    match v with
    |a -> a :: []
    |Or(a,b) -> (change_in_list_Or a) @ (change_in_list_Or b) in
  let rec paire a l =
    match l with
    |[] -> []
    |h :: t -> Union(a, h) :: (paire a t) in 
 let rec make_paire l =
   match l with
   |[] -> []
   |h :: t -> (paire h t) @ (make_paire t) in 
 let rec inter_list l =
   match l with
   |[] -> Set VTSet.empty
   |h::[] -> h
   |h::t -> (Inter(h, inter_list t)) in
 let rec null_list l =
   match l with
   |[] -> Bool true
   |h :: t -> Or((null_prod h), (null_list t)) in
 let produc = production g vn in
 let inter = inter_list (make_paire (change_in_list_Union (first_vn g vn))) in
 let list_null = (change_in_list_Or (null_list produc)) in
 let if_null = (Cond ((null_vn g vn), Inter((first_vn g vn), (follow_vn g vn)))) in

 And(IsEmpty (inter),
     And( ExistsUniq (list_null),
	 IsEmpty(if_null)))
    
let is_ll1 : grammar  -> bexpr =
  fun g -> 
  let vns = nterminals  g in
  VNSet.fold (fun vn e -> And(ll1_vn g vn,e))  vns (Bool true)


(** Derivation *)

let is_first : solution -> vn -> VTSet.t =
  fun sol v -> snd sol (firstv v)

let is_null : solution -> vn -> bool =
  fun sol v -> fst sol (nullv v)

let is_follow : solution -> vn -> VTSet.t =
  fun sol v -> snd sol (followv v)

let deriv : grammar -> vn -> vt -> (v list) option =
  fun g ->
  let sol = solve ((null_sys g) @(first_sys g)@(follow_sys g)) in  
  fun vn vt -> 
  let prods = production g vn in
  (* TODO *) None
