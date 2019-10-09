(ns main)

(def my-name "Andrea Morciano")

(defn hello-message
  [name]
  (str "Hello " name))

(defn -main
  [& args]
  (println (hello-message my-name)))
