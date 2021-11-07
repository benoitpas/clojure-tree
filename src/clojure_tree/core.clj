(ns clojure-tree.core
  (:gen-class))

(defn addId
  "Add unique id to nodes in a tree"
  [tree i]
  (case (count tree)
  0 tree
  1 (list (list (list (first tree) i)) (+ i 1))
  2 (let [
          nvalue (list (first tree) i)
          left (addId (first (rest tree)) (+ i 1))
         ]
     (list (list nvalue (first left)) (first (rest left)) ))
  3 (let [
          nvalue (list (first tree) i)
          left (addId (first (rest tree)) (+ i 1))
          right (addId (first (rest (rest tree))) (first (rest left)))
         ]
     (list (list nvalue (first left) (first right)) (first (rest right)) ))
  )
 )

(defn -main
  "Test addId"
  [& args]
  (println "Hello, World!")
  (def tree '("a" ("b") ("c" ("d") ("e"))))
  (println tree)
  (println (addId tree 1))
)