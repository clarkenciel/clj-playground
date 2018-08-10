(ns grading-students
  (:require [clojure.java.io :as io]))


(defn nearest-five [grade]
  (let [increased (+ grade 5)]
    (- increased
      (mod increased 5))))


(defn roundable? [grade]
  (and (>= grade 38)
    (> 3 (- (nearest-five grade) grade))))


(defn round [grade]
  (if (roundable? grade)
    (nearest-five grade)
    grade))


(defn pipe-grades [xform in-seq out-stream]
  (doseq [grade (sequence xform in-seq)]
    (.write out-stream (.getBytes (str grade "\n") "UTF-8"))))


(defn perform-grading []
  (let [output-file-name (get (System/getenv) "OUTPUT_PATH")
        input-stream (drop 1 (line-seq (io/reader *in*)))] ; drop the first line as we don't care
    (with-open [output-file (io/output-stream output-file-name)]
      (pipe-grades
        (comp (map #(Integer/parseInt %)) (map round))
        input-stream output-file))))


(defn test-grading
  "a li'l test of the functionality that will generate
  a rather large file of test numbers and then run the rounding on them"
  [junk-file results-file]

  (letfn [(random-int [limit]
            (int (rand limit)))
          (grade-line []
            (str (random-int 100) "\n"))
          (line-bytes [] (.getBytes (grade-line)))]

    (with-open [out-stream (io/output-stream junk-file)]
      (dotimes [_ 100000]
        (.write out-stream (line-bytes))))

    (with-open [in-stream (io/input-stream junk-file)
                out-stream (io/output-stream results-file)]
      (pipe-grades
        (comp (map #(Integer/parseInt %)) (map round))
        (line-seq (io/reader in-stream))
        out-stream)))
  )

;; uncomment and execute line below for a test
;; (test-grading "junk.txt" "big-test.txt")
