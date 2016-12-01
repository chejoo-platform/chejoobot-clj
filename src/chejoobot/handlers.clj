(ns chejoobot.handlers
  (:require [clojure.spec :as spec]
            [clojure.test :refer [deftest is run-tests]]))

(defn command
  "Returns a filter that matches a command."
  [cmd]
  (fn [update] (= cmd update)))

(spec/def ::filter (spec/fspec :args (spec/cat :update ::telegram/update)
                               :ret boolean?))
(spec/fdef command
           :args (spec/cat :command string?)
           :ret ::filter)

(def handlers
  "Description of API handlers."
  ;; State -> [{:filter (fn [upd8] bool) :handler (fn [upd8] new-state)}]
  {nil [{:filter (command "start")
         :handler start}
        {:filter command?
         :handler command-handler}]})

(spec/def ::handler (spec/fspec :args (spec/cat :update ::telegram/update)
                                :ret (spec/nilable keyword?)))

(deftest handlers-test
  (is (spec/valid? ::handlers-def handlers)))

(comment (run-tests))
