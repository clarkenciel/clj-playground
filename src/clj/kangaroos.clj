(ns kangaroos)

(defn kangaroo [x1 v1 x2 v2]
  (if (and  (< v2 v1)
        (= 0 (mod (- x2 x1) (- v1 v2))))
    "YES"
    "NO"))
