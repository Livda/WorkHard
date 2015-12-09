{
  open Parser
  let keyword_table = Hashtbl.create 53
  let _ =
    List.iter (fun (kwd, tok) -> Hashtbl.add keyword_table kwd tok)
      [
	"let", LET;
	"in", IN;
	"function", FUNCTION;
	"if", IF;
	"then", THEN;
	"else", ELSE;
	"rec", REC;
	"true", TRUE;
	"false", FALSE;
	"fst", FST;
	"snd", SND;
      ]
}

let space = [' ' '\t']

let letter = ['A'-'Z' 'a'-'z' '_']

let digit = ['0'-'9']

rule get_token = parse
  | "//" [^'\n']* '\n'? { get_token lexbuf }
  | "/*" ([^'*']|('*'+[^'*''/']))* '*'+ '/' { get_token lexbuf}
  | ['\n' '\r'] { Lexing.new_line lexbuf; get_token lexbuf }
  | space { get_token lexbuf }
  | "<"   { LESS }
  | '+'   { ADD }
  | '*'   { MULT }
  | '-'   { SUB }
  | '='   { EQUAL }
  | '('   { LEFT_PAREN }
  | ')'   { RIGHT_PAREN }
  | '['   { LEFT_BRACKET }
  | ']'   { RIGHT_BRACKET }
  | "::"  { CONS }
  | ":"   { COLON }
  | "->"  { ARROW }
  | '|'   { ALTERNATIVE }
  | ','   { COMMA }
  | ";;"  { END_OF_EXPRESSION }
  | digit+ as v { INT (int_of_string v) }
  | letter (letter | digit)* as id {
    try
      Hashtbl.find keyword_table id
    with Not_found -> IDENT id
  }
  | eof { EOF }
