open Ast

(* [t] is the type of the typing environments *)
type t

(* [empty] is the empty typing environment. *)
val empty : t

(* [singleton vr typ] returns an environment only binding [vr] to the type [typ] *)
val singleton : string -> typ -> t

exception TypeMismatch of string * typ * typ

(* [add vr ty env] adds the binding (vr:ty) to the typing environment env.
   Raise [TypeMismatch(vr, ty, ty')]
   if  [vr] is already bound with a  type ty' in the environment.
   Note that the exception is raised even if ty = ty'.
*)
val add : string -> typ -> t -> t

(* [update vr ty env] adds the binding (vr:ty) to the typing environment env.
   If there is already a binding, the previous binding is overwritten. *)
val update : string -> typ -> t -> t

(* [find vr env] retrieves the type of [vr] in the environment [env].
   Raise [Not_found] if it does not exist *)
val find : string -> t -> typ

(* [add_all e1 e2] adds all the bindings of e1 to e2.
   Raise [TypeMismatch(vr,ty,ty') if vr is bound to different types in [e1] and [e2] *)
val add_all : t -> t -> t

(* [update_all e1 e2] updates the bindings of e2 with the bindings of e1 *)
val update_all : t -> t -> t


