(ns dango-stack.diagnosis.create.db
  (:require [dango-stack.config :refer [get-db-config]]
            [clojure.java.jdbc :as jdbc]))

(defn save-diagnosis [diagnosis-data]
  (let [db-config (get-db-config)
        query "INSERT INTO diagnosis (cared_person_id, diagnosis_date, diagnosis, evolution) VALUES (?, ?, ?, ?) RETURNING id"
        params [(:cared_person_id diagnosis-data) (:diagnosis_date diagnosis-data) (:diagnosis diagnosis-data) (:evolution diagnosis-data)]]
    (jdbc/execute! db-config [query params])))
