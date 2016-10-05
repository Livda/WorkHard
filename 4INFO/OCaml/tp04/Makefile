all: lexer.mll parser.mly ast.ml value.ml print_expr.ml print_val.ml eval.ml main.ml
	ocamlbuild -yaccflag -v main.native

clean:
	rm -rf _build main.native
