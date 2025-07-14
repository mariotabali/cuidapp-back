(ns dango-stack.util.jwt
  (:require [buddy.sign.jwt :as jwt]
            [dango-stack.config :refer [jwt-secret]]))

(defn generate-jwt-token [email]
  (jwt/sign 
    {:email email
    :jti (str (java.util.UUID/randomUUID)) 
    :iat (quot (System/currentTimeMillis) 1000)}
   (jwt-secret)))

(defn decode-jwt [token]
  (jwt/unsign token (jwt-secret)))
