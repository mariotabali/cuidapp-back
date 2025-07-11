(ns dango-stack.register.orchestrator
  (:require
   [dango-stack.util.jwt :refer [generate-jwt-token]]
   [dango-stack.register.email :refer [send-activation-email]]
   [dango-stack.register.db :refer [save-activation-details]]
   [bcrypt-clj.auth :refer [crypt-password]])
  )

(defn register-orchestrator [user-details]
  (let [{:keys [name email password]} user-details
        token (generate-jwt-token email)
        password-hash (crypt-password password)]
    (try 
      (send-activation-email email token)
      (catch Exception e 
        (println (.getMessage e))))
    (save-activation-details email name password-hash token)
    {:status 201}))
