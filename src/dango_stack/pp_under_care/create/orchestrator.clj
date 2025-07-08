(ns dango-stack.pp-under-care.create.orchestrator
  (:require [dango-stack.pp-under-care.create.db :refer [insert-cared-person]] 
            [dango-stack.pp-under-care.create.validator :refer [validate-person-details!]]))

(defn create-pp-under-care-orchestrator [person-details]
  (validate-person-details! person-details)
  (insert-cared-person person-details))

