(ns dango-stack.routes
  (:require [compojure.core :refer [GET POST defroutes]]
            [compojure.route :as route]
            [dango-stack.util :refer [json-response]] 
            [dango-stack.login.orchestrator :refer [login-orchestrator]] 
            [dango-stack.pp-under-care.orchestrator :refer [pp-under-care-orchestrator]]
            [dango-stack.pp-under-care.create.orchestrator :refer [create-pp-under-care-orchestrator]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [dango-stack.middleware.auth :refer [wrap-authentication]]
            ))

(defroutes app-routes
  (POST "/api/login" req
    (let [email-password (:body req)]
      (comment {:input "login information valid email and a non empty password"}
               {:sample_input {:email "john@gmail.com" :password "ilikebananas"}}
               {:output "an object having a status and a jwt token if successful"}
               {:sample_output {:status 200
                                :JWT_TOKEN "asdasdasdasd"}}
               {:description "Receives email and password, validates that email is valid, selects email from user table and compares hashes if true return 200 and the JWT_TOKEN"})
      (json-response(login-orchestrator email-password)))
    ) 
  (GET "/api/pp-under-care" req
     (json-response (pp-under-care-orchestrator req))) 
  (POST "/api/pp-under-care" req 
     (let [body (:body req)] 
       (json-response (create-pp-under-care-orchestrator body)))
       )
  (route/not-found
   (json-response {:error "404 not found"})))

(def app
  (-> app-routes
      wrap-authentication
      (wrap-json-body {:keywords? true})
      wrap-json-response)) 