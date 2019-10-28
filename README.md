## WIP
# easy-bert spring webflux

Simple example of creating bert rest server with spring web flux.
Based on https://github.com/robrua/easy-bert java Bert adapter

## Requirements

* Java 11+
* Python to init bert model. Since Tensorflow java cannot open original model.


## Init model

* `python model_init.py` to load bert model. 
First argument may be used to specify directory for model. 
Default directory is `tf/model`

## Start server
* `BERT_PATH={absolute path to model} ./gradlew bootRun` or use basic spring boot workflow.
