package com.hendisantika.springboottestexample.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/17
 * Time: 07.14
 * To change this template use File | Settings | File Templates.
 */

@Converter(autoApply = true)
public class VehicleIdentificationNumberAttributeConverter implements AttributeConverter<VehicleIdentificationNumber, String> {
    @Override
    public String convertToDatabaseColumn(VehicleIdentificationNumber attribute) {
        return attribute.toString();
    }

    @Override
    public VehicleIdentificationNumber convertToEntityAttribute(String dbData) {
        return new VehicleIdentificationNumber(dbData);
    }
}
