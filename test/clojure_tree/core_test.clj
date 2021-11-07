(ns clojure-tree.core-test
  (:require [clojure.test :refer :all]
            [clojure-tree.core :refer :all]))

(deftest test-tree
  (testing "empty tree"
    (is (= (addId '() 0) '())))

  (testing "tree with one node"
    (is (= (addId '("a") 0) '((("a" 0)) 1))))

  (testing "tree with two nodes"
    (is (= 
      (addId '("a" ("b")) 0) 
      '(( ("a" 0) (("b" 1))) 2))
    )
  )

  (testing "tree with three nodes"
    (is (= 
      (addId '("a" ("b") ("c")) 0) 
      '(( ("a" 0) (("b" 1)) (("c" 2))) 3))
    )
  )

)




