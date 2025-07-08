(ns dango-stack.pp-under-care.create.validator
  (:require [clojure.string :as str]))

(def valid-genders #{"female" "male" "other" "unspecified"})

(defn valid-iso8601-date? [s]
  (boolean (re-matches #"^\d{4}-\d{2}-\d{2}$" s)))

(defn validate-person-details! [{:keys [name dob gender]}]
  (cond
    (or (not (string? name)) (str/blank? name))
    (throw (ex-info "Invalid or empty 'name' field" {:status 400}))

    (or (not (string? dob)) (not (valid-iso8601-date? dob)))
    (throw (ex-info "Invalid 'dob' field: expected ISO8601 date YYYY-MM-DD" {:status 400}))

    (or (not (string? gender)) (not (valid-genders gender)))
    (throw (ex-info (str "Invalid 'gender' field: must be one of " (pr-str valid-genders))
                    {:status 400}))

    :else
    true)) ; valid
