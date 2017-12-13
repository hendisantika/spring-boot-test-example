package com.hendisantika.springboottestexample.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/17
 * Time: 07.28
 * To change this template use File | Settings | File Templates.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNameNotFoundException extends RuntimeException {
    private String username;

    public UserNameNotFoundException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }
}
