package org.example;

import org.example.config.MicroserviceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties(value = {
        MicroserviceProperties.class
})
public class PaymentServiceApp {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+3"));
        SpringApplication.run(PaymentServiceApp.class, args);
    }
}