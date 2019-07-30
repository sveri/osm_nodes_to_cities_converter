(ns main
  (:require [clojure.java.jdbc :as j])
  (:gen-class))


(def db-url (System/getProperty "osm.db.url", "jdbc:postgresql://localhost:5432/osm?user=osmcities&password=osmcities"))

(defn get-all-nodes [db]
  (j/query db ["select id as nodes_id, tags -> 'name' AS name, tags -> 'is_in' AS is_in, tags -> 'is_in:country' as
                country, tags -> 'is_in:continent' as continent from nodes"]))

(defn convert-osm-to-cities-table [db]
  (let [nodes (get-all-nodes db)
        _ (j/insert-multi! db :cities nodes)]))


(defn -main [& args]
  (convert-osm-to-cities-table db-url))

