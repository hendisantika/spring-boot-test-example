package com.hendisantika.springboottestexample.service;

import com.hendisantika.springboottestexample.domain.VehicleIdentificationNumber;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/17
 * Time: 07.20
 * To change this template use File | Settings | File Templates.
 */
public class VehicleIdentificationNumberNotFoundException extends RuntimeException {
    private VehicleIdentificationNumber vehicleIdentificationNumber;

    public VehicleIdentificationNumberNotFoundException(VehicleIdentificationNumber vin) {
        this(vin, null);
    }

    public VehicleIdentificationNumberNotFoundException(VehicleIdentificationNumber vin,
                                                        Throwable cause) {
        super("Unable to find VehicleIdentificationNumber " + vin, cause);
        this.vehicleIdentificationNumber = vin;
    }

    public VehicleIdentificationNumber getVehicleIdentificationNumber() {
        return this.vehicleIdentificationNumber;
    }
}
