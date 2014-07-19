(defproject cheatsheet-reagent "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2173"]
                 [reagent "0.4.2"]
                 [org.clojure/core.match "0.2.1"]
                 [instar "1.0.4"]
                 [fipp "0.4.3"]
                 [org.codehaus.jsr166-mirror/jsr166y "1.7.0"]
                 [cljs-ajax "0.2.6"]
                 [ring/ring-jetty-adapter "1.1.6"]
                 [compojure "1.1.8"]
                 ]

  :plugins [[lein-cljsbuild "1.0.2"]]
  :hooks [leiningen.cljsbuild]

  :prep-tasks [["run" "generate-edn"]]

  :profiles {:dev {:dependencies [[midje "1.5.0"]]}
             :prod {:cljsbuild
                    {:builds
                     {:client {:compiler
                               {:optimizations :simple ; valid options: :advanced, :simple
                                :preamble ^:replace ["reagent/react.min.js"]
                                :pretty-print false}}}}}
             :srcmap {:cljsbuild
                      {:builds
                       {:client {:compiler
                                 {:source-map "resources/client.js.map"
                                  :source-map-path "client"}}}}}}
  :source-paths ["src"]
  :cljsbuild {:builds {:client {:source-paths ["src"]
                                :compiler
                                {:preamble ["reagent/react.js"]
                                 :output-dir "resources/client"
                                 :output-to "resources/client.js"
                                 ;:source-map "resources/client.js.map"
                                 :pretty-print true}}}}
;  :main ^:skip-aot cheatsheet-reagent.parse
  :main ^:skip-aot cheatsheet-reagent.core
  )
