curl -sSL http://bit.ly/2ysbOFE | bash -s -- 1.4.2 1.4.2 0.4.15

PATH=${PATH}:`pwd`/fabric-samples/bin/

FABRIC_CFG_PATH=${PATH}

mkdir -p config
mkdir -p crypto-config

cryptogen generate --config=./crypto-config.yaml

configtxgen -profile OneOrgOrdererGenesis -outputBlock ./config/genesis.block

configtxgen -profile OneOrgChannel -outputCreateChannelTx ./config/channel.tx -channelID channelone

configtxgen -profile OneOrgChannel -outputAnchorPeersUpdate ./config/Org1MSPanchors.tx -channelID channelone -asOrg Org1MSP

docker-compose -p network  up -d
sleep 20
docker exec -e "CORE_PEER_LOCALMSPID=Org1MSP" -e "CORE_PEER_MSPCONFIGPATH=/etc/hyperledger/msp/users/Admin@org1.codete.com/msp" peer0.org1.codete.com peer channel create -o orderer.codete.com:7050 -c channelone -f /etc/hyperledger/configtx/channel.tx
docker exec -e "CORE_PEER_LOCALMSPID=Org1MSP" -e "CORE_PEER_MSPCONFIGPATH=/etc/hyperledger/msp/users/Admin@org1.codete.com/msp" peer0.org1.codete.com peer channel join -b channelone.block
