FROM ubuntu:18.04

ENV TZ=Europe/Warsaw
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone 

RUN apt update && apt install -y \
  libsnappy-dev libssl-dev build-essential automake pkg-config libtool libffi-dev libgmp-dev \
  jupyter-core \
  jupyter-notebook \
  jupyter-client \
  python3-pip \
  wget \
  vim \
  git \
  curl \
  software-properties-common

RUN curl -sL https://deb.nodesource.com/setup_11.x | bash -
RUN add-apt-repository -y ppa:ethereum/ethereum

RUN apt-get update
RUN apt-get install -y ethereum nodejs

RUN pip3 install \
    pyethereum \
    ethereum \
    trinity \
    populus \
    py-evm \
    vyper
    
RUN curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh -s -- -y
RUN curl https://get.parity.io -L | sh -s --  -r stable

RUN npm install -g \
    ganache-cli \
    truffle \
    solc \
    truper

RUN npm install -g --unsafe-perm ijavascript protocol-buffers
 
RUN useradd -ms /bin/bash codete
RUN adduser codete sudo

USER codete

RUN ijsinstall --install=local

EXPOSE 8545
EXPOSE 8888
EXPOSE 9000
EXPOSE 3000

WORKDIR /home/codete/
RUN mkdir /home/codete/workshop/

CMD jupyter-notebook --ip=0.0.0.0 --NotebookApp.token='' --NotebookApp.password='' --no-browser --notebook-dir=/home/codete/workshop/
