(ns dango-stack.middleware.auth
  (:require [dango-stack.util.jwt :refer [decode-jwt]]
            [clojure.tools.logging :as log]
            [clojure.string :as str]))

(def public-paths #{"/api/login" "/api/register"})

(defn wrap-authentication [handler]
  (fn [req]
    (let [uri (:uri req)]
      (if (contains? public-paths uri)
        (handler req)
        (let [auth-header (get-in req [:headers "authorization"])
          jwt-token   (some-> auth-header (str/replace #"(?i)^Bearer " ""))]
          (println (decode-jwt jwt-token))
          (if-let [user-info (decode-jwt jwt-token)]
            (do
              (log/info "Authenticated request by user:" (:email user-info))
              (handler (assoc req :user user-info)))
            {:status 401
             :headers {"Content-Type" "application/json"}
             :body {:error "Unauthorized"}}))))))
