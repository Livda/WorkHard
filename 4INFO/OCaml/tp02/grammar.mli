type vt (** terminals *)
type vn (** non-terminals *)
type v =   (** terminals or non-terminals *)
| Vt of vt
| Vn of vn
type grammar    

type production = vn * v list

module VTSet : Set.S with type elt = vt 

module VNSet : Set.S with type elt = vn

val string_to_vt : string -> vt

val vt_to_string : vt -> string

val vn_to_string : vn -> string

val output_vn : out_channel -> vn -> unit
(** Output a non-terminal over a channel *)

val output_vt : out_channel -> vt -> unit
(** Output a terminal over a channel *)

val vt_equal : vt -> vt -> bool
(** Return true iff the terminals are equal. *)

val vn_equal : vn -> vn -> bool
(** Return true iff the non-terminals are equal. *)

val axiom    : grammar -> vn
(** Return the axiom of the grammar. *)

val end_of_stream : grammar -> vt

val terminals   : grammar -> VTSet.t
(** Return the set  of terminals of the grammar. *)

val nterminals  : grammar -> VNSet.t
(** Return the set of non-terminals of the grammar. *)

val production  : grammar -> vn -> (v list) list
(** [production g vn] returns { r | vn -> r is a production of g}
    (the set is represented as a list).
*)

(** [sprod] represents a production of the form Y -> w X w' such that *)
type sprod =
 {
  ntl  : vn;       (** is the non-terminal on the left hand side of the production i.e. Y *)
  wordl : v list;  (** is the word on the left of X i.e w *)
  ntin : vn;       (** is the non-terminal in the middle of the production i.e. X *)
  wordr : v list   (** is the word on the right of X i.e w' *)
 }

val gather_sprod_using_vn : grammar -> vn -> sprod list
(** [gather_sprod_using_vn g X] = { Y -> wXw' | Y -> wXw' is a production of the grammar g}
    (the set is represented as a list and the production is decomposed as a [sprod])
    Note that a single production i.e Y -> aXXb can have several decompositions *)

val from_file : string -> grammar
(** [from_file str] parses the file str and returns the corresponding grammar.
    the syntax of grammars is given by the following (informal) grammar:
    S      -> "(" vt_set "," vn_set "," axiom "," production_set ")"
    vt_set -> "{" vt_list "}"
    vn_set -> "{" vn_list "}"
    axiom  -> vn
    vt_set -> vt "," vt_set | vt
    vn_set -> vn "," vn_set | vn
    production_set -> "{" production_list "}"
    production_list -> production "," production_list | production
    production     -> vn "->" vn_vt_list 
    vn_vt_list     -> vn_vt vn_vt_list | 
    vn_vt          -> vn | vt
    vn             -> alphanumeric_string
    vt             -> alphanumeric_string
*)

