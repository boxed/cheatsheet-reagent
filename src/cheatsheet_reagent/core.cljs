(ns cheatsheet-reagent.core
  (:require [reagent.core :as reagent :refer [atom]]
            [ajax.core :refer [GET POST]]
            [instar.core :refer [transform]]))

(def cheatsheet (atom nil))

(def state (atom {:search-text ""}))

(defn section-component [cmd]
  (if (and (:text cmd) (:url cmd))
    [:a.section {:href (:url cmd)} (:text cmd)]
    [:span.section (:text cmd)])
  [:div.hidden])

(defn show-cmd? [cmd]
  (if (nil? cmd)
    false
    (or (= (:search-text @state) "") (not= (.indexOf (.toLowerCase (:text cmd)) (:search-text @state)) -1))))

(defn show-row? [row]
  (let [{title :title cmds :cmds} row]
    (not= 0 (count (filter show-cmd? cmds)))))

(defn show-section? [section]
  (let [[_ table] section]
    (not= 0 (count (filter show-row? table)))))

(defn show-box? [box]
  (not= 0 (count (filter show-section? box))))

(defn cmd-component [cmd]
  (if (:url cmd)
    [:a.cmd {:href (:url cmd)} (:text cmd)]
    [:span.cmd (:text cmd)]))

(defn cmds-component [cmds]
  [:div
    (doall (for [cmd (doall (filter show-cmd? cmds))]
      ^{:key (hash cmd)} [cmd-component cmd]))])

(defn box-component [box]
  [:div.box
   (doall (for [[[title subtitle cmds-one-line] table] (doall (filter show-section? box))]
    ^{:key (hash table)} [:div.section
     [:h2.title [section-component title]]
     [:h3.subtitle [section-component subtitle]]
     [cmds-component cmds-one-line]
     (doall (for [{title :title cmds :cmds} (doall (filter show-row? table))]
       ^{:key (hash cmds)} [:div.row
        [:h4 (:text title)]
        [cmds-component cmds]]))
     ]))
  ])

(defn root []
  (let [cc @cheatsheet]
    [:div.top
     [:input {:onChange #(swap! state assoc :search-text (-> %1 .-target .-value .toLowerCase))}]
     [:h1 (:title cc)]
     (doall (for [box (doall (filter show-box? (:boxes cc)))]
       ^{:key (hash box)} [box-component box]))
     ]))

(defn ^:export run []
  (GET "cheatsheet.edn" {:handler #(reset! cheatsheet %)})
  (reagent/render-component [root]
                            (.-body js/document)))
