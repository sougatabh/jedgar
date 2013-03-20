(defproject jedgar "0.0.1-SNAPSHOT"
  :description "jedgar, a sample application with pedestal(Clojure web framework) and basil(templating framework in clojure)"
  :url ""
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.0"]
                 [io.pedestal/pedestal.service "0.1.0"]

                 ;; Remove this line and uncomment the next line to
                 ;; use Tomcat instead of Jetty:
                 [io.pedestal/pedestal.jetty "0.1.0"]
                 ;; [io.pedestal/pedestal.tomcat "0.0.1-SNAPSHOT"]

                 ;; Logging
                 [ch.qos.logback/logback-classic "1.0.7"]
                 [org.slf4j/jul-to-slf4j "1.7.2"]
                 [org.slf4j/jcl-over-slf4j "1.7.2"]
                 [org.slf4j/log4j-over-slf4j "1.7.2"]
                 [basil         "0.4.0"]]
  :profiles {:dev {:source-paths ["dev"]}}
  :resource-paths ["config"]
  :main ^{:skip-aot true} jedgar.server)
