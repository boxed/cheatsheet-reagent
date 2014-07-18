(ns cheatsheet-reagent.core-test
  (:use midje.sweet)
  (:require [cheatsheet-reagent.parse :refer :all]
            [clojure.core.match :refer [match]]
            ))

(fact
  (parse-box [:box "green"
              :section "Documentation"
              :table [["clojure.repl/"
                       :cmds '[clojure.repl/doc clojure.repl/find-doc
                               clojure.repl/apropos clojure.repl/source
                               clojure.repl/pst clojure.java.javadoc/javadoc
                               "(foo.bar/ is namespace for later syms)"]]]
              ]) => ""

 )
