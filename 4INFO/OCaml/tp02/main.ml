open Grammar
open Equation

let output_vt_set o s = 
 Printf.fprintf o "{";
 VTSet.iter (fun vt -> Printf.fprintf o " %a" output_vt vt) s;
 Printf.fprintf o " }"


module type LexerI = 
 sig
  type ulex

  val token : Lexing.lexbuf -> ulex

  val terminal_of_ulex : grammar -> ulex -> vt

  val is_eof : ulex -> bool
  (** [is_eof ul] Return true iff ul is the token representing the end of file.
      Invariant: is_eof ul -> terminal_of_ulex g ul = end_of_stream g *)

  val string_of_token : ulex -> string

 end

module MyLexer : LexerI = 
struct

 type ulex = () (* Replace with Ulex.ulex of TP1 *)

 let token = 
  fun _ -> failwith "Main.MyLexer.token : use Lexer.token of TP1"

 let string_of_token  = 
  fun _ -> failwith "Main.MyLexer.string_of_token : adapt Ulex.print_token of TP1"

 let terminal_of_ulex = 
  fun _ _ -> failwith "Main.MyLexer.terminal_of_ulex : TODO"
   
 let is_eof = 
  function _ -> failwith "Main.MyLexer.is_eof : TODO"

end

let scanner lexbuf =
  
  let rec scanner_rec n l = 
    try 
     let tk = MyLexer.token lexbuf in
     if MyLexer.is_eof tk
     then (tk::l)
     else scanner_rec (n+1) (tk :: l) 
    with x -> 
     begin
      Printf.printf "Warning : exception %s was raised after reading %i tokens\n" 
       (Printexc.to_string x) n;
      l
     end
  in
  List.rev (scanner_rec 0 []);;


let grammar_file = ref "" 
let simplify = ref 0
 
let args = [
 ("-grammar",Arg.Set_string grammar_file,"<file> : Analyse the grammar specification from <file> ");
 ("-simplify", Arg.Set_int simplify,"perform n iterations of simplifications before printing equations (-1 means until no simplifications)")
]

let usage_msg = "usage:"

let check_arg () = 
 match !grammar_file with
 | "" -> Arg.usage args usage_msg ; exit 1
 | _  -> ()

let _ =
 Arg.parse args (fun _ -> ()) usage_msg ; 
 check_arg ();
 let g  = Grammar.from_file !grammar_file in
 (** Check accessibility *)
 let sol_acc = solve (LL1.acc_sys g) in
 begin
  match verify_assertion sol_acc (LL1.check_acc g) with
  | None -> ()
  | Some v -> Printf.fprintf stdout "Non terminal %a is not accessible\n" output_bexpr v ; exit 1
 end ;
 (** Check generative *)
 let sol_gen = solve (LL1.gen_sys g) in
 begin
  match verify_assertion sol_gen (LL1.check_gen g) with
  | None -> ()
  | Some v -> Printf.fprintf stdout "Non terminal %a is not generative\n" output_bexpr v ; exit 1
 end;
 try
  begin
  Printf.fprintf stdout "Grammar is well-formed\n\n";
  let null_sys = LL1.null_sys g in
  let first_sys = LL1.first_sys g in
  let follow_sys = LL1.follow_sys g  in
  (* Show system of equations *)
  let all_sys = simplify_sys !simplify (null_sys @ first_sys @ follow_sys) in
  Printf.fprintf stdout "Equations\n";
  output_sys  stdout all_sys ; 
  Printf.fprintf stdout "\nSolutions\n";
  let sol     = solve all_sys in
  output_solution stdout  sol all_sys ; 
  Printf.fprintf stdout "\n";
  flush stdout ; 
  match verify_assertion sol (LL1.is_ll1 g) with
  | Some e ->
   begin
    Printf.fprintf stdout "The grammar is not LL1\n" ;
    Printf.fprintf stdout "Assertion not verified %a\n" output_bexpr e
   end
  | None -> Printf.fprintf stdout "The grammar is LL1\n"; 
   try
    Printf.fprintf stdout "Input a string:\n";
    flush stdout;
    let lexbuf = Lexing.from_channel stdin  in
    let stream = scanner lexbuf in 
    let tree   = (Engine.parse g (MyLexer.terminal_of_ulex g)  stream) in 
    Engine.print_parse_tree MyLexer.string_of_token tree
   with exn -> Printf.fprintf stdout "%s\n" (Printexc.to_string exn)
  end 
 with Undefined(s,vt) ->
  Printf.fprintf stdout "Undefined(%s,%s) \n" s (vn_to_string vt);  
