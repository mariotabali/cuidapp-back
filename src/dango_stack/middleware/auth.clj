(ns dango-stack.middleware.auth
  (:require [dango-stack.util.jwt :refer [decode-jwt]]
            [clojure.tools.logging :as log]))

(defn wrap-authentication [handler]
  (fn [req]
    (let [jwt-token (get-in req [:headers "authorization"])]
      (if-let [user-info (decode-jwt jwt-token)] 
        (do
          (log/info "Authenticated request by user:" (:email user-info))
          (handler (assoc req :user user-info)))
        ;; Respond with 401 if token is invalid or missing
        {:status 401 :headers {"Content-Type" "application/json"} :body {:error "Unauthorized"}}))))