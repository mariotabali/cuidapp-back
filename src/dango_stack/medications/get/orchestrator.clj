(ns dango-stack.medications.get.orchestrator
  (:require [dango-stack.medications.get.db :refer [get-medications]]))

(defn get-medications-orchestrator [cared-person-id]
  (get-medications cared-person-id))
