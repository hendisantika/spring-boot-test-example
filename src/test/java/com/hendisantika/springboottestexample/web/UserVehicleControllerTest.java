package com.hendisantika.springboottestexample.web;

import com.hendisantika.springboottestexample.WelcomeCommandLineRunner;
import com.hendisantika.springboottestexample.domain.VehicleIdentificationNumber;
import com.hendisantika.springboottestexample.service.VehicleDetails;
import com.hendisantika.springboottestexample.service.VehicleIdentificationNumberNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/17
 * Time: 07.52
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(UserVehicleController.class)
public class UserVehicleControllerTest {
    private static final VehicleIdentificationNumber VIN = new VehicleIdentificationNumber(
            "00000000000000000");

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private UserVehicleService userVehicleService;

    @Test
    public void getVehicleWhenRequestingTextShouldReturnMakeAndModel() throws Exception {
        given(this.userVehicleService.getVehicleDetails("sboot"))
                .willReturn(new VehicleDetails("Honda", "Civic"));
        this.mvc.perform(get("/sboot/vehicle").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk()).andExpect(content().string("Honda Civic"));
    }

    @Test
    public void getVehicleWhenRequestingJsonShouldReturnMakeAndModel() throws Exception {
        given(this.userVehicleService.getVehicleDetails("sboot"))
                .willReturn(new VehicleDetails("Honda", "Civic"));
        this.mvc.perform(get("/sboot/vehicle").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'make':'Honda','model':'Civic'}"));
    }

    @Test
    public void getVehicleWhenRequestingHtmlShouldReturnMakeAndModel() throws Exception {
        given(this.userVehicleService.getVehicleDetails("sboot"))
                .willReturn(new VehicleDetails("Honda", "Civic"));
        this.mvc.perform(get("/sboot/vehicle.html").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<h1>Honda Civic</h1>")));
    }

    @Test
    public void getVehicleWhenUserNotFoundShouldReturnNotFound() throws Exception {
        given(this.userVehicleService.getVehicleDetails("sboot"))
                .willThrow(new UserNameNotFoundException("sboot"));
        this.mvc.perform(get("/sboot/vehicle")).andExpect(status().isNotFound());
    }

    @Test
    public void getVehicleWhenVinNotFoundShouldReturnNotFound() throws Exception {
        given(this.userVehicleService.getVehicleDetails("sboot"))
                .willThrow(new VehicleIdentificationNumberNotFoundException(VIN));
        this.mvc.perform(get("/sboot/vehicle")).andExpect(status().isNotFound());
    }

    @Test(expected = NoSuchBeanDefinitionException.class)
    public void welcomeCommandLineRunnerShouldBeAvailable() {
        // Since we're a @WebMvcTest WelcomeCommandLineRunner should not be available.
        assertThat(this.applicationContext.getBean(WelcomeCommandLineRunner.class));
    }


}