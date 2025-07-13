(ns dango-stack.register.email
  (:require
   [postal.core :refer [send-message]]
   [clj-http.client :as http]  ;;hack
   [cheshire.core :as json] ;;hack
   [environ.core :refer [env]]))

(defn send-activation-email [email token]
  (let [smtp-config {:host     (env :smtp-host)
                     :user     (env :smtp-user)
                     :pass     (env :smtp-pass)
                     :port     (Integer/parseInt (or (env :smtp-port) "587"))
                     :tls      true}
        from (or (env :smtp-from) (env :smtp-user))
        subject "Activa tu cuenta"
        body (str "Bienvenido!\n\nPor favor activa tu cuenta para comenzar:\n\n"
                  "https://tinova.uk/api/activate?token=" token)]
    (send-message smtp-config
                  {:from    from
                   :to      email
                   :subject subject
                   :body    body}))
  )

(defn send-activation-email-mailersend [email token]
  (let [api-token (env :mailersend-token)
        sender-email (or (env :mailersend-sender-email) "noreply@yourdomain.com")
        url "https://api.mailersend.com/v1/email"
        subject "Activate your account"
        activation-url (str "https://tinova.uk/api/activate?token=" token)
        body-text (str "Welcome!\n\nPlease activate your account by visiting the link:\n" activation-url)
        body-html (str "<p>Welcome!</p><p>Please <a href=\"" activation-url "\">activate your account</a>.</p>")
        payload {:from {:email sender-email}
                 :to [{:email email}]
                 :subject subject
                 :text body-text
                 :html body-html}]
    (try
      (let [response (http/post url
                                {:headers {"Authorization" (str "Bearer " api-token)
                                           "Content-Type" "application/json"}
                                 :body (json/generate-string payload)
                                 :throw-exceptions false})]
        (println "MailerSend response:" (:status response) (:body response))
        response)
      (catch Exception e
        (println "Failed to send activation email via MailerSend:" (.getMessage e))
        {:error (.getMessage e)}))))
