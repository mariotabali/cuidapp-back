(ns dango-stack.medications.create.db
  (:require [clojure.java.jdbc :as jdbc]
            [dango-stack.config :refer [get-db-config]]))

(defn create-medication [medication]
  (let [db-config (get-db-config)
        insert-query "INSERT INTO medications (cared_person_id, name, dosage, frequency, instructions, is_current) VALUES (?, ?, ?, ?, ?, ?) RETURNING id"
        params [(get medication :cared_person_id)
                (get medication :name)
                (get medication :dosage)
                (get medication :frequency)
                (get medication :instructions)
                (get medication :is_current true)]]
    (jdbc/with-db-transaction [t-con db-config]
      (jdbc/query t-con (into [insert-query] params)))))
