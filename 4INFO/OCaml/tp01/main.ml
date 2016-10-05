open Ulex 

(** scanner : lexbuf -> token list 
    scanner lexbuf constructs the list of token read from the argument lexbuf
    (ends when reading UL_EOF )
*)
let scanner lexbuf = 
  
  let rec scanner_rec n l = 
    try 
      match Lexer.token lexbuf with
      | UL_EOF as tk ->  (tk::l)
      |   tk   -> scanner_rec (n+1) (tk :: l) 
  with x -> 
    begin
    Printf.printf "Warning : exception %s was raised after reading %i tokens\n" 
      (Printexc.to_string x) n;
      l
    end
  in
    List.rev (scanner_rec 0 [])



let _ =
  (** 1. Construct a lex buffer from the standard input channel *)
  let lexbuf = Lexing.from_channel stdin in
  
  (** 2. Construct the list of tokens *)
  let tokens = scanner lexbuf in

  (** 3. Print the tokens *)
  List.iter (fun tk -> Printf.fprintf stdout "Token: %a\n" Ulex.print_token tk) tokens ;
  Printf.printf "DONE\n"

