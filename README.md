# baserf

A [re-frame](https://github.com/day8/re-frame) application designed to test docker deploy to heroku.

## Getting Started

``` 
$ lein new re-frame baserf +handler +routes
```

### Project Overview

* Architecture:
[Single Page Application (SPA)](https://en.wikipedia.org/wiki/Single-page_application)
* Languages
  - Front end ([re-frame](https://github.com/day8/re-frame)): [ClojureScript](https://clojurescript.org/) (CLJS)
  - Back end/middleware ([Compojure](https://github.com/weavejester/compojure)): [Clojure](https://clojure.org/)
* Dependencies
  - UI framework: [re-frame](https://github.com/day8/re-frame)
  ([docs](https://github.com/day8/re-frame/blob/master/docs/README.md),
  [FAQs](https://github.com/day8/re-frame/blob/master/docs/FAQs/README.md)) ->
  [Reagent](https://github.com/reagent-project/reagent) ->
  [React](https://github.com/facebook/react)
  - Full stack framework: [Compojure](https://github.com/weavejester/compojure)
  ([Wiki](https://github.com/weavejester/compojure/wiki), [API docs](http://weavejester.github.com/compojure)) ->
  [Ring](https://github.com/ring-clojure/ring)
  ([Wiki](https://github.com/ring-clojure/ring/wiki), [API docs](http://ring-clojure.github.com/ring))
  - Client-side routing: [Secretary](https://github.com/gf3/secretary)
* Build tools
  - Project task & dependency management: [Leiningen](https://github.com/technomancy/leiningen)
  - CLJS compilation, REPL, & hot reload: [`shadow-cljs`](https://github.com/thheller/shadow-cljs)
* Development tools
  - Debugging: [CLJS DevTools](https://github.com/binaryage/cljs-devtools)

#### Directory structure

* [`/`](/../../): project config files
* [`dev/`](dev/): source files compiled only with the [dev](#running-the-app) profile
  - [`cljs/user.cljs`](dev/cljs/user.cljs): symbols for use during development in the
[ClojureScript REPL](#connecting-to-the-browser-repl-from-a-terminal)
* [`resources/public/`](resources/public/): SPA root directory;
[dev](#running-the-app) / [prod](#production) profile depends on the most recent build
  - [`index.html`](resources/public/index.html): SPA home page
    - Dynamic SPA content rendered in the following `div`:
        ```html
        <div id="app"></div>
        ```
    - Customizable; add headers, footers, links to other scripts and styles, etc.
  - Generated directories and files
    - Created on build with either the [dev](#running-the-app) or [prod](#production) profile
    - Deleted on `lein clean` (run by all `lein` aliases before building)
    - `js/compiled/`: compiled CLJS (`shadow-cljs`)
      - Not tracked in source control; see [`.gitignore`](.gitignore)
* [`src/clj/baserf/`](src/clj/baserf/): Backend and middleware source files (Clojure,
[Compojure](https://github.com/weavejester/compojure))
* [`src/cljs/baserf/`](src/cljs/baserf/): SPA source files (ClojureScript,
[re-frame](https://github.com/Day8/re-frame))
  - [`core.cljs`](src/cljs/baserf/core.cljs): contains the SPA entry point, `init`

### Editor/IDE

Use your preferred editor or IDE that supports Clojure/ClojureScript development. See
[Clojure tools](https://clojure.org/community/resources#_clojure_tools) for some popular options.

### Environment Setup

1. Install [JDK 8 or later](https://openjdk.java.net/install/) (Java Development Kit)
2. Install [Leiningen](https://leiningen.org/#install) (Clojure/ClojureScript project task &
dependency management)
3. Install [Node.js](https://nodejs.org/) (JavaScript runtime environment)
7. Clone this repo and open a terminal in the `baserf` project root directory
8. Download project dependencies:
    ```sh
    lein deps && npm install
    ```

### Browser Setup

Browser caching should be disabled when developer tools are open to prevent interference with
[`shadow-cljs`](https://github.com/thheller/shadow-cljs) hot reloading.

Custom formatters must be enabled in the browser before
[CLJS DevTools](https://github.com/binaryage/cljs-devtools) can display ClojureScript data in the
console in a more readable way.

#### Chrome/Chromium

1. Open [DevTools](https://developers.google.com/web/tools/chrome-devtools/) (Linux/Windows: `F12`
or `Ctrl-Shift-I`; macOS: `⌘-Option-I`)
2. Open DevTools Settings (Linux/Windows: `?` or `F1`; macOS: `?` or `Fn+F1`)
3. Select `Preferences` in the navigation menu on the left, if it is not already selected
4. Under the `Network` heading, enable the `Disable cache (while DevTools is open)` option
5. Under the `Console` heading, enable the `Enable custom formatters` option

#### Firefox

1. Open [Developer Tools](https://developer.mozilla.org/en-US/docs/Tools) (Linux/Windows: `F12` or
`Ctrl-Shift-I`; macOS: `⌘-Option-I`)
2. Open [Developer Tools Settings](https://developer.mozilla.org/en-US/docs/Tools/Settings)
(Linux/macOS/Windows: `F1`)
3. Under the `Advanced settings` heading, enable the `Disable HTTP Cache (when toolbox is open)`
option

Unfortunately, Firefox does not yet support custom formatters in their devtools. For updates, follow
the enhancement request in their bug tracker:
[1262914 - Add support for Custom Formatters in devtools](https://bugzilla.mozilla.org/show_bug.cgi?id=1262914).

## Development

### Running the App

Start a temporary local web server, build the app with the `dev` profile, and serve the app with
hot reload:

```sh
lein dev
```

Please be patient; it may take over 20 seconds to see any output, and over 40 seconds to complete.

When `[:app] Build completed` appears in the output, browse to
[http://localhost:8280/](http://localhost:8280/).

[`shadow-cljs`](https://github.com/thheller/shadow-cljs) will automatically push ClojureScript code
changes to your browser on save. To prevent a few common issues, see
[Hot Reload in ClojureScript: Things to avoid](https://code.thheller.com/blog/shadow-cljs/2019/08/25/hot-reload-in-clojurescript.html#things-to-avoid).

Opening the app in your browser starts a
[ClojureScript browser REPL](https://clojurescript.org/reference/repl#using-the-browser-as-an-evaluation-environment),
to which you may now connect.

#### Connecting to the browser REPL from your editor

See
[Shadow CLJS User's Guide: Editor Integration](https://shadow-cljs.github.io/docs/UsersGuide.html#_editor_integration).
Note that `lein dev` runs `shadow-cljs watch` for you, and that this project's running build id is
`app`, or the keyword `:app` in a Clojure context.

Alternatively, search the web for info on connecting to a `shadow-cljs` ClojureScript browser REPL
from your editor and configuration.

For example, in Vim / Neovim with `fireplace.vim`
1. Open a `.cljs` file in the project to activate `fireplace.vim`
2. In normal mode, execute the `Piggieback` command with this project's running build id, `:app`:
    ```vim
    :Piggieback :app
    ```

#### Connecting to the browser REPL from a terminal

1. Connect to the `shadow-cljs` nREPL:
    ```sh
    lein repl :connect localhost:8777
    ```
    The REPL prompt, `shadow.user=>`, indicates that is a Clojure REPL, not ClojureScript.

2. In the REPL, switch the session to this project's running build id, `:app`:
    ```clj
    (shadow.cljs.devtools.api/nrepl-select :app)
    ```
    The REPL prompt changes to `cljs.user=>`, indicating that this is now a ClojureScript REPL.
3. See [`user.cljs`](dev/cljs/user.cljs) for symbols that are immediately accessible in the REPL
without needing to `require`.

### Running `shadow-cljs` Actions

See a list of [`shadow-cljs CLI`](https://shadow-cljs.github.io/docs/UsersGuide.html#_command_line)
actions:
```sh
lein run -m shadow.cljs.devtools.cli --help
```

Please be patient; it may take over 10 seconds to see any output. Also note that some actions shown
may not actually be supported, outputting "Unknown action." when run.

Run a shadow-cljs action on this project's build id (without the colon, just `app`):
```sh
lein run -m shadow.cljs.devtools.cli <action> app
```
### Debug Logging

The `debug?` variable in [`config.cljs`](src/cljs/baserf/config.cljs) defaults to `true` in
[`dev`](#running-the-app) builds, and `false` in [`prod`](#production) builds.

Use `debug?` for logging or other tasks that should run only on `dev` builds:

```clj
(ns baserf.example
  (:require [baserf.config :as config])

(when config/debug?
  (println "This message will appear in the browser console only on dev builds."))
```

## Production

Build the app with the `prod` profile:

```sh
lein with-profile prod uberjar
```

Please be patient; it may take a few seconds to see any output, and over 50 seconds to complete.

The `resources/public/js/compiled` directory is created, containing the compiled `app.js` and
`manifest.edn` files. The `target/` directory is then created, containing the
standalone `baserf.jar`.

### Running the Server

[Run the jar](https://github.com/ring-clojure/ring/wiki/Setup-for-production#run-the-server),
setting the port the Ring server will use by setting the environment variable, `port`.

# Passing a port in as shown below results in a failure to launch:
``` 
Kevins-Air-3:baserf kmob$ port=3000 java -jar target/baserf.jar 
2020-04-04 12:40:54.837:INFO::main: Logging initialized @4225ms to org.eclipse.jetty.util.log.StdErrLog
Exception in thread "main" java.lang.ClassCastException: java.lang.Long incompatible with java.lang.String
        at baserf.server$_main.invokeStatic(server.clj:8)
        at baserf.server$_main.doInvoke(server.clj:7)
        at clojure.lang.RestFn.invoke(RestFn.java:397)
        at clojure.lang.AFn.applyToHelper(AFn.java:152)
        at clojure.lang.RestFn.applyTo(RestFn.java:132)
        at baserf.server.main(Unknown Source)
```

```sh
port=2000 java -jar target/baserf.jar
```

If `port` is not set, the server will run on port 3000 by default.

### Deploying to Heroku

1. [Create a Heroku app](https://devcenter.heroku.com/articles/creating-apps):
    ```sh
    heroku create
    ```

2. [Deploy the app code](https://devcenter.heroku.com/articles/git#deploying-code):

    ```sh
    git push heroku master
    ```
  

### If you don't include node_modules directory the deploy fails

THIS REALLY ISN'T THE REASON!!!! THE REASON WAS BUILDPACKS SETTINGS!!!!
The deploy fails if the .gitignore excludes the node_modules directory 
``` 
remote:        Compiling baserf.server
remote:        [:app] Compiling ...
remote:        The required namespace "react" is not available, it was required by "reagent/core.cljs".
remote:        The namespace was provided via :foreign-libs which is not supported.
remote:        Please refer to https://shadow-cljs.github.io/docs/UsersGuide.html#cljsjs for more information.
remote:        You may just need to run:
remote:          npm install react
remote:        Error encountered performing task 'do' with profile(s): 'prod'
remote:        Suppressed exit
remote:  !     Failed to build.
remote:  !     Push rejected, failed to compile Clojure (Leiningen 2) app.
```

# With node_modules included in the git repo, "[:app] Compiling" succeeds 
``` 
remote:        [:app] Compiling ...
remote:        [:app] Build completed. (119 files, 42 compiled, 0 warnings, 42.56s)
remote:        Created /tmp/build_9f325287c3ca4cfce2aaf9d2066cd156/target/baserf-0.1.0-SNAPSHOT.jar
remote:        Created /tmp/build_9f325287c3ca4cfce2aaf9d2066cd156/target/baserf.jar
remote: -----> Discovering process types
remote:        Procfile declares types -> web
remote: 
remote: -----> Compressing...
remote:        Done: 115.2M
remote: -----> Launching...
remote:        Released v3
remote:        https://mighty-meadow-78433.herokuapp.com/ deployed to Heroku
```
# But the app crashes.
at the browser:
``` 
Application Error
An error occurred in the application and your page could not be served. If you are the application owner, check your logs for details. You can do this from the Heroku CLI with the command
heroku logs --tail
```
In the heroku log
```
2020-04-04T18:19:58.000000+00:00 app[api]: Build started by user kmobgm@gmail.com
2020-04-04T18:22:12.331444+00:00 app[api]: Deploy 4f070772 by user kmobgm@gmail.com
2020-04-04T18:22:12.354868+00:00 app[api]: Scaled to web@1:Free by user kmobgm@gmail.com
2020-04-04T18:22:12.331444+00:00 app[api]: Release v3 created by user kmobgm@gmail.com
2020-04-04T18:22:19.000000+00:00 app[api]: Build succeeded
2020-04-04T18:22:20.677654+00:00 app[web.1]: Setting JAVA_TOOL_OPTIONS defaults based on dyno size. Custom settings will override them.
2020-04-04T18:22:20.684843+00:00 app[web.1]: Picked up JAVA_TOOL_OPTIONS: -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8
2020-04-04T18:22:28.721297+00:00 app[web.1]: 2020-04-04 18:22:28.717:INFO::main: Logging initialized @8032ms to org.eclipse.jetty.util.log.StdErrLog
2020-04-04T18:22:29.187415+00:00 app[web.1]: Execution error (ClassCastException) at baserf.server/-main (server.clj:8).
2020-04-04T18:22:29.187426+00:00 app[web.1]: java.lang.Long cannot be cast to java.lang.String
2020-04-04T18:22:29.187427+00:00 app[web.1]: 
2020-04-04T18:22:29.187427+00:00 app[web.1]: Full report at:
2020-04-04T18:22:29.187428+00:00 app[web.1]: /tmp/clojure-5597310829239524502.edn
2020-04-04T18:22:29.299360+00:00 heroku[web.1]: State changed from starting to crashed
2020-04-04T18:22:29.303175+00:00 heroku[web.1]: State changed from crashed to starting
2020-04-04T18:22:36.252942+00:00 app[web.1]: Setting JAVA_TOOL_OPTIONS defaults based on dyno size. Custom settings will override them.
2020-04-04T18:22:36.264398+00:00 app[web.1]: Picked up JAVA_TOOL_OPTIONS: -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8
2020-04-04T18:22:40.876089+00:00 app[web.1]: 2020-04-04 18:22:40.872:INFO::main: Logging initialized @4608ms to org.eclipse.jetty.util.log.StdErrLog
2020-04-04T18:22:41.227753+00:00 app[web.1]: Execution error (ClassCastException) at baserf.server/-main (server.clj:8).
2020-04-04T18:22:41.227763+00:00 app[web.1]: java.lang.Long cannot be cast to java.lang.String
2020-04-04T18:22:41.227764+00:00 app[web.1]: 
2020-04-04T18:22:41.227764+00:00 app[web.1]: Full report at:
2020-04-04T18:22:41.227765+00:00 app[web.1]: /tmp/clojure-6285618264460749594.edn
2020-04-04T18:22:41.329653+00:00 heroku[web.1]: State changed from starting to crashed
2020-04-04T18:22:41.996356+00:00 heroku[router]: at=error code=H10 desc="App crashed" method=GET path="/" host=mighty-meadow-78433.herokuapp.com request_id=4c138dc0-f9fa-4956-b31c-4c55481af84d fwd="70.94.9.139" dyno= connect= service= status=503 bytes= protocol=https
2020-04-04T18:22:42.718140+00:00 heroku[router]: at=error code=H10 desc="App crashed" method=GET path="/favicon.ico" host=mighty-meadow-78433.herokuapp.com request_id=396f97ac-8241-4d5e-80db-7cf2d940e8fd fwd="70.94.9.139" dyno= connect= service= status=503 bytes= protocol=https
2020-04-04T18:23:45.293241+00:00 heroku[router]: at=error code=H10 desc="App crashed" method=GET path="/" host=mighty-meadow-78433.herokuapp.com request_id=78db1088-8f40-45a4-b0c3-c3cc52b34b74 fwd="70.94.9.139" dyno= connect= service= status=503 bytes= protocol=https
2020-04-04T18:23:45.859752+00:00 heroku[router]: at=error code=H10 desc="App crashed" method=GET path="/favicon.ico" host=mighty-meadow-78433.herokuapp.com request_id=49685557-37a8-482e-9ed1-86f0461c2c21 fwd="70.94.9.139" dyno= connect= service= status=503 bytes= protocol=https
```

Added the target directory back to the git repo and still get failure in the heroku log.
``` 
2020-04-04T18:49:54.000000+00:00 app[api]: Build started by user kmobgm@gmail.com
2020-04-04T18:52:01.907852+00:00 heroku[web.1]: State changed from crashed to starting
2020-04-04T18:52:01.490185+00:00 app[api]: Release v4 created by user kmobgm@gmail.com
2020-04-04T18:52:01.490185+00:00 app[api]: Deploy 85a69209 by user kmobgm@gmail.com
2020-04-04T18:52:07.000000+00:00 app[api]: Build succeeded
2020-04-04T18:52:09.604459+00:00 app[web.1]: Setting JAVA_TOOL_OPTIONS defaults based on dyno size. Custom settings will override them.
2020-04-04T18:52:09.609633+00:00 app[web.1]: Picked up JAVA_TOOL_OPTIONS: -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8
2020-04-04T18:52:14.216141+00:00 app[web.1]: 2020-04-04 18:52:14.211:INFO::main: Logging initialized @4602ms to org.eclipse.jetty.util.log.StdErrLog
2020-04-04T18:52:14.551011+00:00 app[web.1]: Execution error (ClassCastException) at baserf.server/-main (server.clj:8).
2020-04-04T18:52:14.551102+00:00 app[web.1]: java.lang.Long cannot be cast to java.lang.String
2020-04-04T18:52:14.551103+00:00 app[web.1]: 
2020-04-04T18:52:14.551104+00:00 app[web.1]: Full report at:
2020-04-04T18:52:14.551104+00:00 app[web.1]: /tmp/clojure-6490846177929135515.edn
2020-04-04T18:52:14.673168+00:00 heroku[web.1]: State changed from starting to crashed
2020-04-04T18:52:18.435716+00:00 heroku[router]: at=error code=H10 desc="App crashed" method=GET path="/" host=mighty-meadow-78433.herokuapp.com request_id=25d76446-7a0f-4033-8607-32b3f6f39817 fwd="70.94.9.139" dyno= connect= service= status=503 bytes= protocol=https
2020-04-04T18:52:18.918021+00:00 heroku[router]: at=error code=H10 desc="App crashed" method=GET path="/favicon.ico" host=mighty-meadow-78433.herokuapp.com request_id=3963cd05-9a18-42f2-b5de-20d70bca394d fwd="70.94.9.139" dyno= connect= service= status=503 bytes= protocol=https

```

# Fixed Build Issues
The errors are complaining about path but before that the build still complains about a string in the main. 
There are multiple discussions about heroku wanting to determine it's own port so I resorted to Procfile and main as 
used in the simple web app for heroku deployment project from [Purelyfunctional](https://purelyfunctional.tv/courses/web-dev-in-clojure/).

In Procfile:
``` 
web: java $JVM_OPTS -cp target/baserf.jar clojure.main -m baserf.server $PORT
```

In src/clj/baserf/baserf.server
``` 
(defn -main [port]
  (run-jetty handler {:port (Integer. port)}))
```

Should now go back to the git configuration and remove all the storage of target and node_modules since
those probably didn't matter.

* remove /target from git repo - DONE!
* remove /node_modules from git repo - 

# Final Summary on Heroku Deploy!

0. Fix the -main and Procfile (see above) before attempting deploy to Heroku.

1. [Create a Heroku app](https://devcenter.heroku.com/articles/creating-apps):
    ```sh
    heroku create
    ```

2. Set heroku [buildpacks](https://devcenter.heroku.com/articles/using-node-js-with-clojure-and-clojurescript-applications)
Add buildbacks at the heroku dashboard or at the command line. Without buildpacks set 
to include nodejs, heroku's build tools can't find node.

```
$ heroku buildpacks:clear
$ heroku buildpacks:add heroku/nodejs
$ heroku buildpacks:add heroku/clojure
```

3. [Deploy the app code](https://devcenter.heroku.com/articles/git#deploying-code):

    ```sh
    git push heroku master
    ```
