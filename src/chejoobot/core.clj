(ns chejoobot.core
  (:gen-class)
  (:require [clojure.core
             [async :as async]
             [match :refer [match]]]
            [clojure.spec :as spec]
            [clojure.spec.gen :as gen]
            [taoensso.timbre :as timbre]))

(defn read-updates
  "Start a server or a process to pull updates from telegram api."
  [server-mode]
  (let [updates (async/chan 1000)]
    (match server-mode
      :poll (start-poll-server updates)
      :push (start-ring-server updates))
    updates))

(spec/def ::server-mode #{:poll :push})
(comment (spec/exercise ::server-mode))
(spec/fdef read-updates
           :args ::server-mode)

(defn process-updates
  "Process telegram updates one by one and preserve state between updates."
  [state update]
  )

(defn -main [& args]
  (let [updates (read-updates :poll)]
    (async/reduce process-updates nil updates)))
