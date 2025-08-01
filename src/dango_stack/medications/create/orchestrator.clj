(ns dango-stack.medications.create.orchestrator
  (:require [dango-stack.medications.create.db :refer [create-medication]]))

(defn create-medication-orchestrator [medication-data]
  (create-medication medication-data))
