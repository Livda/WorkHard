{
 open GrammarParser
 open Lexing

}

let char  = ['0'-'9' 'a'-'z' 'A'-'Z' '_' '\'' '<' '>']
let comment = '%' [^'\n']* '\n'


rule token = parse
| [' ' '\t']+     { token lexbuf }     (* skip blanks *)
| '\n'           { Lexing.new_line lexbuf ; token lexbuf }
| comment             { Lexing.new_line lexbuf ; token lexbuf }
| "->"                { ARROW }
| char+  as lxm       { IDENT lxm }               
| "("                 { LPAR }
| "{"                 { LBR }
| ")"                 { RPAR }
| "}"                 { RBR }
| ","                 { COMMA }
|  _    as lxm        { let pos = lexeme_start_p lexbuf in
                        failwith 
                         (Printf.sprintf
                         "Unknowm token %c at line %i column %i" lxm pos.pos_lnum pos.pos_bol)
                      }
