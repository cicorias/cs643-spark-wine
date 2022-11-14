#!/usr/bin/env bash

docker run -it --rm -p 8888:8888 -p 4040:4040 -p 4041:4041 -v "${PWD}/work":/home/jovyan/work jupyter/all-spark-notebook
