#!/bin/bash

docker run -it --rm  -v $PWD/data:/data -v $PWD/data:/app/data  cicorias/predict:latest 