{
  open Ulex (* Ulex contains the type definition of lexical units *)
}

rule token = parse 
|[' ' '\n' '\t'] { token lexbuf }
|"=" {UL_EGAL}
  |"ou" {UL_OR}
  |"et" {UL_AND}
  |"<>" {UL_DIFF}
  |"<" {UL_INF}
  |">" {UL_SUP}
  |"(" {UL_PAROUV}
  |")" {UL_PARFER}
  |['A'-'Z''a'-'z'] (['A'-'Z''a'-'z'] | ['0'-'9'])* as id  {UL_IDENT id }
  |['0'-'9']+ as nb {UL_NB (int_of_string nb)}
  |"/*" ('*'+[^'*' '/'] | [^'*'])* '*'+'/' as id {UL_COM id}
  | ['0']   { UL_UNIT}
  | eof     { UL_EOF }

(* rule token = parse "/*" {comment lexbuf}

and comment = parse
|"*/" {token lexbuf}
|_ {comment lexbuf} *)
