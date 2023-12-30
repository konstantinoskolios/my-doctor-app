package org.example.utils;

import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@UtilityClass
public class PaymentUtil {

    public static String formatDate(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(millis));
    }

    public static List<String> reduceDuplicatesIds(List<String> strings) {
        HashSet<String> set = new HashSet<>(strings);
        return new ArrayList<>(set);
    }
}
