package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.TimeZone;

@SpringBootApplication
@EnableEurekaClient
public class PaymentServiceApp {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+3"));
        SpringApplication.run(PaymentServiceApp.class, args);
    }
}