(ns chejoobot.handlers.core
  (:require [clojure
             [spec :as spec]
             [test :refer [deftest is run-tests]]]
            [clojure.spec.test :as stest]
            [telegram-bot-api.core :as telegram]))

(defn command?
  "It is a filter that chacks for commands."
  [update]
  true)

(defn start
  "A handler that handles start command."
  [update]
  nil)

(defn command-handler
  "Handle any command."
  [update]
  nil)

(def handlers
  "Description of API handlers."
  ;; State -> [{:filter (fn [upd8] bool) :handler (fn [upd8] new-state)}]
  {nil [{:filter (command "start")
         :handler start}
        {:filter command?
         :handler command-handler}
        {:filter callback?
         :handler callback-handler}]
   ::ask []})

(spec/def ::state (spec/nilable keyword?))
(spec/def ::handler (spec/fspec :args (spec/cat :update ::telegram/update)
                                :ret ::state))
(spec/def ::handler-desc (spec/keys :req [::filter ::handler]))
(spec/def ::handlers-def (spec/map-of ::state (spec/coll-of ::handler-desc)))

(deftest handlers-test
  (is (spec/valid? ::handlers-def handlers)))
