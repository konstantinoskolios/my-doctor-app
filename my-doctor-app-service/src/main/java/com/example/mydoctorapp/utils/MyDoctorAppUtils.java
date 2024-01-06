package com.example.mydoctorapp.utils;

import com.example.mydoctorapp.enumerations.DateFormatPattern;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static com.example.mydoctorapp.constants.Constants.EUROPEAN_TIMEZONE;
import static com.example.mydoctorapp.enumerations.DateFormatPattern.GMT_FORMAT_ONLY_DATE;

@UtilityClass
public class MyDoctorAppUtils {

    public static String getCurrentTimeInGMT3() {
        return DateTimeFormatter.ofPattern(DateFormatPattern.GMT_FORMAT.getPattern())
                .withZone(ZoneId.of(EUROPEAN_TIMEZONE))
                .format(Instant.now());
    }

    @SneakyThrows
    public static Date getCurrentTimeInGMT3OnlyDate() {
        var formattedDate = DateTimeFormatter.ofPattern(GMT_FORMAT_ONLY_DATE.getPattern())
                .withZone(ZoneId.of(EUROPEAN_TIMEZONE))
                .format(Instant.now());

        SimpleDateFormat sdf = new SimpleDateFormat(GMT_FORMAT_ONLY_DATE.getPattern());
        return sdf.parse(formattedDate);
    }

    public static List<String> reduceDuplicatesIds(List<String> strings) {
        HashSet<String> set = new HashSet<>(strings);
        return new ArrayList<>(set);
    }
}
