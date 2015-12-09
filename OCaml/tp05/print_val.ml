open Value

let print_val value =
  let fmt = Format.std_formatter in
  let rec print_val = function
    | Val_int n -> Format.pp_print_string fmt (string_of_int n)
    | Val_bool b -> Format.pp_print_string fmt (string_of_bool b)
    | Val_nil -> Format.pp_print_string fmt "[]"
    | Val_cons(v1, v2) ->
      print_val v1;
      Format.pp_print_string fmt "::";
      print_val v2
    | Val_pair(v1, v2) ->
      Format.pp_print_string fmt "(";
      print_val v1;
      Format.pp_print_string fmt ", ";
      print_val v2;
      Format.pp_print_string fmt ")"
    | Val_closure _ -> Format.pp_print_string fmt "<fun>"
  in
  print_val value;
  Format.print_flush ()
