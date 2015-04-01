(defproject dynamic-reverse-proxy "0.7.0-alpha1"
  :description "Dynamic reverse proxy"
  :url "http://github.com/softek/dynamic-reverse-proxy"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-3126"]]

  :profiles {:dev {:dependencies [[org.clojure/clojurescript "0.0-3126"]]}}
  :plugins [[lein-cljsbuild "1.0.5"]
            [lein-simpleton "1.3.0"]]

  :clean-targets ["out" "lib"]

  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src"]
              :compiler {
                :output-to "out/dev/dynamic-reverse-proxy.js"
                :output-dir "out/dev"
                :optimizations :none
                :source-map "out/dev/dynamic-reverse-proxy.js.map"
                :pretty-print true
                :preamble ["version.js"]
                :target :nodejs
                :externs []}}
             {:id "release"
              :source-paths ["src"]
              :compiler {
                :output-to "lib/dynamic-proxy.js"
                :output-dir "lib"
                :optimizations :advanced
                :source-map "lib/dynamic-proxy.js.map"
                :pretty-print false
                :preamble ["version.js"]
                :target :nodejs
                :externs ["node_modules/closurecompiler-externs/events.js"
                          "node_modules/closurecompiler-externs/stream.js"
                          "node_modules/closurecompiler-externs/net.js"
                          "node_modules/closurecompiler-externs/http.js"
                          "node_modules/closurecompiler-externs/https.js"
                          "externs/http-proxy.js"]}}]}

  :aliases {
    "build-release" ["do" "clean" ["cljsbuild" "once" "release"]]
    })
