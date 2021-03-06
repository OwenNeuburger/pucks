(ns pucks.agents.zigzag
  (:use [pucks globals util vec2D]
        pucks.agents.active))

(def leftmost (/ pi 4))

(def rightmost (* 3 (/ pi 4)))

(defn zigzag-proposals [p]
  (let [switch-to-right (<= (:rotation p) leftmost)
        switch-to-left (>= (:rotation p) rightmost)
        direction (if (or switch-to-right 
                          (and (= (:turning (:memory p)) :right)
                               (not switch-to-left)))
                    :right
                    :left)]
    {:acceleration 1
     :rotation (if (= direction :left)
                 leftmost
                 rightmost)
     :memory (merge (:memory p)
                    {:turning direction})}))

(defn zigzag []
  (merge-agents (active)
                {:agent-types #{zigzag}
                 :proposal-function zigzag-proposals
                 :color [255 0 255]
                 :memory {:turning :left}}))