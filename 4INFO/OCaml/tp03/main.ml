let _ =
    let channel_in = open_in "grammar" in
    let lexbuf = Lexing.from_channel channel_in in
    let result = Parser.main Lexer.token lexbuf in
        print_string (Type.ast_to_string result lexbuf) ;
        close_in channel_in
