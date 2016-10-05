open Grammar
open Equation
 
val acc_sys : grammar -> equation list

val check_acc : grammar -> bexpr
 
val gen_sys : grammar -> equation list

val check_gen : grammar -> bexpr
 
val null_sys : grammar -> equation list

val null : grammar -> vn -> bool
 
val first_sys : grammar -> equation list

val follow_sys : grammar -> equation list

val is_ll1 : grammar -> bexpr

val deriv : grammar -> vn -> vt -> (v list) option

