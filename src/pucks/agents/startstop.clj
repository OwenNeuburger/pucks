(ns pucks.agents.startstop
  (:use [pucks globals util vec2D]
        pucks.agents.active))

(defn startstop-proposals [p]
  {:acceleration (if (< (mod (:steps p) 50) 25)
                   1
                  (* 0.25 (- (length (:velocity p)))))}) ;; if you don't scale the reverse acceleration you can get oscillations

(defn startstop []
  (merge-agents (active)
                {:agent-types #{:startstop}
                 :proposal-function startstop-proposals
                 :color [96 255 96]}))