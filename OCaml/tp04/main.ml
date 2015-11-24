let loop debug =
  while true do
    let lexbuf = Lexing.from_channel stdin in
    print_string "# ";
    flush stdout;
    try
      let expr = Parser.main Lexer.get_token lexbuf in
      if debug then begin Print_expr.print_expr expr; print_newline () end;
      let v = Eval.eval [] expr in
      print_string "- = ";
      Print_val.print_val v;
      print_newline ();
    with
    | Eval.Error msg -> Printf.printf "error: eval: %s\n" msg
    | Failure msg -> Printf.printf "error: %s\n" msg
  done

let _ = loop (Array.length Sys.argv = 2 && Sys.argv.(1) = "-d")
