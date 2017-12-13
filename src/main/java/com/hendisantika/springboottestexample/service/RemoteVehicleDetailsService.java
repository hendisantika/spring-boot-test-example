package com.hendisantika.springboottestexample.service;

import com.hendisantika.springboottestexample.domain.VehicleIdentificationNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

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
public class RemoteVehicleDetailsService implements VehicleDetailsService {

    private static final Logger logger = LoggerFactory
            .getLogger(RemoteVehicleDetailsService.class);

    private final RestTemplate restTemplate;

    public RemoteVehicleDetailsService(ServiceProperties properties,
                                       RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .rootUri(properties.getVehicleServiceRootUrl()).build();
    }

    @Override
    public VehicleDetails getVehicleDetails(VehicleIdentificationNumber vin)
            throws VehicleIdentificationNumberNotFoundException {
        Assert.notNull(vin, "VIN must not be null");
        logger.debug("Retrieving vehicle data for: " + vin);
        try {
            return this.restTemplate.getForObject("/vehicle/{vin}/details",
                    VehicleDetails.class, vin);
        } catch (HttpStatusCodeException ex) {
            if (HttpStatus.NOT_FOUND.equals(ex.getStatusCode())) {
                throw new VehicleIdentificationNumberNotFoundException(vin, ex);
            }
            throw ex;
        }
    }
}
