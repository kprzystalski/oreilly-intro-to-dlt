/*
 * SPDX-License-Identifier: Apache-2.0
 */

package com.codete.fabric.examples.oltcar;

import java.util.Objects;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import com.owlike.genson.annotation.JsonProperty;

@DataType()
public final class Car {

    @Property()
    private final Integer mileage;

    @Property()
    private final String repairName;


    public String getMileage() {
        return Integer.toString(mileage);
    }

    public String getRepairName() {
        return repairName;
    }

    public Car(@JsonProperty("mileage") final Integer mileage, @JsonProperty("repairName") final String repairName) {
        this.mileage = mileage;
        this.repairName = repairName;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        Car other = (Car) obj;

        return Objects.deepEquals(new String[] {getMileage(), getRepairName()},
                new String[] {other.getMileage(), other.getRepairName()});
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMileage(), getRepairName());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode())
         + " [mileage=" + Integer.toString(mileage) + ", repairName=" + repairName + "]";
    }
}
