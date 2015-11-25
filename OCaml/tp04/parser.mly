%{
  open Ast
%}

%token <int> INT
%token <bool> BOOL
%token LET REC IN
%token EOF END_OF_EXPRESSION
%token IF THEN ELSE
%token FUNCTION
%token LEFT_PAREN RIGHT_PAREN
%token COMMA
%token NIL
%token CONS
%token PLUS MINUS MUL EQUAL INF
%token FIRST SECOND
%token <string> IDENT
%right ELSE
%nonassoc IN
%left EQUAL
%nonassoc INF
%left PLUS MINUS
%left MUL
%right CONS
%right FIRST SECOND
%start main
%type <Ast.ml_expr> main
%%

main:
 | EOF { Printf.printf "\nbye"; exit 0 }
 | expr END_OF_EXPRESSION { $1 }
 | error {
    let bol = (Parsing.symbol_start_pos ()).Lexing.pos_bol in
    failwith ("parsing: line " ^
		 (string_of_int ((Parsing.symbol_start_pos ()).Lexing.pos_lnum)) ^
		 " between character " ^
		 (string_of_int (Parsing.symbol_start () - bol)) ^
		 " and " ^
		 (string_of_int ((Parsing.symbol_end ()) + 1 - bol)))
 }

expr:
| LET IDENT EQUAL expr IN expr { Ml_let($2, $4, $6) }
| LET REC IDENT EQUAL expr IN expr { Ml_letrec($3, $5, $7) }
| simple_expr                            { $1 }
| LEFT_PAREN expr RIGHT_PAREN            { $2 }
| expr CONS expr                         { Ml_cons ($1, $3) }
| LEFT_PAREN expr COMMA expr RIGHT_PAREN { Ml_pair($2, $4) }
| FIRST expr                             { Ml_unop(Ml_fst, $2) }
| SECOND expr                            { Ml_unop(Ml_snd, $2) }
| expr PLUS expr                         { Ml_binop(Ml_add, $1, $3) }
| expr MINUS expr                        { Ml_binop(Ml_sub, $1, $3) }
| expr MUL expr                          { Ml_binop(Ml_mult, $1, $3) }
| expr EQUAL expr                        { Ml_binop(Ml_eq, $1, $3) }
| expr INF expr                          { Ml_binop(Ml_less, $1, $3) }
| IF expr THEN expr ELSE expr            { Ml_if($2, $4, $6)}
| application                            { List.fold_left (fun res a -> Ml_app(res, a)) (List.hd $1) (List.tl $1) }

simple_expr:
| INT   { Ml_int $1 }
| BOOL  { Ml_bool $1 }
| NIL   { Ml_nil }
| IDENT { Ml_var $1 }

application:
 | simple_expr_or_parenthesized_expr simple_expr_or_parenthesized_expr application_next { $1 :: $2 :: $3 }

simple_expr_or_parenthesized_expr:
 | simple_expr                            { $1 }
 | LEFT_PAREN expr COMMA expr RIGHT_PAREN { Ml_pair($2, $4) }
 | LEFT_PAREN expr RIGHT_PAREN            { $2 }

application_next:
 | simple_expr_or_parenthesized_expr application_next { $1 :: $2 }
 | { [] }
