(ns between-sets
  (:require [clojure.set :as s]))



(defn get-total-x [as bs]
  (letfn [(factor? [a b] (zero? (mod b a)))]
    (->> (range (apply max as) (inc (apply min bs)))
      (filter (fn [x]
                (and (every? #(factor? % x) as)
                  (every? #(factor? x %) bs))))
      count)))
