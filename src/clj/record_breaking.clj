(ns record-breaking)


(defn calculate-records [[score & scores]]
  (reduce
    (fn [{:keys [min max min-breaks max-breaks] :as records} score]
      (cond
        (< score min)
        (assoc records :min score :min-breaks (inc min-breaks))

        (< max score)
        (assoc records :max score :max-breaks (inc max-breaks))

        :else records))
    {:min score :max score :min-breaks 0 :max-breaks 0}
    scores))


(defn breaking-records [scores]
  (let [{:keys [max-breaks min-breaks]} (calculate-records scores)]
    [max-breaks min-breaks]))
