(ns dango-stack.pp-under-care.db
  (:require [clojure.java.jdbc :as jdbc]
            [environ.core :refer [env]]))

(def db-spec {:dbtype   "postgresql"
              :dbname   (env :db-name)
              :host     (env :db-host)
              :port     (env :db-port)
              :user     (env :db-user)
              :password (env :db-password)})

(defn get-pp-under-care [username]
  (jdbc/query db-spec
              ["SELECT name, age FROM cared_people WHERE user_id = (SELECT id FROM users WHERE email = ?)" username]))
