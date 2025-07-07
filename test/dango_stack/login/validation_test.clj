(ns dango-stack.login.validation-test
  (:require [clojure.test :refer :all]
            [dango-stack.login.validation :refer [validate-login-input]]))

(deftest validate-login-input-test
  (testing "Valid email and password"
    (is (= {} (validate-login-input {:email "juanperez@gmail.com" :password "securePassword"}))))

  (testing "Invalid email format"
    (is (= {:error "Invalid email format "} (validate-login-input {:email "juanperez[at]gmail.com" :password "securePassword"}))))

  (testing "Empty password"
    (is (= {:error "Password cannot be empty "} (validate-login-input {:email "juanperez@gmail.com" :password ""}))))

  (testing "Nil email"
    (is (= {:error "Invalid email format "} (validate-login-input {:email nil :password "securePassword"}))))

  (testing "Nil password"
    (is (= {:error "Password cannot be empty "} (validate-login-input {:email "juanperez@gmail.com" :password nil}))))
)