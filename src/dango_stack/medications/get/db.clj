(ns dango-stack.medications.get.db
  (:require [dango-stack.config :refer [get-db-config]]
            [clojure.java.jdbc :as jdbc]))

(defn get-medications [cared-person-id]
  (let [db-config (get-db-config)
        query "SELECT id, name, dosage, frequency, instructions, is_current, created_at FROM medications WHERE cared_person_id = ?"]
    (jdbc/query db-config [query cared-person-id])))

