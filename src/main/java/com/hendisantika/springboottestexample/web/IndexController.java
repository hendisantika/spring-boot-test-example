package com.hendisantika.springboottestexample.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 15/12/17
 * Time: 09.20
 * To change this template use File | Settings | File Templates.
 */

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping
    Map index() {
        Map data = new HashMap();

        data.put("message", "Hello Guys!");
        data.put("topic", "Migrate From Eclipse to IntelliJ IDEA");
        data.put("Date", new Date());

        return data;
    }
}
