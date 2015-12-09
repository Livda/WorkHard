open Ast

module CmpStr =
 struct
  type t = string
  let compare = Pervasives.compare
 end

module StrMap = Map.Make(CmpStr)

type t = Ast.typ StrMap.t

let empty = StrMap.empty

let singleton = StrMap.singleton


exception TypeMismatch of string * typ * typ

let add str typ env =
 try
  let typ' = StrMap.find str env in
  raise (TypeMismatch(str,typ,typ'))
 with Not_found -> StrMap.add str typ env

let update =  StrMap.add

let find = StrMap.find

let add_all  =  StrMap.fold add

let update_all = StrMap.fold update


