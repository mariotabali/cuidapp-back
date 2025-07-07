(ns dango-stack.util.response)

(defn unauthorized-response []
  {:status 401 :error "Unauthorized "})

(defn success-response [status data]
  (assoc data :status status))