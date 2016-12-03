(ns chejoobot.handlers.filters
  (:require [clojure
             [spec :as spec]
             [test :refer [deftest is testing]]]
            [telegram-bot-api.api :as tapi]))

;; Filter Spec

(spec/def ::filter (spec/fspec :args (spec/cat :update ::tapi/update)
                               :ret boolean?))

;; Command Literal Filter

(defn command-literal
  "Returns a filter that matches a command."
  [cmd]
  (fn [update] (= cmd update)))

(spec/fdef command-literal
           :args (spec/cat :command string?)
           :ret ::filter)

(deftest command-literal-test
  (let [res (stest/summarize-results (stest/check `command))]
    (is (= (:total res) (:passed res)))))
