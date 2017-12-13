package com.hendisantika.springboottestexample.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/17
 * Time: 07.22
 * To change this template use File | Settings | File Templates.
 */

@Component
@ConfigurationProperties
public class ServiceProperties {
    private String vehicleServiceRootUrl = "http://localhost:8080/vs";

    public String getVehicleServiceRootUrl() {
        return this.vehicleServiceRootUrl;
    }

    public void setVehicleServiceRootUrl(String vehicleServiceRootUrl) {
        this.vehicleServiceRootUrl = vehicleServiceRootUrl;
    }
}
