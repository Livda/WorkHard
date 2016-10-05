
type typ =
| Tbool
| Tint
| TPair of typ * typ
| TList of typ
| TFun of  typ * typ


type ml_unop = Ml_fst | Ml_snd

type ml_binop = Ml_add | Ml_sub | Ml_mult | Ml_eq | Ml_less

type ml_expr =
| Ml_int of int
| Ml_bool of bool
| Ml_nil of typ
| Ml_cons of ml_expr * ml_expr
| Ml_pair of ml_expr * ml_expr
| Ml_unop of ml_unop * ml_expr
| Ml_binop of ml_binop * ml_expr * ml_expr
| Ml_var of string
| Ml_if of ml_expr * ml_expr * ml_expr
| Ml_fun of (ml_pattern * ml_expr) list
| Ml_app of ml_expr * ml_expr
| Ml_let of string * ml_expr * ml_expr
| Ml_letrec of string * typ * ml_expr * ml_expr

and ml_pattern =
| Ml_pattern_var of string * typ
| Ml_pattern_bool of bool
| Ml_pattern_int of int
| Ml_pattern_pair of ml_pattern * ml_pattern
| Ml_pattern_nil of typ
| Ml_pattern_cons of ml_pattern * ml_pattern

and ml_ast = Ml_expr of ml_expr | Ml_definition of string * ml_expr | Ml_definitionrec of string * typ * ml_expr

