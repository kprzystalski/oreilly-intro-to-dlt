/*
 * SPDX-License-Identifier: Apache-2.0
 */

package com.codete.fabric.examples.oltcar;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;
import org.hyperledger.fabric.shim.ledger.KeyModification;

import com.owlike.genson.Genson;

@Contract(
        name = "OLTCar",
        info = @Info(
                title = "OLTCar contract",
                description = "OLT example contract",
                version = "0.0.1",
                license = @License(
                        name = "Apache 2.0 License",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"),
                contact = @Contact(
                        email = "karol@codete.com",
                        name = "Karol Przystalski",
                        url = "https://www.codete.com")))

@Default
public final class OLTCar implements ContractInterface {

    private final Genson genson = new Genson();

    /**
     * Creates a new car on the ledger.
     *
     * @param ctx the transaction context
     * @param vin the vin for the new car
     * @param mileage the make of the new car
     * @param repairName the model of the new car
     * @return the created Car entry
     */
    @Transaction()
    public Car addCarRepair(final Context ctx, final String vin, final Integer mileage, final String repairName) {
        ChaincodeStub stub = ctx.getStub();

        Car car = new Car(mileage, repairName);
        String carState = genson.serialize(car);
        stub.putStringState(vin, carState);

        return car;
    }

    /**
     * Retrieves every car history.
     *
     * @param ctx the transaction context
     * @return array of Cars events found on the ledger
     */
    @Transaction()
    public Car[] queryCarsHistory(final Context ctx, final String vin) {
        ChaincodeStub stub = ctx.getStub();

        List<Car> cars = new ArrayList<Car>();

        QueryResultsIterator<KeyModification> results = stub.getHistoryForKey(vin);

        Iterator<KeyModification> iter = results.iterator();
        while (iter.hasNext()) {
          Car car = genson.deserialize(iter.next().getStringValue(), Car.class);
          cars.add(car);
        }

        Car[] response = cars.toArray(new Car[cars.size()]);

        return response;
    }

}
