(ns main
  (:require [clojure.java.jdbc :as j]
            [clojure.string :as str])
  (:gen-class))


(def db-url (System/getProperty "osm.db.url", "jdbc:postgresql://localhost:5432/osm?user=osmcities&password=osmcities"))

(defn get-all-nodes [db]
  (j/query db ["select id as nodes_id, tags -> 'name' AS name, tags -> 'is_in' AS is_in, tags -> 'is_in:country' as
                country, tags -> 'is_in:continent' as continent from nodes"]))

(defn get-lower-name [node]
  (let [name (get node :name "")]
    (if (nil? name)
      nil
      (str/lower-case name))))

(defn convert-osm-to-cities-table [db]
  (let [nodes (get-all-nodes db)
        nodes (mapv #(assoc % :lower_name (get-lower-name %)) nodes)
        _ (j/insert-multi! db :cities nodes)]))


(defn -main [& args]
  (convert-osm-to-cities-table db-url))

