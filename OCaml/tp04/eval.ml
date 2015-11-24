open Ast
open Value

exception Error of string

let eval_unop op arg =
  match arg with
  | Ml_pair(fst, snd) -> raise (Error "Uniary operator doesn't exist!")
  | _ -> raise (Error "Have to be a pair!")

let eval_binop op arg1 arg2 = raise (Error "not implemented yet!")

let rec pattern_matching pattern value = raise (Error "not implemented yet!")

let rec tryfind f = function
  | [] -> raise Not_found
  | hd :: tl -> try f hd with _ -> tryfind f tl

let rec eval env = function
  | Ml_int n -> Val_int n
  | Ml_bool b -> Val_bool b
  | Ml_nil -> Val_nil
  | Ml_cons(hd, tl) -> Val_cons(eval env hd, eval env tl)
  | Ml_pair(fst, snd)-> Val_pair(eval env fst, eval env snd)
  | Ml_unop(op, exp) -> eval_unop op exp
  | Ml_binop(op, exp1, exp2) -> eval_binop op exp1 exp2
  | Ml_var var -> List.assoc var env
  | _ -> raise (Error "not implemented yet!")
