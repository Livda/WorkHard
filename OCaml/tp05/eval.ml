open Ast
open Value

exception Error of string

let eval_unop op arg =
  match arg with
  | Val_pair(v1, v2) -> begin
    match op with
    | Ml_fst -> v1
    | Ml_snd -> v2
  end
  | _ -> raise (Error "wrong type in eval_unop")

let rec eval_eq arg1 arg2 =
  match (arg1, arg2) with
  | (Val_int m, Val_int n) -> m = n
  | (Val_nil, Val_nil) -> true
  | (Val_nil, Val_cons _) | (Val_cons _, Val_nil) -> false
  | (Val_pair(v1, v2), Val_pair(v3, v4)) -> eval_eq v1 v3 && eval_eq v2 v4
  | (Val_cons(v1, v2), Val_cons(v3, v4)) -> eval_eq v1 v3 && eval_eq v2 v4
  | _ -> raise (Error "wrong type in equality")

and eval_binop op arg1 arg2 =
  match (arg1, arg2) with
  | (Val_int m, Val_int n) -> begin
    match op with
    | Ml_add -> Val_int (m + n)
    | Ml_sub -> Val_int (m - n)
    | Ml_mult -> Val_int (m * n)
    | Ml_eq -> Val_bool (m = n)
    | Ml_less -> Val_bool (m < n)
  end
  | _ ->
    match op with
    | Ml_eq -> Val_bool(eval_eq arg1 arg2)
    | _ -> raise (Error "wrong type in eval_binop")

let rec pattern_matching pattern value =
  match (pattern, value) with
  | (Ml_pattern_var(id,_), v) -> [(id, v)]
  | (Ml_pattern_bool b1, Val_bool b2) -> if b1 = b2 then [] else raise (Error "pattern matching failure")
  | (Ml_pattern_int n1, Val_int n2) -> if n1 = n2 then [] else raise (Error "pattern matching failure")
  | (Ml_pattern_nil _, Val_nil) -> []
  | (Ml_pattern_cons(m1, m2), Val_cons(v1, v2)) -> pattern_matching m1 v1 @ pattern_matching m2 v2
  | (Ml_pattern_pair(m1, m2), Val_pair(v1, v2)) -> pattern_matching m1 v1 @ pattern_matching m2 v2
  | _ -> raise (Error "pattern matching failure")

let rec tryfind f = function
  | [] -> raise Not_found
  | hd :: tl -> try f hd with _ -> tryfind f tl

let rec eval_expr env = function
  | Ml_int n -> Val_int n
  | Ml_bool b -> Val_bool b
  | Ml_nil _ -> Val_nil
  | Ml_cons(e1, e2) -> Val_cons(eval_expr env e1, eval_expr env e2)
  | Ml_pair(e1, e2) -> Val_pair(eval_expr env e1, eval_expr env e2)
  | Ml_unop(op, e) -> eval_unop op (eval_expr env e)
  | Ml_binop(op, e1, e2) -> eval_binop op (eval_expr env e1) (eval_expr env e2)
  | Ml_var x -> begin
    try
      List.assoc x env
    with Not_found -> raise (Error ("unbound variable " ^ x))
  end
  | Ml_if(c, e1, e2) -> begin
    match eval_expr env c with
    | Val_bool true -> eval_expr env e1
    | Val_bool false -> eval_expr env e2
    | _ -> raise (Error "wrong type in if")
  end
  | Ml_fun pattern_expr_list -> Val_closure(env, pattern_expr_list)
  | Ml_app(e1, e2) -> begin
    let val_arg = eval_expr env e2 in
    match eval_expr env e1 with
    | Val_closure(env', pattern_expr_list) -> begin
      try
	let (pattern_matching_env, e) =
	  tryfind (fun (pattern, e) -> (pattern_matching pattern val_arg, e)) pattern_expr_list
	in
	eval_expr (pattern_matching_env @ env') e
      with Not_found -> raise (Error "no match in pattern matching")
    end
    | _ -> raise (Error "wrong type in application")
  end
  | Ml_let(x, e1, e2) -> let v = eval_expr env e1 in eval_expr ((x, v) :: env) e2
  | Ml_letrec(f, _, e1, e2) -> begin
    match e1 with
    | Ml_fun pattern_expr_list -> let rec env' = (f, Val_closure(env', pattern_expr_list)) :: env in eval_expr env' e2
    | _ -> raise (Error "illegal recursive definition")
  end

let eval env = function
  | Ml_definition(id, expr) -> begin
    let v = eval_expr !env expr in
    env := (id, v) :: !env;
    None
  end
  | Ml_definitionrec(f, _, expr) -> begin
    match expr with
    | Ml_fun pattern_expr_list ->
      let rec env' = (f, Val_closure(env', pattern_expr_list)) :: !env in
      env := env';
      None
    | _ -> raise (Error "illegal recursive definition")
  end
  | Ml_expr expr -> Some(eval_expr !env expr)
