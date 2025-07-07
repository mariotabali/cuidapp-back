
(ns dango-stack.pp-under-care.orchestrator
  (:require [dango-stack.util.jwt :refer [decode-jwt]]
            [dango-stack.pp-under-care.db :refer [get-pp-under-care]]))

(defn pp-under-care-orchestrator [req]
  (let [jwt-token (get-in req [:headers "authorization"])
        username (:email (decode-jwt jwt-token))]
    (println username)
    (get-pp-under-care username)))
