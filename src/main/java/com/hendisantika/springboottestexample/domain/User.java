package com.hendisantika.springboottestexample.domain;

import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/17
 * Time: 07.11
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private VehicleIdentificationNumber vin;

    protected User() {
    }

    public User(String username, VehicleIdentificationNumber vin) {
        Assert.hasLength(username, "Username must not be empty");
        Assert.notNull(vin, "VIN must not be null");
        this.username = username;
        this.vin = vin;
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public VehicleIdentificationNumber getVin() {
        return this.vin;
    }

}
