(ns kangaroos)

(defn kangaroo [x1 v1 x2 v2]
  (let [can-catch-up? (< v2 v1)
        will-sync? (= 0 (mod (- x2 x1) (- v1 v2)))]
    (if (and can-catch-up? will-sync?)
      "YES"
      "NO")))
