open Ast

type value =
| Val_int of int
| Val_bool of bool
| Val_pair of value * value
| Val_nil
| Val_cons of value * value
| Val_closure of (string * value) list * (ml_pattern * ml_expr) list
