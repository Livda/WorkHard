open Ast

let print_pattern fmt pattern =
  let rec print_pattern = function
    | Ml_pattern_var id -> Format.pp_print_string fmt id
    | Ml_pattern_bool b -> Format.pp_print_string fmt (string_of_bool b)
    | Ml_pattern_int n -> Format.pp_print_string fmt (string_of_int n)
    | Ml_pattern_pair(p1, p2) ->
      Format.pp_print_string fmt "(";
      print_pattern p1;
      Format.pp_print_string fmt ", ";
      print_pattern p2;
      Format.pp_print_string fmt ")"
    | Ml_pattern_nil -> Format.pp_print_string fmt "[]"
    | Ml_pattern_cons(p1, p2) ->
      print_pattern p1;
      Format.pp_print_string fmt "::";
      print_pattern p2
  in
  print_pattern pattern

let print_unop fmt = function
  | Ml_fst -> Format.pp_print_string fmt "fst"
  | Ml_snd -> Format.pp_print_string fmt "snd"

let print_binop fmt = function
  | Ml_add -> Format.pp_print_string fmt "+"
  | Ml_sub -> Format.pp_print_string fmt "-"
  | Ml_mult -> Format.pp_print_string fmt "*"
  | Ml_eq -> Format.pp_print_string fmt "="
  | Ml_less -> Format.pp_print_string fmt "<"

let make_prefix prefix n = prefix ^ String.make n ' '

let print_expr expr =
  let fmt = Format.std_formatter in
  let rec print_let_letrec prefix s id e1 e2 =
    Format.pp_print_string fmt (prefix ^ s ^ " " ^ id ^ " = ");
    let prefix' = make_prefix prefix 1 in
    (match e1 with
    | Ml_fun _ | Ml_if _ | Ml_let _ | Ml_letrec _ ->
      Format.pp_force_newline fmt ();
      Format.pp_print_string fmt prefix';
      print_expr prefix' e1;
      Format.pp_force_newline fmt ();
      Format.pp_print_string fmt (prefix ^ "in");
    | _ ->
      print_expr prefix e1;
      Format.pp_print_string fmt (prefix ^ " in"));
    Format.pp_force_newline fmt ();
    print_expr prefix e2
  and print_expr prefix = function
    | Ml_int n -> Format.pp_print_string fmt (string_of_int n)
    | Ml_bool b -> Format.pp_print_string fmt (string_of_bool b)
    | Ml_nil -> Format.pp_print_string fmt "[]"
    | Ml_cons(e1, e2) ->
      print_expr prefix e1;
      Format.pp_print_string fmt "::";
      print_expr prefix e2
    | Ml_pair(e1, e2) ->
      Format.pp_print_string fmt "(";
      print_expr prefix e1;
      Format.pp_print_string fmt ", ";
      print_expr prefix e2;
      Format.pp_print_string fmt ")"
    | Ml_unop(op, e) ->
      Format.pp_print_string fmt "(";
      print_unop fmt op;
      Format.pp_print_string fmt " ";
      print_expr prefix e;
      Format.pp_print_string fmt ")"
    | Ml_binop(op, e1, e2) ->
      Format.pp_print_string fmt "(";
      print_expr prefix e1;
      print_binop fmt op;
      print_expr prefix e2;
      Format.pp_print_string fmt ")"
    | Ml_var id -> Format.pp_print_string fmt id
    | Ml_if(c, e1, e2) ->
      Format.pp_print_string fmt "if ";
      print_expr prefix c;
      Format.pp_print_string fmt " then ";
      print_expr prefix e1;
      Format.pp_print_string fmt " else ";
      print_expr prefix e2;
    | Ml_fun((pattern, e) :: tl) ->
      Format.pp_print_string fmt "(function ";
      print_pattern fmt pattern;
      Format.pp_print_string fmt " -> ";
      let prefix' = make_prefix prefix 1 in
      print_expr prefix' e;
      if tl <> [] then Format.pp_force_newline fmt ();
      let rec print_tl = function
	| [] -> ()
	| (pattern, e) :: tl ->
	  Format.pp_print_string fmt (prefix' ^ "| ");
	  print_pattern fmt pattern;
	  Format.pp_print_string fmt " -> ";
	  print_expr prefix' e;
	  if tl <> [] then Format.pp_force_newline fmt ();
	  print_tl tl
      in
      print_tl tl;
      Format.pp_print_string fmt ")";
    | Ml_fun [] -> failwith "function must have at least one pattern"
    | Ml_app(e1, e2) ->
      Format.pp_print_string fmt "(";
      print_expr prefix e1;
      Format.pp_print_string fmt " ";
      print_expr prefix e2;
      Format.pp_print_string fmt ")"
    | Ml_let(id, e1, e2) -> print_let_letrec prefix "let" id e1 e2
    | Ml_letrec(id, e1, e2) -> print_let_letrec prefix "let rec" id e1 e2
  in
  print_expr "" expr;
  Format.print_flush ()
