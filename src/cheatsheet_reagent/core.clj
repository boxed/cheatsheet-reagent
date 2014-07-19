(ns cheatsheet-reagent.core
  (:require
   [ring.adapter.jetty :as jetty]
   [compojure.route :as route]
   [compojure.core :refer [defroutes GET POST]]
   [compojure.handler :as handler]
   [ring.util.response :as resp]
   [cheatsheet-reagent.parse :refer [generate-edn]]
   ))


(defroutes app
  (GET "/" [] (resp/file-response "index.html" {:root "resources"}))
  (GET "/cheatsheet.edn" [] (resp/content-type (resp/file-response "cheatsheet.edn" {:root "resources"}) "application/edn"))
  (GET "/client.js" [] (resp/file-response "client.js" {:root "resources"}))
  (GET "/style.css" [] (resp/file-response "style.css" {:root "resources"}))
  (route/not-found "<h1>Page not found</h1>"))

(defn -main [web-port]
  (if (= "generate-edn" web-port)
    (generate-edn)
    (jetty/run-jetty (handler/site app) {:port (Integer. web-port) :join? false})))
