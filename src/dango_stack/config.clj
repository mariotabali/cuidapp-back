(ns dango-stack.config
  (:require [environ.core :refer [env]]))

(defn get-db-config [] 
  {:classname   "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname     (str "//" (env :db-host) ":" (env :db-port) "/" (env :db-name))
   :user        (env :db-user)
   :password    (env :db-password)})

(defn jwt-secret []
  (env :jwt-secret))