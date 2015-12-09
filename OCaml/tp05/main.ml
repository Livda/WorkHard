let loop debug =
  let env = ref [] in
  let tenv = ref TypEnv.empty in
  while true do
    let lexbuf = Lexing.from_channel stdin in
    print_string "# ";
    flush stdout;
    try
      let ast,free = Parser.main Lexer.get_token lexbuf in
      if debug then begin
	Print_ast.print_ast ast; print_newline ()
      end;
      let free = List.fold_left (fun free (s,_) -> StrSet.remove s free) free !env in
      if not (StrSet.is_empty free)
      then
       begin
        Printf.printf "Unbound variables :" ;
        StrSet.print free ;
        Printf.printf "\n"
       end
      else
        let val_option = Eval.eval env ast in
        (match val_option with
        | None -> ()
        | Some v ->
	 print_string "- = ";
	 Print_val.print_val v;
        let typ = Typing.wt_ast tenv ast in
         Printf.printf ":%s\n" (Print_ast.string_of_typ typ)
	)
    with
    | TypEnv.TypeMismatch(s,t,t') -> failwith (Printf.sprintf "Type error: variable %s has incompatible types %s and %s" s (Print_ast.string_of_typ t) (Print_ast.string_of_typ t'))
    | Eval.Error msg -> Printf.printf " error: eval: %s\n" msg
    | Failure msg -> Printf.printf " error: %s\n" msg
  done

let _ = loop (Array.length Sys.argv = 2 && Sys.argv.(1) = "-d")
