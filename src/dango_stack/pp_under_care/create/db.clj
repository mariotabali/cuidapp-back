(ns dango-stack.pp-under-care.create.db
  (:require [clojure.java.jdbc :as jdbc]
            [dango-stack.config :refer [get-db-config]]) 
  (:import (java.sql Date)))

(defn insert-cared-person [person-details]
  (let [{:keys [name dob gender address carer-id]} person-details
        db-config (get-db-config)
        java-date-from-iso (Date/valueOf dob)
        insert-query "INSERT INTO cared_people (name, date_of_birth, gender, address, carer_id)
                       VALUES (?, ?, ?, ?, ?) RETURNING id, created_at"
        result (jdbc/query db-config [insert-query name java-date-from-iso gender address carer-id])]
    (first result)))
