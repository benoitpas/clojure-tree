(ns clojure-tree.core-test
  (:require [clojure.test :refer :all]
            [clojure-tree.core :refer :all]))

(deftest test-addId
  (testing "empty tree"
    (is (= (addId '() 0) '())))

  (testing "tree with one node"
    (is (= (addId '("a") 0) 
    '((("a" 0)) 1)
    ))
  )

  (testing "tree with two nodes"
    (is (= 
      (addId '("a" ("b")) 0) 
      '(( ("a" 0) (("b" 1))) 2)
    ))
  )

  (testing "tree with three nodes"
    (is (= 
      (addId '("a" ("b") ("c")) 0) 
      '(( ("a" 0) (("b" 1)) (("c" 2))) 3)
    ))
  )
)

(deftest test-addId-nary
  (testing "empty tree"
    (is (= (addId-nary '() 0) '())))

  (testing "tree with one node"
    (is (= (addId-nary '("a") 0) '((("a" 0)) 1))))

  (testing "tree with two nodes"
    (is (= 
      (addId-nary '("a" ("b")) 0) 
      '(( ("a" 0) (("b" 1))) 2))
    )
  )

  (testing "tree with three nodes"
    (is (= 
      (addId-nary '("a" ("b") ("c")) 0) 
      '(( ("a" 0) (("c" 2)) (("b" 1))) 3))
    )
  )
)




