
(ns dango-stack.pp-under-care.orchestrator
  (:require 
   [dango-stack.pp-under-care.db :refer [get-pp-under-care]]))

(defn pp-under-care-orchestrator [username] 
    (get-pp-under-care username))
