{
  open Parser
  let keyword_table = Hashtbl.create 53
  let _ =
    List.iter (fun (kwd, tok) -> Hashtbl.add keyword_table kwd tok)
      [
	"let", LET
      ]
}

let space = [' ' '\t']

let letter = ['A'-'Z' 'a'-'z' '_']

let digit = ['0'-'9']

rule get_token = parse
  | "//" [^'\n']* '\n'? { get_token lexbuf }
  | "/*" ([^'*']|('*'+[^'*''/']))* '*'+ '/' { get_token lexbuf}
  | ['\n' '\r'] { Lexing.new_line lexbuf; get_token lexbuf }
  | ','  { COMMA }
  | '('  { LEFT_PAREN }
  | ')'  { RIGHT_PAREN }
  | ";;" { END_OF_EXPRESSION }
  | space { get_token lexbuf }
  | digit+ as v { INT (int_of_string v) }
  | eof { EOF }
  | "true" as t { BOOL (bool_of_string t) }
  | "false" as f { BOOL (bool_of_string f) }
  | "[]" { NIL }
  | "::" { CONS }
  | '+' { PLUS }
  | '-' { MINUS }
  | '*' { MUL }
  | '=' { EQUAL }
  | '<' { INF }
  | "fst" { FIRST }
  | "snd" { SECOND }
  | letter+ as i { IDENT i }
