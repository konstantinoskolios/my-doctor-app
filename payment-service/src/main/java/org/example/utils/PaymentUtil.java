package org.example.utils;

import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@UtilityClass
public class PaymentUtil {

    public static String formatDate(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(millis));
    }
}
