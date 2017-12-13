package com.hendisantika.springboottestexample.domain;

import org.springframework.util.Assert;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/17
 * Time: 07.13
 * To change this template use File | Settings | File Templates.
 */
public final class VehicleIdentificationNumber {
    private String vin;

    public VehicleIdentificationNumber(String vin) {
        Assert.notNull(vin, "VIN must not be null");
        Assert.isTrue(vin.length() == 17, "VIN must be exactly 17 characters");
        this.vin = vin;
    }

    @Override
    public int hashCode() {
        return this.vin.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this.vin.equals(((VehicleIdentificationNumber) obj).vin);
    }

    @Override
    public String toString() {
        return this.vin;
    }

}
