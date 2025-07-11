(ns dango-stack.register.db
  (:require
   [clojure.java.jdbc :as jdbc]
   [dango-stack.config :refer [get-db-config]]))

(defn save-activation-details [email name password-hash token]
  (let [db (get-db-config)
        query "INSERT INTO user_activations 
                 (email, name, password_hash, token, created_at, expires_at)
               VALUES (?, ?, ?, ?, NOW(), NOW() + INTERVAL '1 day')"]
    (jdbc/execute! db [query email name password-hash token])))
