package com.example.mydoctorapp.utils;

import com.example.mydoctorapp.enumerations.DateFormatPattern;
import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.example.mydoctorapp.constants.Constants.EUROPEAN_TIMEZONE;

@UtilityClass
public class MyDoctorAppUtils {

    public static String getCurrentTimeInGMT3() {
        return DateTimeFormatter.ofPattern(DateFormatPattern.GMT_FORMAT.getPattern())
                .withZone(ZoneId.of(EUROPEAN_TIMEZONE))
                .format(Instant.now());
    }

    public static String getCurrentTimeInGMT3OnlyDate() {
        return DateTimeFormatter.ofPattern(DateFormatPattern.GMT_FORMAT_ONLY_DATE.getPattern())
                .withZone(ZoneId.of(EUROPEAN_TIMEZONE))
                .format(Instant.now());
    }

    public static List<String> reduceDuplicatesIds(List<String> strings) {
        HashSet<String> set = new HashSet<>(strings);
        return new ArrayList<>(set);
    }

}
