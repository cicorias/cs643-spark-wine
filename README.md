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


# Cloud Init

```bash
#!/bin/bash

apt update && apt upgrade -y
apt install -y default-jdk git gradle \
    maven htop dnsdiag net-tools netcat \
    curl wget


cd /opt
sudo curl -OL https://dlcdn.apache.org/spark/spark-3.3.1/spark-3.3.1-bin-hadoop3-scala2.13.tgz
sudo tar xzf spark-3.3.1-bin-hadoop3-scala2.13.tgz

```


```text
#main
ip-172-31-92-10.ec2.internal

#workers
ip-172-31-84-110.ec2.internal
ip-172-31-82-101.ec2.internal
ip-172-31-85-72.ec2.internal
ip-172-31-85-145.ec2.internal

```



```
./bin/spark-submit \
    --master spark://ip-172-31-92-10.ec2.internal:7077 \
    --class cs643.spark.wine.App \
    --files /home/ubuntu/g/cs643-spark-wine/TrainingDataset.csv,/home/ubuntu/g/cs643-spark-wine/ValidationDataset.csv \
    /home/ubuntu/g/cs643-spark-wine/app/build/libs/app.jar TrainingDataset.csv ValidationDataset.csv "spark://ip-172-31-92-10.ec2.internal:7077"




```