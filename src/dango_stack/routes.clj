(ns dango-stack.routes
  (:require [compojure.core :refer [GET POST defroutes]]
            [compojure.route :as route]
            [dango-stack.util :refer [json-response]] 
            [dango-stack.login.orchestrator :refer [login-orchestrator]]
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

  (route/not-found
   (json-response {:error "404 not found"})))
