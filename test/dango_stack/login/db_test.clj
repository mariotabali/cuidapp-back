(ns dango-stack.login.db-test
  (:require [clojure.test :refer :all]
            [dango-stack.login.db :refer :all]
            [clojure.java.jdbc :as jdbc]
            [bcrypt-clj.auth :refer [crypt-password]]
            [dango-stack.config :as config]))

(deftest test-check-user-credentials
  (with-redefs [config/get-db-config (fn [] {:dbtype "h2" :dbname "mem:"})
                jdbc/query (fn [_ _]
                             [{:password (crypt-password "correct-password")}])]
    (testing "Valid credentials"
      (is (true? (check-user-credentials "test@example.com" "correct-password"))))

    (testing "Invalid password" 
      (is (nil? (check-user-credentials "test@example.com" "wrong-password")))) 

    (testing "Non-existent user"
      (with-redefs [jdbc/query (fn [_ _] nil)]
        (is (nil? (check-user-credentials "non-existent@example.com" "password")))))))
