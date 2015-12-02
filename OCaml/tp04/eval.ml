open Ast
open Value

exception Error of string

let eval_unop op arg =
  match arg with
  | Val_pair(fst, snd) -> begin
    match op with
    | Ml_fst -> fst
    | Ml_snd -> snd
  end
  | _ -> raise (Error "Argument have to be a pair!")

let eval_binop op arg1 arg2 =
  match arg1, arg2 with
  | Val_int(a1), Val_int(a2) -> begin
    match op with
    | Ml_add  -> Val_int(a1 + a2)
    | Ml_sub  -> Val_int(a1 - a2)
    | Ml_mult -> Val_int(a1 * a2)
    | Ml_eq   -> Val_bool(a1 = a2)
    | Ml_less -> Val_bool(a1 < a2)
  end
  | a1, a2 -> begin
    match op with
    | Ml_eq -> Val_bool(a1 = a2)
    | _     -> raise (Error "Wrong operator!")
    end

let rec pattern_matching pattern value =
  match pattern, value with
  | Ml_pattern_nil, Val_nil -> []
  | Ml_pattern_var id, v -> [(id, v)]
  | Ml_pattern_int i1, Val_int i2 -> if i1=i2 then [] else raise (Error "Pattern didn't match!")
  | Ml_pattern_bool b1, Val_bool b2 -> if b1=b2 then [] else raise (Error "Pattern didn't match!")
  | Ml_pattern_cons(e1, e2), Val_cons(v1, v2) -> pattern_matching e1 v1 @ pattern_matching e2 v2
  | Ml_pattern_pair(e1, e2), Val_pair(v1, v2) -> pattern_matching e1 v1 @ pattern_matching e2 v2
  | _ -> raise (Error "Pattern didn't match!")

let rec tryfind f = function
  | [] -> raise Not_found
  | hd :: tl -> try f hd with _ -> tryfind f tl

let rec eval env = function
  | Ml_int n                -> Val_int n
  | Ml_bool b               -> Val_bool b
  | Ml_nil                  -> Val_nil
  | Ml_cons(hd, tl)         -> Val_cons(eval env hd, eval env tl)
  | Ml_pair(fst, snd)       -> Val_pair(eval env fst, eval env snd)
  | Ml_unop(op, exp)        -> eval_unop op (eval env exp)
  | Ml_binop(op, exp1, exp2)-> (eval_binop op (eval env exp1) (eval env exp2))
  | Ml_var var              -> List.assoc var env
  | Ml_if(test, exe1, exe2) -> if ((eval env test)=Val_bool true) then (eval env exe1) else (eval env exe2)
   | Ml_fun pattern_expr_list -> Val_closure(env, pattern_expr_list)
  |Ml_let(x, exp1, exp2) -> let v = eval env exp1 in eval ((x, v) :: env) exp2
  |Ml_letrec(f, exp1, exp2) -> begin
    match exp1 with
    | Ml_fun pattern_list -> let rec env2 = (f, Val_closure(env2, pattern_list)) :: env in eval env2 exp2
    | _ -> raise (Error "You try to make an illegal recursive definition!")
  end
  | _                       -> raise (Error "not implemented yet!")
