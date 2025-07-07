(ns dango-stack.util.jwt
  (:require [buddy.sign.jwt :as jwt]
            [dango-stack.config :refer [jwt-secret]]))

(defn generate-jwt-token [email]
  (jwt/sign {:email email} (jwt-secret)))