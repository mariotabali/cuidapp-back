(ns dango-stack.login.db
  (:require [clojure.java.jdbc :as jdbc]
            [dango-stack.config :refer [get-db-config]] 
            [bcrypt-clj.auth :refer [check-password]]
            ))

(defn check-user-credentials [email submitted-password]
  (let [db-config (get-db-config)
        user-query "SELECT password FROM users WHERE email = ?"
        user (first (jdbc/query db-config [user-query email]))]
    (when user
      (when (check-password submitted-password (:password user))
        true)
      )))