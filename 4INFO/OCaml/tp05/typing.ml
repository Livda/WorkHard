open Print_ast
open Ast

let is_list_typ = function
 | TList _ -> true
 | _       -> false


let rec typ_of_pattern : ml_pattern -> TypEnv.t * Ast.typ =
 function
 | Ml_pattern_var(s,typ) -> failwith "TODO"
 | Ml_pattern_bool b -> failwith "TODO"
 | Ml_pattern_int i  -> failwith "TODO"
 | Ml_pattern_pair(p1,p2) -> failwith "TODO"
 | Ml_pattern_nil ty -> failwith "TODO"
 | Ml_pattern_cons(x,l) -> failwith "TODO"

let rec wt_expr (env:TypEnv.t) = function
 | Ml_int i -> Tint
 | Ml_bool b -> Tbool
 | Ml_nil ty   -> TList ty
 | Ml_pair(e1,e2) -> TPair ((wt_expr env e1), (wt_expr env e2))
 | Ml_cons(e1,le1) ->
    let tl = TList (wt_expr env le1) in
    let te = wt_expr env e1 in
    (match tl, te with
    | Tbool, Tbool -> tl
    | Tint, Tint -> tl
    | _ -> failwith "List fill with bad type")
 | Ml_unop(op, e) -> (match op, e with
    | Ml_fst, Ml_pair(e1, e2) -> (wt_expr env e1)
    | Ml_snd, Ml_pair(e1, e2) -> (wt_expr env e2))
 | Ml_binop(op,e1,e2) -> failwith "TODO"
 | Ml_var x -> failwith "TODO"
 | Ml_if(e1,e2,e3) -> failwith "TODO"
 | Ml_fun l -> failwith "TODO"
 | Ml_app(e1,e2) -> failwith "TODO"
 | Ml_let (x,e1,e2) -> failwith "TODO"
 | Ml_letrec(x,typ,e1,e2) -> failwith "TODO"

let wt_ast tenv ast =
  match ast with
  | Ml_expr e -> wt_expr (!tenv) e
  | Ml_definition(s,e) ->
   let ty' = wt_expr !tenv e in
   tenv := TypEnv.update s ty'  !tenv ;
   ty'
  | Ml_definitionrec (s,ty',e) ->
   let ty = wt_expr (TypEnv.update s ty' !tenv) e in
   if ty = ty'
   then
    begin
     tenv := TypEnv.update s ty !tenv ;
     ty'
    end
   else failwith (Printf.sprintf "Type error: let rec with incompatible types %s and %s" (string_of_typ ty) (string_of_typ ty'))




