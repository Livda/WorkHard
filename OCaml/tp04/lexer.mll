{
  open Parser
  let keyword_table = Hashtbl.create 53
  let _ =
    List.iter (fun (kwd, tok) -> Hashtbl.add keyword_table kwd tok)
      [
  "let"     , LET ;
  "rec"     , REC ;
  "in"      , IN ;
  "true"    , BOOL true ;
  "false"   , BOOL false ;
  "fst"     , FIRST ;
  "snd"     , SECOND ;
  "if"      , IF ;
  "then"    , THEN ;
  "else"    , ELSE ;
  "function", FUNCTION
      ]
}

let space = [' ' '\t']

let letter = ['A'-'Z' 'a'-'z' '_']

let digit = ['0'-'9']

rule get_token = parse
  | "//" [^'\n']* '\n'? { get_token lexbuf }
  | "/*" ([^'*']|('*'+[^'*''/']))* '*'+ '/' { get_token lexbuf}
  | ['\n' '\r'] { Lexing.new_line lexbuf; get_token lexbuf }
  | ','         { COMMA }
  | '('         { LEFT_PAREN }
  | ')'         { RIGHT_PAREN }
  | ";;"        { END_OF_EXPRESSION }
  | space       { get_token lexbuf }
  | digit+ as v { INT (int_of_string v) }
  | eof         { EOF }
  | "[]"        { NIL }
  | "::"        { CONS }
  | '+'         { PLUS }
  | '-'         { MINUS }
  | '*'         { MUL }
  | '='         { EQUAL }
  | '<'         { INF }
  | letter+ as i{ try
      Hashtbl.find keyword_table i with
      |Not_found -> IDENT i }
