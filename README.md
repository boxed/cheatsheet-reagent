# cheatsheet-reagent

This lib has two parts:

- Parse the clojure cheatsheet and output something more clojure-esque and also collect metadata. Outputs an edn file.
- A reagent based ClojureScript frontend implementation

## Run

    lein run 8000

This will run the parse code, compile the ClojureScript, output the edn file and start a test webserver on port 8000 for trying it out.
