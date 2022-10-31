https://hub.docker.com/r/bitnami/spark


https://www.guru99.com/pyspark-tutorial.html

https://spark.apache.org/examples.html


docker run -d -p 8888:8888 -p 4040:4040 -p 4041:4041 jupyter/pyspark-notebook

https://jupyter-docker-stacks.readthedocs.io/en/latest/using/specifics.html#apache-spark

docker run -it --rm -p 8888:8888 -p 4040:4040 -p 4041:4041 -v $(pwd)/home:/data jupyter/all-spark-notebook

docker run -it --rm -p 8888:8888 -p 4040:4040 -p 4041:4041 -v "${PWD}/work":/home/jovyan/work jupyter/all-spark-notebook


# running the ml model
https://piotrszul.github.io/spark-tutorial/notebooks/3.1_ML-Introduction.html

https://github.com/piotrszul/spark-tutorial/tree/master/notebooks


https://www.kaggle.com/code/fatmakursun/pyspark-ml-tutorial-for-beginners
