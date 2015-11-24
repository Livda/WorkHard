open Grammar

type var = string * vn

(** Syntax for boolean and set expressions *)  
type bexpr =
| BVar    of var             (** boolean variable *)
| Bool    of bool            (** boolean constant *)
| And     of bexpr * bexpr   (** conjunction *)
| Or      of bexpr * bexpr   (** disjunction *)
| Implies of bexpr * bexpr   (** implication *)
| ExistsUniq of bexpr list   (** ExistsUniq l only holds if a single expression e in l holds *)
| IsEmpty of sexpr           (** the expression represents the empty set *)
and sexpr =
| SVar  of var               (** set variable *)
| Set   of VTSet.t           (** constant set of terminals *)
| Union of sexpr * sexpr     (** set union *)
| Inter of sexpr * sexpr     (** set intersection *)
| Cond  of bexpr * sexpr     (** Cond(b,s) if condition b holds then s else empty set *)
   
(** [eval_bexpr fb fs e] evaluates the expression [e] 
    using [fb] to get the value of boolean variables 
    and   [fs] to get the value of set variables
*)
val eval_bexpr : (var -> bool) -> (var -> VTSet.t) -> bexpr -> bool

 (** [eval_sexpr fb fs e] evaluates the expression [e] 
    using [fb] to get the value of boolean variables 
    and   [fs] to get the value of set variables
*)  
 val eval_sexpr : (var -> bool) -> (var -> VTSet.t) -> sexpr -> VTSet.t

 type equation =
 | BEq of var * bexpr  (** boolean equation *)
 | SEq of var * sexpr  (** set equation *)

(** the solution of a system of [equation] is given by a pair of function.
    The first function gives the values for variables of type bool.
    The second function gives the values for variables of type VTSet.t
*)
 type solution =
  (var -> bool) * (var -> VTSet.t)


 (** [make_bool_system g s f] constructs a system of boolean equations of the form
     [BEq((s,v1),f g v1)....BEq((s,vn),f g vn)] for all the non-terminals vi of the grammar *)
 val make_bool_system : grammar -> string -> (grammar -> vn -> bexpr) -> equation list

 (** [make_set_system g s f] constructs a system of set equations of the form
     [SEq((s,v1),f g v1)....SEq((s,vn),f g vn)] for all the non-terminals vi of the grammar *)
 val make_set_system : grammar -> string -> (grammar -> vn -> sexpr) -> equation list

 (** [solve sys] solves a system of equations and returns the solution. *)
 val solve : equation list -> solution

 (** [verify_assertion sol e] 
     returns None if the evaluation of the boolean expression is true when evaluating the expression with the solution [sol].
     If it returns Some e', e' is a sub-expression of e which explains why the assertion does not hold.
 *)
 val verify_assertion : solution -> bexpr ->  bexpr option

 (** * Other functions *)

 (** [simplify_sys n s] simplifies the system of equations using n iteration steps.
     -1 for as many steps as needed *)
 val simplify_sys : int -> equation list -> equation list

 (**  [output_sys o s] prints the system of equations *)
 val output_sys : out_channel -> equation list -> unit

 (** [output_solution o sol s] prints the solution for all the variables of the system s *)
 val output_solution : out_channel -> solution -> equation list  -> unit

 (** [output_bexpr o e] prints the boolean expression e *)
 val output_bexpr  : out_channel -> bexpr -> unit

(** Exception raised if the system is not well-formed *)
exception MultipleDefinition of var
exception Undefined of var


  
