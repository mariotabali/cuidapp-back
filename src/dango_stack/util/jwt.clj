(ns dango-stack.util.jwt
  (:require [buddy.sign.jwt :as jwt]
            [dango-stack.config :refer [jwt-secret]]))

(defn generate-jwt-token [email]
  (jwt/sign {:email email} (jwt-secret)))

(defn decode-jwt [token]
  (jwt/unsign token "huevos-con-aceite"))
