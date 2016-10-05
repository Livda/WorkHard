{
    open Parser
    open Lexing
    open Parsing
}

let char  = ['a'-'z' 'A'-'Z']
let nb = ['1'-'9']
let comment = '%' [^'\n']* '\n'


rule token = parse
| [' ' '\t']+     { token lexbuf }     (* skip blanks *)
| '\n'           { Lexing.new_line lexbuf ; token lexbuf }
| comment             { Lexing.new_line lexbuf ; token lexbuf }
| "("                 { LPAR }
| "{"                 { LBR }
| ")"                 { RPAR }
| "}"                 { RBR }
| ","                 { COMMA }
| "begin"             { BEGIN }
| "end"               { END }
| "int"               { ENTIER }
| "bool"              { BOOLEEN }
| ";"                 { PTVIRG }
| "<-"                { AFFECT }
| "<"                 { INF }
| "+"                 { PLUS }
| "and"               { AND }
| nb+ as lxm          { NUMBER lxm }
| char+  as lxm       { IDENT lxm }
| eof                 { EOF }
|  _    as lxm        { let pos = lexeme_start_p lexbuf in
    failwith (
        Printf.sprintf
        "Unknowm token %c at line %i column %i"
        lxm
        pos.pos_lnum
        pos.pos_bol)
}
