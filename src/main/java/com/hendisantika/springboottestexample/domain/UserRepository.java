package com.hendisantika.springboottestexample.domain;

import org.springframework.data.repository.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/17
 * Time: 07.16
 * To change this template use File | Settings | File Templates.
 */
public interface UserRepository extends Repository<User, Long> {
    User findByUsername(String username);
}
