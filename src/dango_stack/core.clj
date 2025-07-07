(ns dango-stack.core
  (:require [compojure.core :refer :all]
            [dango-stack.http :as http])
  (:gen-class))

(defn -main [& args] 
  (http/start-server {}))

