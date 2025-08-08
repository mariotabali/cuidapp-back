(ns dango-stack.diagnosis.create.orchestrator
  (:require [dango-stack.diagnosis.create.db :refer [save-diagnosis]]))

(defn diagnosis-orchestrator [diagnosis-data]
  (save-diagnosis diagnosis-data))
