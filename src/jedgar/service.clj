(ns jedgar.service
    (:require [io.pedestal.service.http :as bootstrap]
              [io.pedestal.service.http.route :as route]
              [io.pedestal.service.http.body-params :as body-params]
              [io.pedestal.service.http.route.definition :refer [defroutes]]
              [ring.util.response :as ring-resp]
              [basil.public   :as basil]
              [basil.lib      :as balib]))

;;Defining the Jedgar template
(def jedgar-tpl (basil/make-group-from-classpath :prefix "templates/"))

(defn about-page
  [request]
  (ring-resp/response (format "Clojure %s" (clojure-version))))

(defn html-response
  [html]
  (ring-resp/content-type (ring-resp/response html) "text/html"))

(defn now-page
  "This function will return the current time"
  [request]
    (html-response (basil/render-by-name jedgar-tpl "now.html" [{:time (new java.util.Date)}])))

(defn home-page
  [request]
  (ring-resp/response "Hello World!"))

(defroutes routes
  [[["/" {:get home-page}
     ;; Set default interceptors for /about and any other paths under /
     ^:interceptors [(body-params/body-params)]
     ["/now" {:get now-page}]
     ["/about" {:get about-page}]]]])

;; You can use this fn or a per-request fn via io.pedestal.service.http.route/url-for
(def url-for (route/url-for-routes routes))

;; Consumed by jedgar.server/create-server
(def service {:env :prod
              ;; You can bring your own non-default interceptors. Make
              ;; sure you include routing and set it up right for
              ;; dev-mode. If you do, many other keys for configuring
              ;; default interceptors will be ignored.
              ;; :bootstrap/interceptors []
              ::bootstrap/routes routes
              ;; Root for resource interceptor that is available by default.
              ::bootstrap/resource-path "/public"
              ;; Either :jetty or :tomcat (see comments in project.clj
              ;; to enable Tomcat)
              ::bootstrap/type :jetty
              ::bootstrap/port 8080})
