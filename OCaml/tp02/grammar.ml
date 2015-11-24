(* The types vt and vn are strings but this is not exported in the interface. *)
type vt = string 
type vn = string 

let output_vt = output_string
let output_vn = output_string

type v =   
| Vt of vt
| Vn of vn

type production = vn * v list 


module VTSet = Set.Make(struct type t = vt let compare = Pervasives.compare end)
module VNSet = VTSet (* In the implementation, VTSet and VNSet are the same. *) 

module VTMap = Map.Make(struct type t = vn let compare = Pervasives.compare end)
module VNMap = VTMap

module ProdSet = Set.Make(struct type t = v list let compare = Pervasives.compare end)

type grammar = {
 axiom         : vn;
 terminals     : VTSet.t;
 non_terminals : VNSet.t;
 productions   : ProdSet.t VNMap.t;
}

let vt_equal : string -> string -> bool = (=)
let vn_equal : string -> string -> bool = (=)

let string_to_vt s = s 

let vt_to_string s = s

let vn_to_string s = s

let axiom g = g.axiom

let end_of_stream g = "#"

let terminals g = g.terminals

let nterminals g = g.non_terminals

let production g vn = 
 try 
   ProdSet.elements (VNMap.find vn g.productions)
 with
   Not_found -> [] (* this non-terminal has no production *)

let is_vn : vn -> v -> bool = 
 fun vn v -> 
  match v with
  | Vt _ -> false
  | Vn vn' -> vn_equal vn vn'


let check_production vnt v (vn,l) =
 (if not (VTSet.mem vn vnt)
  then failwith (Printf.sprintf "%s -> ... : %s should be a non-terminal" vn vn)
 );
 try
  let v = List.find (fun s -> not (VTSet.mem s v)) l in
  failwith (Printf.sprintf "%s -> ...%s... : %s should be declared" vn v v)
 with Not_found -> ()

let from_lexbuf lexbuf = 
 let (vt,vn,ax,prod)  = GrammarParser.main GrammarLexer.token lexbuf in 
 let terms = List.fold_left (fun acc e -> VTSet.add e acc) VTSet.empty vt in 
 let nterms = List.fold_left (fun acc e -> VNSet.add e acc) VNSet.empty vn in 
 let idents = VTSet.union terms nterms in

 let add_prod prods (vn,l) =
  check_production nterms idents (vn,l);
  let ps = try VNMap.find vn prods  with Not_found -> ProdSet.empty in 
  let l  = List.map (fun v -> if VNSet.mem v terms then Vt v else Vn v) l in
  VNMap.add vn (ProdSet.add l ps) prods in

 (* A string cannot be used as both a terminal and non-terminal *)
 let inter  = (VTSet.inter terms nterms) in
 (
  if not (VTSet.is_empty inter)
  then failwith (Printf.sprintf "Identifier %s cannot be used as both a terminal and non-terminal" (VTSet.choose inter))
 );
 (* The axiom is a non-terminal *)
 (
 if not (VTSet.mem ax nterms)
 then failwith (Printf.sprintf "The axiom %s should be a non-terminal" ax)
 );
 
 let prods = List.fold_left add_prod VNMap.empty prod in
 {
  axiom = ax ; 
  terminals = terms;
  non_terminals = nterms ; 
  productions = prods ; 
 }

let from_file file = 
 let ch = open_in file in
 let lexbuf = Lexing.from_channel ch in
  try 
  from_lexbuf lexbuf
 with Parsing.Parse_error -> 
  begin
(*   let token = Lexing.lexeme lexbuf in *)
   let pos_s = Lexing.lexeme_start_p lexbuf in
   let pos_e = Lexing.lexeme_end_p lexbuf in 
  Printf.printf "File \"%s\", line %i, character %i-%i\n" file  
   pos_s.Lexing.pos_lnum 
   (pos_s.Lexing.pos_cnum -  pos_s.Lexing.pos_bol) 
   (pos_e.Lexing.pos_cnum -  pos_e.Lexing.pos_bol) ;
  raise Parsing.Parse_error
  end



(** [filter_production g f] = { a | vn -> w in P /\ f vn w = Some a } *)

let filter_production : grammar -> (production -> 'a option) -> 'a list = 
 fun g f -> 
 let nt = nterminals g in
 VNSet.fold (fun nt acc -> 
  let prods = production g nt in 
  List.fold_right (fun l acc -> 
   match f (nt, l) with
   | None -> acc
   | Some l' -> l'::acc) prods acc) nt []


let split_proto_phrase : vn -> v list -> (v list *v list) list =
 fun vn l ->
  let rec split acc pre post =
   match post with
   | [] -> acc
   | e::post' -> if is_vn vn e
    then split ((List.rev pre,post')::acc) (e::pre) post'
    else split acc (e::pre) post' in
  split [] [] l

type sprod =
 {
  ntl  : vn;       (** is the non-terminal on the left hand side of the production i.e. Y *)
  wordl : v list;  (** is the word on the left of X i.e w *)
  ntin : vn;       (** is the non-terminal in the middle of the production i.e. X *)
  wordr : v list   (** is the word on the right of X i.e w' *)
 }


(** [prod_of_sprod sp] explain how to reconstructs a production *)
let prod_of_sprod : sprod -> production =
 fun {ntl ; wordl; ntin;wordr} -> (ntl, wordl@(Vn ntin)::wordr)
   
let split_prod : vn -> production -> sprod list =
 fun vn (vn',l) ->
  List.map (fun (w,w') -> {ntl = vn'; wordl = w ; ntin = vn ; wordr = w'}) (split_proto_phrase vn l)

   
let gather_sprod_using_vn : grammar -> vn -> sprod list =
 fun g vn -> 
 List.concat (filter_production g (fun p -> Some (split_prod vn p)))


 

 




