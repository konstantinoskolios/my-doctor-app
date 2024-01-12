package com.example.mydoctorapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.util.TimeZone;

import static com.example.mydoctorapp.constants.Constants.EUROPEAN_TIMEZONE;

@SpringBootApplication
@PropertySource("classpath:.env")
public class MyDoctorAppApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone(EUROPEAN_TIMEZONE));
        SpringApplication.run(MyDoctorAppApplication.class, args);
    }

}
