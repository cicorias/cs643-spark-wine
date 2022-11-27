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
ADD model /app/model
