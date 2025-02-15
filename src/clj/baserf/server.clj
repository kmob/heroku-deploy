(ns baserf.server
  (:require [baserf.handler :refer [handler]]
            [config.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

 ;;(defn -main [& _args]
 ;;  (let [port (Integer/parseInt (or (env :port) "3000"))]
 ;;    (run-jetty handler {:port port :join? false})))

(defn -main [port]
  (run-jetty handler {:port (Integer. port)}))
