(ns apples-oranges
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))


(defn read-ints []
  (map #(Integer/parseInt %)
    (s/split (read-line) #"\s")))


(defn run []
  (let [[hs he] (read-ints)
        [apple-tree orange-tree] (read-ints)
        _ (read-line)
        apples (read-ints)
        oranges (read-ints)
        house {:start hs :end he}
        apple-count (->> apples
                      (map #(+ apple-tree %))
                      (filter #(<= hs % he))
                      count)
        orange-count (->> oranges
                       (map #(+ orange-tree %))
                       (filter #(<= hs % he))
                       count)]
    (println apple-count)
    (println orange-count)))

(run)
