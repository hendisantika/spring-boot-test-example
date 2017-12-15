package com.hendisantika.springboottestexample.service;

import com.hendisantika.springboottestexample.domain.VehicleIdentificationNumber;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpServerErrorException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/17
 * Time: 07.43
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringRunner.class)
@RestClientTest({RemoteVehicleDetailsService.class, ServiceProperties.class})
public class RemoteVehicleDetailsServiceTest {
    private static final String VIN = "00000000000000000";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private RemoteVehicleDetailsService service;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void getVehicleDetailsWhenVinIsNullShouldThrowException() {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("VIN must not be null");
        this.service.getVehicleDetails(null);
    }

    @Test
    public void getVehicleDetailsWhenResultIsSuccessShouldReturnDetails() {
        this.server.expect(requestTo("/vehicle/" + VIN + "/details"))
                .andRespond(withSuccess(getClassPathResource("/sample/test/service/vehicledetails.json"),
                        MediaType.APPLICATION_JSON));
        VehicleDetails details = this.service
                .getVehicleDetails(new VehicleIdentificationNumber(VIN));
        assertThat(details.getMake()).isEqualTo("Honda");
        assertThat(details.getModel()).isEqualTo("Civic");
    }

    @Test
    public void getVehicleDetailsWhenResultIsNotFoundShouldThrowException() {
        this.server.expect(requestTo("/vehicle/" + VIN + "/details"))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));
        this.thrown.expect(VehicleIdentificationNumberNotFoundException.class);
        this.service.getVehicleDetails(new VehicleIdentificationNumber(VIN));
    }

    @Test
    public void getVehicleDetailsWhenResultIServerErrorShouldThrowException() {
        this.server.expect(requestTo("/vehicle/" + VIN + "/details"))
                .andRespond(withServerError());
        this.thrown.expect(HttpServerErrorException.class);
        this.service.getVehicleDetails(new VehicleIdentificationNumber(VIN));
    }

    private ClassPathResource getClassPathResource(String path) {
        return new ClassPathResource(path, getClass());
    }

}