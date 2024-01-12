package com.example.mydoctorapp.utils;

import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.example.mydoctorapp.constants.Constants.EUROPEAN_TIMEZONE;
import static com.example.mydoctorapp.enumerations.DateFormatPattern.GMT_FORMAT;

@UtilityClass
public class MyDoctorAppUtils {

    public static String getCurrentTimeInGMT3() {
        return DateTimeFormatter.ofPattern(GMT_FORMAT.getPattern())
                .withZone(ZoneId.of(EUROPEAN_TIMEZONE))
                .format(Instant.now());
    }

    public static List<String> reduceDuplicatesIds(List<String> strings) {
        HashSet<String> set = new HashSet<>(strings);
        return new ArrayList<>(set);
    }
}
