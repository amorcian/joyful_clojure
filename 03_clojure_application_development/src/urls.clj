(ns urls
  (:refer-clojure :exclude [update])
  (:require [korma.core :refer :all]
            [korma.db :refer :all]
            [db.core :refer [db]]))

(defentity urls
  (database db)
  (table :urls)
  (entity-fields :id :url))

(defn get-url-handler
  [req]
  (let [id (get-in req [:params :id])
        result (select urls (where (= :id id)))
        url (first result)]
    (if-not url
      {:status 404}
      {:status 200 :body url})))

(defn create-url-handler
  [req]
  (let [url (get-in req [:body :url])
        id (.toString (java.util.UUID/randomUUID))
        entity {:url url :id id}]
    (transaction
     (insert urls (values entity)))
    {:status 201 :body entity}))