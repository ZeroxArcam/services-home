package com.pragma.hogar360.serviceshome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServicesHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicesHomeApplication.class, args);
    }

}
