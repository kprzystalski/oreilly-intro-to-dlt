# Prerequirements

Please install Docker that is mandatory for most exercises:

- Linux: [https://docs.docker.com/v17.12/cs-engine/1.13/](https://docs.docker.com/v17.12/cs-engine/1.13/)
- Windows: [https://docs.docker.com/docker-for-windows/install/](https://docs.docker.com/docker-for-windows/install/)
- Mac: [https://docs.docker.com/docker-for-mac/install/](https://docs.docker.com/docker-for-mac/install/)

# Presentation

Slides are placed in the main directory of this repository.

# Ethereum

Setup steps for Ethereum:

1. Get the docker image from https://hub.docker.com/r/kprzystalski/dlt-ethereum. You can pull it:

``docker pull kprzystalski/dlt-ethereum``

2. If you would like to modify the image, the Dockerfile is placed in Ethereum directory of this repository.

The examples are pulled in the container.

To run the container use the following command:

``docker run -it -p 8888:8888 -p 8545:8545 -p 3000:3000 kprzystalski/dlt-ethereum``

To access the bash on this container please find the container ID with ``docker ps`` and execute the bash command on this container with ``docker exec <CONTAINER> /bin/bash``.

# Fabric

We have the example based on Hyperledger Fabric. The installation of Hyperledger Fabric consist of one simple step:
``curl -sSL http://bit.ly/2ysbOFE | bash -s -- 1.4.2 1.4.2 0.4.15``.

# Corda

Just pull the prepared Corda docker image ``https://hub.docker.com/r/kprzystalski/dlt-corda``:

``docker pull kprzystalski/dlt-corda``.

The examples are already on the container, but you need to map the ports:

``docker run -it -p 8000:8000 kprzystalski/dlt-corda``.

You can access the bash in the same way like we did in the Ethereum example.

## Further reading

1. Mastering Ethereum, Gavin Wood, Andreas M. Antonopoulos. O'Reilly 2018

2. Hyperledger Fabric and Composer - First Practical Blockchain, Dr. Mohammad Nauman. Packt 2018

3. R3 Corda for Architects and Developers : With Case Studies in Finance, Insurance, Healthcare, Travel, Telecom, and Agriculture, Debajani Mohanty. Apress 2019
