(ns dango-stack.login.validation)

(defn validate-login-input [input]
  (let [{:keys [email password]} input] 
    (cond
      (or (nil? email) (not (re-matches #"(?i)^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,6}$" email)))
      {:error "Invalid email format "}
      (empty? password)
      {:error "Password cannot be empty "}
      :else
      {})))
