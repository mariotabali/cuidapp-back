(ns dango-stack.activate-account.db
  (:require [dango-stack.config :refer [get-db-config]]
            [clojure.java.jdbc :as jdbc]))
            
 (defn activate-user [email token]
   (let [db-config (get-db-config)
         query "UPDATE user_activations SET activated_at = NOW ()
                WHERE email = ? AND token = ? AND activated_at IS NULL RETURNING id "]
     (not-empty (jdbc/query db-config [query email token]))))