(defproject baserf "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clojure/clojurescript "1.10.879"
                  :exclusions [com.google.javascript/closure-compiler-unshaded
                               org.clojure/google-closure-library
                               org.clojure/google-closure-library-third-party]]
                 [thheller/shadow-cljs "2.15.5"]
                 [reagent "1.1.0"]
                 [re-frame "1.2.0"]
                 [secretary "1.2.3"]
                 [compojure "1.6.2"]
                 [yogthos/config "1.1.8"]
                 [ring "1.9.4"]]

  :plugins [
            [lein-shell "0.5.0"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj" "src/cljs"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]


  :shell {:commands {"open" {:windows ["cmd" "/c" "start"]
                             :macosx  "open"
                             :linux   "xdg-open"}}}

  :aliases {"dev"          ["with-profile" "dev" "do"
                            ["run" "-m" "shadow.cljs.devtools.cli" "watch" "app"]]
            "prod"         ["with-profile" "prod" "do"
                            ["run" "-m" "shadow.cljs.devtools.cli" "release" "app"]]
            "build-report" ["with-profile" "prod" "do"
                            ["run" "-m" "shadow.cljs.devtools.cli" "run" "shadow.cljs.build-report" "app" "target/build-report.html"]
                            ["shell" "open" "target/build-report.html"]]
            "karma"        ["with-profile" "prod" "do"
                            ["run" "-m" "shadow.cljs.devtools.cli" "compile" "karma-test"]
                            ["shell" "karma" "start" "--single-run" "--reporters" "junit,dots"]]}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "1.0.3"]]
    :source-paths ["dev"]}

   :prod { }

   :uberjar {:source-paths ["env/prod/clj"]
             :omit-source  true
             :main         baserf.server
             :aot          [baserf.server]
             :uberjar-name "baserf.jar"
             :prep-tasks   ["compile" ["prod"]]}}

  :prep-tasks [])
