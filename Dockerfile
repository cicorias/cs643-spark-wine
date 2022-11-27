FROM ubuntu:jammy-20221101

# Install dependencies
RUN apt-get update && apt-get install -y \
    build-essential \
    cmake \
    git \
    wget \
    default-jdk \
    gradle \
    maven \
    htop \
    dnsdiag \
    net-tools \
    netcat \
    curl \
    wget



ADD app /app
# ADD app/build/libs /app/build/libs
ADD model /app/model
# ADD jars/jcommander-1.82.jar /app/build/libs/jcommander-1.82.jar
# ADD jars/slf4j-api-1.7.33.jar /app/build/libs/slf4j-api-1.7.33.jar

WORKDIR /app
RUN tar xvf /app/build/distributions/app.tar

ENTRYPOINT ["bash", "-c", "/app/app/bin/app -p prediction"]
CMD ["-t /data/test.csv"]


