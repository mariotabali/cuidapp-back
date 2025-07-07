(ns dango-stack.http
  (:require [cheshire.core :as json]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [dango-stack.routes :refer [app-routes]]
            [ring.util.response :refer [response content-type]]))

(defn json-response [data]
  (-> (response (json/generate-string data))
      (content-type "application/json")))

(defn start-server [_deps]
  (println "Starting server at http://localhost:3000")
  ;; Wrap your app routes with the JSON middleware here
  (jetty/run-jetty (wrap-json-body
                    (wrap-json-response app-routes)
                    {:keywords? true})   ; This converts JSON keys to keywords
                   {:port 3000}))