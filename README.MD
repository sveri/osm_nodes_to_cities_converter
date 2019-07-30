Generate uberjar with depstar https://github.com/seancorfield/depstar:

    clj -A:depstar -m hf.depstar.uberjar Cities.jar

Then execute the jar by running:

    java -Dosm.db.url=jdbc:postgresql://localhost:5432/database\?user=usernam\&password=password -cp Cities.jar 
    clojure.main -m main