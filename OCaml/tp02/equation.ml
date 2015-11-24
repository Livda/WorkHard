open Grammar

type var = string * vn

exception MultipleDefinition of var
exception Undefined of var
  
module VarMap = Map.Make(struct type t  = var let compare = compare end)

module VarSet = Set.Make(struct type t  = var let compare = compare end)
 
type bexpr =
| BVar    of var
| Bool    of bool
| And     of bexpr * bexpr 
| Or      of bexpr * bexpr 
| Implies of bexpr * bexpr
| ExistsUniq of bexpr list
| IsEmpty of sexpr
and sexpr =
| SVar  of var
| Set   of VTSet.t
| Union of sexpr * sexpr
| Inter of sexpr * sexpr 
| Cond  of bexpr * sexpr

type equation =
| BEq of var * bexpr
| SEq of var * sexpr


let rec eval_bexpr vb vs e = 
 let rec xeval_bexpr = function
  | BVar v -> vb v
  | Bool b -> b
  | And(e1,e2) -> (xeval_bexpr e1) && (xeval_bexpr e2)
  | Or(e1,e2)  -> (xeval_bexpr e1) || (xeval_bexpr e2)
  | Implies(e1,e2) -> (not (xeval_bexpr e1)) || (xeval_bexpr e2)
  | IsEmpty e -> VTSet.is_empty (eval_sexpr vb vs e)
  | ExistsUniq l ->
   (List.fold_left (fun acc e -> if xeval_bexpr e then acc + 1 else acc) 0 l = 1) in
 xeval_bexpr e
 
and  eval_sexpr vb vs e =
 let rec xeval_sexpr = function
  | SVar  v       -> vs v
  | Set s        ->  s
  | Union(e1,e2) -> VTSet.union (eval_sexpr vb vs e1) (eval_sexpr vb vs e2)
  | Inter(e1,e2) -> VTSet.inter (eval_sexpr vb vs e1) (eval_sexpr vb vs e2)
  | Cond(eb,es)  -> if eval_bexpr vb vs eb then eval_sexpr vb vs es else VTSet.empty  in
 xeval_sexpr e

let mkand e e' =
 match e, e' with
 | Bool false , _ | _ , Bool false -> Bool false
 | Bool true  , x | x , Bool true   -> x
 |  _ , _ -> And(e,e')

let mkor e e' = 
 match e, e' with
 | Bool true , _ | _ , Bool true -> Bool true
 | Bool false , x | x , Bool false -> x
 |  _ , _ -> Or(e,e')

let mkimpl e e' =
 match e  with
 | Bool true  -> e'
 | Bool false -> Bool true
 | _          -> Implies(e,e')

let mkuniq l =
 let isTrue e = e = Bool true in
 let notIsFalse e = not (e = Bool false) in
 let l = List.filter notIsFalse l in
 match l with
 | [] -> Bool false
 | [Bool true] -> Bool true
 | _   ->
  let nb = List.fold_left (fun acc e -> if isTrue e then acc + 1 else acc) 0 l in
  if nb > 1 then Bool false
  else ExistsUniq l
  

let sunion e1 e2 =
 match e1, e2 with
 | Set s1 , Set s2 -> Set (VTSet.union s1 s2)
 | Set s , x | x , Set s when VTSet.is_empty s -> x
 |  _   , _ -> Union(e1,e2)

let sinter e1 e2 =
 match e1, e2 with
 | Set s1 , Set s2 -> Set (VTSet.inter s1 s2)
 | Set s , _ | _ , Set s when VTSet.is_empty s -> Set VTSet.empty
 |  _   , _ ->   Inter(e1,e2)

let scond e1 e2 =
 match e1 with
 | Bool true  -> e2
 | Bool false -> Set VTSet.empty
 |  _         -> Cond(e1,e2)
  
  
let mkempty = function
 | Set s -> if VTSet.is_empty s then Bool true else Bool false
 |   e   -> IsEmpty e

let sbvar bv v =
 match bv v with
 | Bool b -> Bool b
 | _   -> BVar v

let ssvar sv v =
 match sv v with
 | Set s -> Set s
 | _   -> SVar v

let rec simplify_bexpr bvar svar e =
 let rec simplify_bexpr_rec  = function
  | BVar v -> sbvar bvar v
  | Bool b -> Bool b
  | And(e1,e2) -> mkand (simplify_bexpr_rec e1) (simplify_bexpr_rec e2)
  | Or(e1,e2) -> mkor (simplify_bexpr_rec e1) (simplify_bexpr_rec e2)  
  | Implies(e1,e2) -> mkimpl (simplify_bexpr_rec e1) (simplify_bexpr_rec e2)
  | ExistsUniq l   -> mkuniq (List.map simplify_bexpr_rec l)
  | IsEmpty e      -> mkempty (simplify_sexpr bvar svar e) in
 simplify_bexpr_rec e
 
and simplify_sexpr bvar svar e =
 let rec simplify_sexpr_rec = function
 | SVar v -> ssvar svar v
 | Set s  -> Set s
 | Union(e1,e2) -> sunion (simplify_sexpr_rec e1) (simplify_sexpr_rec e2)
 | Inter(e1,e2) -> sinter (simplify_sexpr_rec e1) (simplify_sexpr_rec e2)
 | Cond(b,e2) -> scond (simplify_bexpr bvar svar b) (simplify_sexpr_rec e2) in
 simplify_sexpr_rec e

let rec eq_bexpr e1 e2= 
 match e1,e2 with
  | BVar v , BVar v'                  -> v = v'
  | Bool b , Bool b'                  -> b = b'
  | And(e1,e2), And(e1',e2')
  | Or(e1,e2), Or(e1',e2')            -> eq_bexpr e1 e1' && eq_bexpr e2 e2'
  | Implies(e1,e2) , Implies(e1',e2') -> eq_bexpr e1 e1' && eq_bexpr e2 e2'
  | ExistsUniq l , ExistsUniq l'      ->
   begin try List.for_all2 eq_bexpr l l' with _ -> false end
  | IsEmpty e , IsEmpty e'      -> eq_sexpr e e'
  |   _       , _   -> false
and eq_sexpr e e'=
 match e , e' with
 | SVar v , SVar v' -> v = v'
 | Set s  , Set s'  -> VTSet.equal s s'
 | Union(e1,e2) , Union(e1',e2')
 | Inter(e1,e2) , Inter(e1',e2') ->  eq_sexpr e1 e1' && eq_sexpr e2 e2'
 | Cond(b,e2) , Cond(b',e2') -> eq_bexpr b b' && eq_sexpr e2 e2'
 |   _  ,  _ -> false

  
let output_var o (s,vn) =   
Printf.fprintf o "%s(%s)" s (vn_to_string vn)

let output_vt_set o s = 
 Printf.fprintf o "{";
 VTSet.iter (fun vt -> Printf.fprintf o " %a" output_vt vt) s;
 Printf.fprintf o " }"

 
let rec output_bexpr o = function
 | BVar v        -> output_var o v
 | Bool b        -> Printf.fprintf o "%b" b
 | And(e,e')     -> Printf.fprintf o "(%a) /\\ (%a)" output_bexpr e output_bexpr e'
 | Or(e,e')      -> Printf.fprintf o "(%a) \\/ (%a)" output_bexpr e output_bexpr e'
 | Implies(e,e') -> Printf.fprintf o "(%a) => (%a)" output_bexpr e output_bexpr e'
 | IsEmpty(s)    -> Printf.fprintf o "IsEmpty(%a)" output_sexpr s
 | ExistsUniq l  ->
  let output_list o l =
   List.iter (fun x -> Printf.fprintf o " %a" output_bexpr  x) l in 
  Printf.fprintf o "ExistsUniq(%a)" output_list l 
and  output_sexpr o = function
 | SVar v -> output_var o v
 | Set s  -> output_vt_set o s
 | Union(e1,e2) -> Printf.fprintf o "(%a) \\/ (%a)" output_sexpr e1 output_sexpr e2
 | Inter(e1,e2) -> Printf.fprintf o "(%a) /\\ (%a)" output_sexpr e1 output_sexpr e2
 | Cond(c,e)    -> Printf.fprintf o "(%a) => (%a)" output_bexpr c output_sexpr e

  
    
let var_of_equation = function
 | BEq(v,_) | SEq(v,_) -> v

let check_system l =
  ignore (List.fold_left
           (fun vs eq ->
            let v = var_of_equation eq in
            if VarSet.mem v vs 
            then raise (MultipleDefinition v)
            else (VarSet.add v vs)) VarSet.empty l)


let findb m x =
 try VarMap.find x m
 with Not_found -> raise (Undefined x)

let finds m x =
 try VarMap.find x m
 with Not_found -> raise (Undefined x)


   
let update mb ms e =
 let eval_bexpr = eval_bexpr (findb mb) (finds ms) in
 let eval_sexpr = eval_sexpr (findb mb) (finds ms) in

 match e with
 | BEq(v,be) -> (VarMap.add v (eval_bexpr be) mb,ms)
 | SEq(v,se) -> (mb, VarMap.add v (eval_sexpr se) ms)
   
let init (mb,ms) e = 
 match e with
 | BEq(v,_) -> (VarMap.add v false mb,ms)
 | SEq(v,_) -> (mb, VarMap.add v VTSet.empty ms)

type solution = (var -> bool)* (var -> VTSet.t)
 
let solve l =
 check_system l;
  
 let rec rec_solve (mb,ms) =
  let (mb',ms') = List.fold_left (fun (mb,ms) eq -> update mb ms eq) (mb,ms) l in
  if VarMap.equal (=) mb mb' && VarMap.equal VTSet.equal ms ms'
  then ((fun v -> VarMap.find v mb'),(fun v-> VarMap.find v ms'))
  else rec_solve (mb',ms') in
 
 let init = (List.fold_left (fun acc eq -> init acc eq) (VarMap.empty,VarMap.empty) l) in
 
 rec_solve init

  
let get_bool_var v (mb,_) = VarMap.find v mb

let get_set_var v (_,ms) = VarMap.find v ms
 
let output_equation  o = function
 | BEq(v,e) -> Printf.fprintf o "%a = %a" output_var v output_bexpr e
 | SEq(v,e) -> Printf.fprintf o "%a = %a" output_var v output_sexpr  e

let output_sys o l =
 List.iter (fun eq -> Printf.fprintf o "%a\n" (output_equation)  eq) l

let simplify_equation bv sv = function
 | BEq(v,e) -> BEq(v,simplify_bexpr bv sv e)
 | SEq(v,e) -> SEq(v,simplify_sexpr bv sv e)

let rec expr_of_bvar l v  =
 match l with
 | [] -> BVar v
 | BEq(v',e)::l when (v = v') -> e
 | _::l -> expr_of_bvar l v

let rec expr_of_svar l v =
 match l with
 | [] -> SVar v
 | SEq(v',e)::l when (v = v') -> e
 | _::l -> expr_of_svar l v

let eq_equation eq eq' =
 match eq , eq' with
 | BEq(v,e) , BEq(v',e') -> v = v' && eq_bexpr e e'
 | SEq(v,e) , SEq(v',e') -> v = v' && eq_sexpr e e'
 |  _ ,  _ -> false
  
let simplify_sys n sys =
 let rec simpl_rec n s = 
  if n = 0 then s
  else
   let fb = expr_of_bvar s  in
   let fs = expr_of_svar s in
   let s' = List.map (simplify_equation fb fs) s in
   if List.for_all2 eq_equation s s'
   then s else simpl_rec (n-1) s' in
 simpl_rec n sys
  
let make_bool_system : grammar -> string -> (grammar -> vn -> bexpr) -> equation list =
 fun g str f ->
  VNSet.fold (fun nt eqs -> (BEq((str,nt),f g nt)) :: eqs) (nterminals g) []

let make_set_system : grammar -> string -> (grammar -> vn -> sexpr) -> equation list =
 fun g str f ->
  VNSet.fold (fun nt eqs -> (SEq((str,nt),f g nt)) :: eqs) (nterminals g) []

let rec verify_assertion : solution -> bexpr ->  bexpr option =
 fun s e -> 
  match e with
  | And(e1,e2) ->
   begin
    match verify_assertion s e1 with
    | None -> verify_assertion s e2
    | Some e -> Some e
   end
  |   _ -> 
   if eval_bexpr (fst s) (snd s) e
   then None
   else Some e

let output_solution o s l =
   
 let output_sol_eq  = function
  | BEq(v,_) -> Printf.fprintf o "%a = %b\n" output_var v (fst s v)
  | SEq(v,_) -> Printf.fprintf o "%a = %a\n" output_var v output_vt_set (snd s v) in
 List.iter (output_sol_eq) l

 

    
