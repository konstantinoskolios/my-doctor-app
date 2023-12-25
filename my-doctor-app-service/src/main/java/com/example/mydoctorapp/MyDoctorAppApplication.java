package com.example.mydoctorapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
//@EnableEurekaClient
public class MyDoctorAppApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+3"));
        SpringApplication.run(MyDoctorAppApplication.class, args);
    }

}
