(defproject dango-stack "0.1.0-SNAPSHOT"
  :description "Simple REST app like Express.js" 
  :repositories {"central" {:url "https://repo1.maven.org/maven2/"}
                 "clojars" {:url "https://repo.clojars.org/"}}
  :dependencies [
                 [org.clojure/clojure "1.11.1"]
                 [compojure "1.7.0"] 
                 [ring/ring-core "1.12.1"]
                 [ring/ring-jetty-adapter "1.12.1"] 
                 [ring/ring-json "0.5.1"]
                 [org.postgresql/postgresql "42.2.25"]
                 [cheshire "5.11.0"] 
                 [org.clojure/tools.logging "1.3.0"] 
                 [org.clojure/java.jdbc "0.4.2"]
                 [environ "1.2.0"]
                 [com.draines/postal "2.0.4"]
                 [buddy/buddy-sign "3.4.333"]
                 [bcrypt-clj "0.3.3"]]
  
  :plugins [[lein-dotenv "RELEASE"]]
  :main dango-stack.core)
