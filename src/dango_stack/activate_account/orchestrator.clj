(ns dango-stack.activate-account.orchestrator
  (:require 
   [dango-stack.util.jwt :refer [decode-jwt]]
   [dango-stack.activate-account.db :refer [activate-user]])
  )
 
  (defn activate-account-orchestrator [token]
    (println (some? token))
    (let [{:keys [email]} (decode-jwt token)]
      (if (activate-user email token)
        {:status 200 :message "Account activated successfully."}
        {:status 400 :message "Activation failed. "})))