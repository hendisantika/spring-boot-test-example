package com.hendisantika.springboottestexample.web;

import com.hendisantika.springboottestexample.domain.User;
import com.hendisantika.springboottestexample.domain.UserRepository;
import com.hendisantika.springboottestexample.domain.VehicleIdentificationNumber;
import com.hendisantika.springboottestexample.service.VehicleDetails;
import com.hendisantika.springboottestexample.service.VehicleDetailsService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/17
 * Time: 07.55
 * To change this template use File | Settings | File Templates.
 */
public class UserVehicleServiceTest {
    private static final VehicleIdentificationNumber VIN = new VehicleIdentificationNumber(
            "00000000000000000");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private VehicleDetailsService vehicleDetailsService;

    @Mock
    private UserRepository userRepository;

    private UserVehicleService service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.service = new UserVehicleService(this.userRepository,
                this.vehicleDetailsService);
    }

    @Test
    public void getVehicleDetailsWhenUsernameIsNullShouldThrowException() {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Username must not be null");
        this.service.getVehicleDetails(null);
    }

    @Test
    public void getVehicleDetailsWhenUsernameNotFoundShouldThrowException() {
        given(this.userRepository.findByUsername(anyString())).willReturn(null);
        this.thrown.expect(UserNameNotFoundException.class);
        this.service.getVehicleDetails("sboot");
    }

    @Test
    public void getVehicleDetailsShouldReturnMakeAndModel() {
        given(this.userRepository.findByUsername(anyString()))
                .willReturn(new User("sboot", VIN));
        VehicleDetails details = new VehicleDetails("Honda", "Civic");
        given(this.vehicleDetailsService.getVehicleDetails(VIN)).willReturn(details);
        VehicleDetails actual = this.service.getVehicleDetails("sboot");
        assertThat(actual).isEqualTo(details);
    }


}