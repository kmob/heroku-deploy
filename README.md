# baserf

A [re-frame](https://github.com/day8/re-frame) application designed to test docker deploy to heroku.

## ISSUES
Outdated version of shadow-cljs was generating security warnings at GitHub.
After updates, jar builds fail. Not clear that this still works to test docker.

## Getting Started

``` 
$ lein new re-frame baserf +handler +routes
```

Start the app. TODO - Doesn't work due to the edn configuration.
``` 
$ lein deps && npm install
$ lein watch
shadow-cljs - HTTP server available at http://localhost:8280
shadow-cljs - server version: 2.11.7 running at http://localhost:9630
shadow-cljs - nREPL server started on port 8777
shadow-cljs - watching build :app
[:app] Configuring build.
[:app] Compiling ...
[:app] Build completed. (261 files, 0 compiled, 0 warnings, 19.65s)
```


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
