package com.hendisantika.springboottestexample.web;

import com.hendisantika.springboottestexample.service.VehicleDetails;
import com.hendisantika.springboottestexample.service.VehicleIdentificationNumberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/17
 * Time: 07.29
 * To change this template use File | Settings | File Templates.
 */

@RestController
public class UserVehicleController {
    private UserVehicleService userVehicleService;

    public UserVehicleController(UserVehicleService userVehicleService) {
        this.userVehicleService = userVehicleService;
    }

    @GetMapping(path = "/{username}/vehicle", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getVehicleDetailsText(@PathVariable String username) {
        VehicleDetails details = this.userVehicleService.getVehicleDetails(username);
        return details.getMake() + " " + details.getModel();
    }

    @GetMapping(path = "/{username}/vehicle", produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleDetails VehicleDetailsJson(@PathVariable String username) {
        return this.userVehicleService.getVehicleDetails(username);
    }

    @GetMapping(path = "/{username}/vehicle.html", produces = MediaType.TEXT_HTML_VALUE)
    public String VehicleDetailsHtml(@PathVariable String username) {
        VehicleDetails details = this.userVehicleService.getVehicleDetails(username);
        String makeAndModel = details.getMake() + " " + details.getModel();
        return "<html><body><h1>" + makeAndModel + "</h1></body></html>";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void handleVinNotFound(VehicleIdentificationNumberNotFoundException ex) {
    }
}
