package com.hendisantika.springboottestexample.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/17
 * Time: 07.45
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringRunner.class)
@JsonTest
public class VehicleDetailsJsonTest {
    @Autowired
    private JacksonTester<VehicleDetails> json;

    @Test
    public void serializeJson() throws Exception {
        VehicleDetails details = new VehicleDetails("Honda", "Civic");
        assertThat(this.json.write(details)).isEqualTo("vehicledetails.json");
        assertThat(this.json.write(details)).isEqualToJson("vehicledetails.json");
        assertThat(this.json.write(details)).hasJsonPathStringValue("@.make");
        assertThat(this.json.write(details)).extractingJsonPathStringValue("@.make")
                .isEqualTo("Honda");
    }

    @Test
    public void deserializeJson() throws Exception {
        String content = "{\"make\":\"Ford\",\"model\":\"Focus\"}";
        assertThat(this.json.parse(content))
                .isEqualTo(new VehicleDetails("Ford", "Focus"));
        assertThat(this.json.parseObject(content).getMake()).isEqualTo("Ford");
    }

}