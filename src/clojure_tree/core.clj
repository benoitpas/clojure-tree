(ns clojure-tree.core
  (:gen-class))

(require '[clojure.core.reducers :as r])

(defn addId
  "Add unique id to nodes in a binary tree"
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

(defn addId-nary
  "Add unique id to nodes in an n-ary tree"
  [tree i]
  (case (count tree)
  0 tree
  1 (list (list (list (first tree) i)) (+ i 1))
    (let [
      nValue (list (first tree) i)
      branches (rest tree)
      reducef (fn
        ([] (list (+ i 1)))
        ([a tree]
          (let [
            nTree (addId-nary tree (first a))
          ] (cons (first (rest nTree)) (cons (first nTree) (rest a)))
          )
        )
      )
      n-branches (r/fold reducef (rest tree))
    ] (list (cons nValue (rest n-branches)) (first n-branches)))
  )
 )

 (defn addId-hm
  "Add unique id to nodes in a tree store in a hash-map"
  [tree i]
  (if-not tree (list '() i)
    (let [
      nValue (list (tree :value) i) ;; we do not handle missing :value
      left  (addId-hm (tree :left) (+ i 1))
      n-left (first left)
      i2 (first (rest left))
      right  (addId-hm (tree :right) i2)
      n-right (first right)
      i3 (first (rest right))
      r {:value nValue}
      r2 (if (empty? n-left) r (assoc r :left n-left))
      r3 (if (empty? n-right) r2 (assoc r2 :right n-right))
    ] (list r3 i3))
  ))

(defn -main
  "Test addId"
  [& args]
  (println "Using a list to store the tree")
  (def tree '("a" ("b") ("c" ("d") ("e"))))
  (println tree)
  (println (addId tree 1))
  (println (addId-nary tree 1))

  (println "Using a hash-map to store the tree")
  (def hm-tree {:value "a" :left {:value "b"} :right {:value "c" :left {:value "d"} :right {:value "e"}}})
  (println hm-tree)
  (println (addId-hm hm-tree 1))
)