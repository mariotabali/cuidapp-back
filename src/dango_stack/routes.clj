(ns dango-stack.routes
  (:require [compojure.core :refer [GET POST defroutes]]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]] 
            [ring.middleware.params :refer [wrap-params]]
            
            [dango-stack.middleware.auth :refer [wrap-authentication]]
            [dango-stack.util :refer [json-response]] 
            [dango-stack.login.orchestrator :refer [login-orchestrator]] 
            [dango-stack.pp-under-care.orchestrator :refer [pp-under-care-orchestrator]]
            [dango-stack.pp-under-care.create.orchestrator :refer [create-pp-under-care-orchestrator]]
            [dango-stack.register.orchestrator :refer [register-orchestrator]]
            [dango-stack.activate-account.orchestrator :refer [activate-account-orchestrator]]
            [dango-stack.medications.create.orchestrator :refer [create-medication-orchestrator]]
            ))

(defroutes app-routes
  (POST "/api/login" req
    (let [email-password (:body req)]
      (comment {:input "login information valid email and a non empty password"}
               {:sample_input {:email "john@gmail.com" :password "ilikebananas"}}
               {:output "an object having a status and a jwt token if successful"}
               {:sample_output {:status 200
                                :JWT_TOKEN "asdasdasdasd"}}
               {:behaviour "Receives email and password, validates that email is valid, selects email from user table and compares hashes if true return 200 and the JWT_TOKEN"})
      (json-response (login-orchestrator email-password)))
    )
  (POST "/api/register" req
    (let [user-details (:body req)]
      (json-response (register-orchestrator user-details)))) 
  (GET "/api/activate" req
    (let [params (:params req)] 
      (json-response (activate-account-orchestrator params))))
  ;; private routes
  (GET "/api/pp-under-care" req
    (json-response (pp-under-care-orchestrator req)))
  (POST "/api/pp-under-care" req 
    (let [body (:body req)] 
      (json-response (create-pp-under-care-orchestrator body))))
  (POST "/api/medications" req
  (let [medication-data (:body req)]
    (json-response (create-medication-orchestrator medication-data))))
  
  (route/not-found
   (json-response {:error "404 not found"})))

(def app
  (-> app-routes
      (wrap-json-body {:keywords? true})  ;; parses :body
      wrap-json-response                  ;; formats responses
      wrap-authentication
      wrap-params))              ;; now it has access to :headers and :body
