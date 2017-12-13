package com.hendisantika.springboottestexample.web;

import com.hendisantika.springboottestexample.domain.User;
import com.hendisantika.springboottestexample.domain.UserRepository;
import com.hendisantika.springboottestexample.service.VehicleDetails;
import com.hendisantika.springboottestexample.service.VehicleDetailsService;
import com.hendisantika.springboottestexample.service.VehicleIdentificationNumberNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/17
 * Time: 07.27
 * To change this template use File | Settings | File Templates.
 */

@Component
public class UserVehicleService {

    private final UserRepository userRepository;

    private final VehicleDetailsService vehicleDetailsService;

    public UserVehicleService(UserRepository userRepository,
                              VehicleDetailsService vehicleDetailsService) {
        this.userRepository = userRepository;
        this.vehicleDetailsService = vehicleDetailsService;
    }

    public VehicleDetails getVehicleDetails(String username)
            throws UserNameNotFoundException,
            VehicleIdentificationNumberNotFoundException {
        Assert.notNull(username, "Username must not be null");
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNameNotFoundException(username);
        }
        return this.vehicleDetailsService.getVehicleDetails(user.getVin());
    }
}
