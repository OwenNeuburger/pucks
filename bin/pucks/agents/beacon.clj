(ns pucks.agents.beacon
    (:use quil.core 
        [pucks globals util vec2D]
        pucks.agents.generic))

(defn draw-beacon [p]
  (let [[x y] (:position p)
        radius (:radius p)
        [r g b] (:color p)]
    (push-matrix)
    (translate x y)
    ;; membrane
    (fill r g b 32)
    (ellipse 0 0 (* radius 2) (* radius 2))
    ;; core
    (ellipse 0 0 radius radius)
    (fill 0 0 0)
    (text-align :center)
    (text (str (:id p)) 0 0)
    (pop-matrix)))

(defn beacon-proposals [p]
  {})

;; beacon-radius + sensor-range = neighborhood-size
;; => beacon-radius = neighborhood-size - sensor-range

(defn beacon []
  (merge-agents (generic)
                {:agent-types #{:beacon}
                 :solid false
                 :color [255 255 128]
                 :radius (- (:neighborhood-size @parameters) (:sensor-range @parameters))
                 :draw-functions [draw-beacon]
                 :proposal-function beacon-proposals
                 :id (gensym "beacon-")
                 :position (rand-xy)}))