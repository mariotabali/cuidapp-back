(ns dango-stack.route-docs
  (:require [compojure.core :refer [defroutes GET POST ANY]]))

(def route-manifest (atom []))

(defmacro defdocroute
  "Like GET/POST but with docstring. Example:

  (defdocroute GET \"/hello\" \"Returns hello message\" [params]
    (json-response {:msg \"hello\"}))"
  [method path docstring bindings & body]
  `(do
     ;; Register route doc info
     (swap! route-manifest conj
            {:method ~(keyword (clojure.string/lower-case (name method)))
             :path ~path
             :doc ~docstring})
     ;; Expand to normal Compojure route
     (~method ~path ~bindings
              ~@body)))

(defmacro defdocroutes
  "Defines a defroutes with docstring routes inside"
  [& routes]
  `(defroutes app-routes
     ~@routes))

;; Function to get manifest
(defn get-route-manifest []
  @route-manifest)

