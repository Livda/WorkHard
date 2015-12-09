module StrCmp =
struct
 type t = string
 let compare = Pervasives.compare
end

module StrSet = Set.Make(StrCmp)

include StrSet

let print s = StrSet.iter (fun str  -> Printf.printf "%s " str) s

