(ns dango-stack.util
  (:require [cheshire.core :as json]
            [ring.util.response :refer [response content-type]]))

(defn json-response [data]
  (-> (response (json/generate-string data))
      (content-type "application/json")))
