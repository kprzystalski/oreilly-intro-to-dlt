FROM hyperledger/fabric-tools:1.4

RUN apt update && apt-get install -y python3-pip

RUN pip3 install --upgrade pip

RUN pip3 install jupyterlab

EXPOSE 8888

RUN useradd -ms /bin/bash codete
RUN adduser codete sudo
USER codete
WORKDIR /home/codete/
RUN mkdir /home/codete/workshop/

CMD jupyter-notebook --ip=0.0.0.0 --NotebookApp.token='' --NotebookApp.password='' --no-browser --notebook-dir=/home/codete/workshop/
