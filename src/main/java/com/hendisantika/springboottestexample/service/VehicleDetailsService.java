package com.hendisantika.springboottestexample.service;

import com.hendisantika.springboottestexample.domain.VehicleIdentificationNumber;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/17
 * Time: 07.18
 * To change this template use File | Settings | File Templates.
 */

@Service
public interface VehicleDetailsService {
    VehicleDetails getVehicleDetails(VehicleIdentificationNumber vin)
            throws VehicleIdentificationNumberNotFoundException;
}
