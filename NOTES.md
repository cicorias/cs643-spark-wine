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
    --packages com.amazonaws:aws-java-sdk:1.12.349,org.apache.hadoop:hadoop-aws:3.3.4 \
    /home/ubuntu/g/cs643-spark-wine/app/build/libs/app.jar TrainingDataset.csv ValidationDataset.csv "spark://ip-172-31-92-10.ec2.internal:7077"


   --files /home/ubuntu/g/cs643-spark-wine/TrainingDataset.csv,/home/ubuntu/g/cs643-spark-wine/ValidationDataset.csv \

```
```
./bin/spark-shell \
    --master spark://ip-172-31-92-10.ec2.internal:7077 \
    --packages com.amazonaws:aws-java-sdk:1.12.349,org.apache.hadoop:hadoop-aws:3.3.4 \
    --conf spark.hadoop.fs.s3a.endpoint=s3.us-east-1.amazonaws.com \
    --conf spark.executor.extraJavaOptions=-Dcom.amazonaws.services.s3.enableV4=true \
    --conf spark.driver.extraJavaOptions=-Dcom.amazonaws.services.s3.enableV4=true

```

```
spark.hadoop.fs.s3a.access.key ASIAVHYSVG6AHITBXIWM
spark.hadoop.fs.s3a.secret.key jQ4QWj2bzAl3ffzj4IS1YTSp3j2YnpPaKFw+okOO
aws_session_token=FwoGZXIvYXdzECoaDCISSGJmnD5ZYb4IfyK/AW8ac+vLX+wUZWDx3XpHm+QQZPUebAWFe7C+VIlLphH0hZhQ7iDtIDmKcnxl40Bb+H6QvzlcPTvQMwg83kDVFi37E/IZ/x+Hhu1iV2GxJJQ3KukQ2pC97UpedSVw70dLADX4PrKoDdQm067l3QpJ8gXWV5sRbFyH3LoflUomsMPYKjUSt6WRqulRlYvnjX5G7zVpjiGT/eU6Ct1rLBZ8FvIuPA3xgeYYkuTI27/iABadiVjeBcuB5AFFQ1/8dyO0KJWgjpwGMi2A1S798h/29WCWdpQk74NliprRn8QrvP75oQnfAlkDBAyMyDYg675YAtswVX0=
spark.hadoop.fs.s3a.impl org.apache.hadoop.fs.s3a.S3AFileSystem


```


sc.hadoopConfiguration.set("fs.s3a.awsAccessKeyId","")
sc.hadoopConfiguration.set("fs.s3a.awsSecretAccessKey","+okOO" )
val myRDD = sc.textFile("s3a://test-bucket-njit/TrainingDataset.csv")


```
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": [
        "s3:GetObject",
        "s3:PutObject",
        "s3:ListObject",
        "s3:DeleteObject"
      ],
      "Effect": "Allow",
      "Resource": [
        "arn:aws:s3:::secret-bucket/*"
      ],
      "Sid": ""
    }
  ]
}




{
	"Version": "2012-10-17",
	"Statement": [
		{
			"Sid": "AddPerm",
			"Effect": "Allow",
			"Principal": "*",
			"Action": ["s3:GetObject", 
			            "s3:PutObject",
			            "s3:ListObject",
			            "s3:DeleteObject"],
			"Resource": "arn:aws:s3:::test-bucket-njit/*"
		}
	]
}
```