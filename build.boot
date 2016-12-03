
(set-env!
 :resource-paths #{"src"}
 :dependencies '[[org.clojure/clojure "1.9.0-alpha14"]
                 [org.clojure/test.check "0.9.0" :scope "test"]
                 [aleph "0.4.2-alpha8"]
                 [com.taoensso/timbre "4.7.4"]
                 [org.clojure/core.async "0.2.395"]
                 [org.clojure/core.match "0.3.0-alpha4"]
                 [org.clojars.mkhoeini/telegram-bot-api "0.1.0-SNAPSHOT"]])

(task-options!
 pom {:project 'org.clojars.mkhoeini/chejoobot-clj
      :version "0.1.0-SNAPSHOT"})

(deftask build
  "Build my project."
  []
  (comp (pom) (jar) (install)))
