(ns between-sets
  (:require [clojure.set :as s]))



(defn get-total-x [as bs]
  (letfn [(factor? [a b] (zero? (mod b a)))]
    (->> (range (apply max as) (inc (apply min bs)))
      (filter (fn [x]
                (and (every? #(factor? % x) as)
                  (every? #(factor? x %) bs))))
      count)))


(defn gcd [x y]
  (if (zero? y) x (recur y (mod x y))))

(defn lcm [x y]
  (/ (Math/abs (* x y))
    (gcd x y)))

;; credit to https://www.hackerrank.com/challenges/between-two-sets/forum/comments/214974
(defn get-total-x' [as bs]
  (let [bs-gcd (reduce gcd bs)
        as-lcm (reduce lcm as)]
    (->> (iterate #(+ as-lcm %) as-lcm)
      (take-while #(<= % bs-gcd))
      (filter #(zero? (mod bs-gcd %)))
      count
      )))
