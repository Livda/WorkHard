(** [token] is the type of the different lexical units. *)
type token = UL_UNIT
	     | UL_EGAL
	     | UL_OR
	     | UL_AND
	     | UL_DIFF
	     | UL_SUP
	     | UL_INF
	     | UL_PAROUV
	     | UL_PARFER
	     | UL_IDENT of string
	     | UL_COM of string
	     | UL_NB of int
             | UL_EOF 

(** [is_eof] : token  -> bool
    is_eof tk returns true if the lexical unit represents the end_of file.
*)
let is_eof = function
  | UL_EOF -> true
  | _      -> false

(** [print_token] : out_channel -> token -> unit
    print_token o tk prints on the output channel o the textual representation of the token tk *)
let print_token o = function
  | UL_UNIT -> Printf.fprintf o "()"
  | UL_EGAL -> Printf.fprintf o "="
  | UL_OR -> Printf.fprintf o "ou"
  | UL_AND -> Printf.fprintf o "et"
  | UL_DIFF -> Printf.fprintf o "<>"
  | UL_INF -> Printf.fprintf o "<"
  | UL_SUP -> Printf.fprintf o ">"
  | UL_PAROUV -> Printf.fprintf o "("
  | UL_PARFER -> Printf.fprintf o ")"
  | UL_IDENT(s) -> Printf.fprintf o "IDENT: %s" s
  | UL_NB(n) -> Printf.fprintf o "Nombre: %d" n
  | UL_COM(s) -> Printf.fprintf o "Commentaire : %s" s
  | UL_EOF  -> Printf.fprintf o "eof"

