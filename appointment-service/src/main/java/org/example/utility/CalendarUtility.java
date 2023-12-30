package org.example.utility;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@UtilityClass
public class CalendarUtility {

    /** Not the best practice but for my project is acceptable :) */
     public static List<String> generateWorkingDates(String startDateStr, String endDateStr) {
        List<String> workingDates = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Calendar calendar = Calendar.getInstance();

        try {
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            calendar.setTime(startDate);

            while (calendar.getTime().before(endDate) || calendar.getTime().equals(endDate)) {
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

                if (dayOfWeek >= Calendar.MONDAY && dayOfWeek <= Calendar.FRIDAY &&
                        hourOfDay >= 9 && hourOfDay < 15) {
                    workingDates.add(dateFormat.format(calendar.getTime()));
                }

                calendar.add(Calendar.MINUTE, 60);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return workingDates;
    }

    public static String formatDate(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(millis));
    }
}
