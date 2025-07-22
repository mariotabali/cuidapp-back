(ns dango-stack.activate-account.db
  (:require [dango-stack.config :refer [get-db-config]]
            [clojure.java.jdbc :as jdbc]))

(defn activate-user [email token]
  (let [db-config (get-db-config)
        activation-query "UPDATE user_activations
                          SET activated_at = NOW()
                          WHERE email = ? AND token = ? AND activated_at IS NULL
                          RETURNING email, name, password_hash"]
    (let [result (jdbc/query db-config [activation-query email token])]
      (when (seq result)
        (let [{:keys [email name password_hash]} (first result)
              insert-user-query "INSERT INTO users (email, password) VALUES (?, ?)"]
          (jdbc/execute! db-config [insert-user-query email password_hash])
          {:status :ok :message "Account activated and user created."})))))

