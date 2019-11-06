FROM ubuntu:18.04

ENV TZ=Europe/Warsaw
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone 

RUN apt update && apt install -y \
  libsnappy-dev libssl-dev build-essential automake pkg-config libtool libffi-dev libgmp-dev \
  jupyter-core \
  jupyter-notebook \
  jupyter-client \
  openjdk-8-jdk \  
  python3-pip \
  gradle \
  unzip \
  zip \
  wget \
  vim \
  git \
  curl \
  software-properties-common

#RUN curl -s https://get.sdkman.io | bash
#RUN chmod a+x "$HOME/.sdkman/bin/sdkman-init.sh"
#RUN source "$HOME/.sdkman/bin/sdkman-init.sh" && sdk install kotlin

ARG KOTLIN_VERSION=1.3.40
RUN cd /opt && \
    wget -q https://github.com/JetBrains/kotlin/releases/download/v${KOTLIN_VERSION}/kotlin-compiler-${KOTLIN_VERSION}.zip && \
    unzip *kotlin*.zip && \
    rm *kotlin*.zip

ENV KOTLIN_HOME /opt/kotlinc
ENV PATH ${PATH}:${KOTLIN_HOME}/bin

ENV PERSISTENCE_FOLDER=/opt/corda/
ENV CONFIG_FOLDER=/opt/corda/nodes/

EXPOSE 10002 10003 10004
EXPOSE 11000
EXPOSE 8888

EXPOSE 10011 10012 10013 10014 10015
EXPOSE 10021 10022 10023 10024 10025
EXPOSE 10031 10032 10033 10034 10035

RUN useradd -ms /bin/bash codete
RUN adduser codete sudo

RUN mkdir /opt/corda
RUN mkdir /opt/corda/bin/
RUN chown -R codete:codete /opt/corda

RUN update-java-alternatives --set /usr/lib/jvm/java-1.8.0-openjdk-amd64

USER codete
RUN mkdir /home/codete/workshop/
WORKDIR /home/codete/workshop/

RUN git clone https://github.com/corda/cordapp-template-kotlin.git
RUN git clone https://github.com/dineshrivankar/cordapp-trading.git

RUN wget -O /opt/corda/corda-webserver.jar http://r3.bintray.com/corda/net/corda/corda-webserver/4.1/corda-webserver-4.1.jar
RUN wget -O /opt/corda/corda.jar https://r3.bintray.com/corda/net/corda/corda/4.1/corda-4.1.jar
RUN wget -O /opt/corda/corda-tools-network-bootstrapper.jar https://ci-artifactory.corda.r3cev.com/artifactory/corda/net/corda/corda-tools-network-bootstrapper/4.1/corda-tools-network-bootstrapper-4.1.jar
#RUN wget -O /opt/corda/corda-tools-network-builder.jar https://ci-artifactory.corda.r3cev.com/artifactory/corda-releases/net/corda/corda-tools-network-builder/4.1/corda-tools-network-builder-4.1.jar
RUN git clone -b corda https://github.com/kprzystalski/oreilly-intro-to-dlt
RUN mv /home/codete/workshop/oreilly-intro-to-dlt/corda/scripts/* /opt/corda/bin/
RUN mkdir /opt/corda/nodes
RUN mv /opt/corda/bin/node.conf /opt/corda/nodes/node.conf
RUN mkdir /opt/corda/certificates/
RUN mkdir /opt/corda/logs/

CMD jupyter-notebook --ip=0.0.0.0 --NotebookApp.token='' --NotebookApp.password='' --no-browser --notebook-dir=/home/codete/workshop/
