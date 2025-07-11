(ns dango-stack.register.email
  (:require
   [postal.core :refer [send-message]]
   [environ.core :refer [env]]))

(defn send-activation-email [email token]
  (let [smtp-config {:host     (env :smtp-host)
                     :user     (env :smtp-user)
                     :pass     (env :smtp-pass)
                     :port     (Integer/parseInt (or (env :smtp-port) "587"))
                     :tls      true}
        from (or (env :smtp-from) (env :smtp-user))
        subject "Activate your account"
        body (str "Welcome!\n\nPlease activate your account by visiting the following link:\n\n"
                  "https://yourdomain.com/activate?token=" token)]
    (send-message smtp-config
                  {:from    from
                   :to      email
                   :subject subject
                   :body    body}))
  )
