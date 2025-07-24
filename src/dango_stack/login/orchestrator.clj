(ns dango-stack.login.orchestrator
  (:require [dango-stack.login.validation :refer [validate-login-input]]
            [dango-stack.login.db :refer [check-user-credentials]]
            [dango-stack.util.jwt :refer [generate-jwt-token]]
            [dango-stack.util.response :refer [unauthorized-response success-response]]))

(defn login-orchestrator [email-password]
  (let [validation-errors (validate-login-input email-password)]
    (if (empty? validation-errors)
      (let [{:keys [email password]} email-password
            user-authenticated (check-user-credentials email password)]
        (if user-authenticated
          (success-response 200 {
                                 :JWT_TOKEN (generate-jwt-token email)
                                 :id (:id user_authenticated)})
          (unauthorized-response)))
      {:status 400 :errors validation-errors})))