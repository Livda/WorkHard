(* The type for set of strings *)
type t

(* The empty set *)
val empty : t

(* [singleton str] is the singleton set containing the only string [str]. *)
val singleton : string -> t

(* [add str set] adds the string [str] to the set [set]. *)
val add : string -> t -> t

(* [remove str set] removes the string [str] from the set [set]. *)
val remove : string -> t -> t

(* [union s1 s2] returns the union of the set [s1] and [s2]. *)
val union : t -> t -> t

(* [diff s1 s2] returns the set s1 \ s2 i.e., removes the elements of s2 from s1 *)
val diff : t -> t -> t

(* [is_empty set] returns true if the set [set] is empty. *)
val is_empty : t -> bool

(* [print set] prints the content of the set [set]. *)
val print : t -> unit


