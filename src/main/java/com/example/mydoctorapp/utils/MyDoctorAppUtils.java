package com.example.mydoctorapp.utils;

import com.example.mydoctorapp.enumerations.DateFormatPattern;
import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class MyDoctorAppUtils {

    public static String getCurrentTimeInGMT3() {
        return DateTimeFormatter.ofPattern(DateFormatPattern.GMT_FORMAT.getPattern())
                .withZone(ZoneId.of("GMT+3"))
                .format(Instant.now());
    }
}
