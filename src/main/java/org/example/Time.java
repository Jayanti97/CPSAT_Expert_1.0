package org.example;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Time {

    public static String currentMonth;
    public static String currentMonthFullName;
    public static int currentYear;
    public static String last2DigitsOfYear;
    public static String monthIndex;
    public static String dateToday;

    public static long get_millisecs() {
        Date date = new Date();

        long mills = date.getTime();
        return mills;
    }

    public static void calenderInfo() {
        Calendar calendar = Calendar.getInstance();
        currentYear = calendar.getWeekYear();
        monthIndex = "" + (calendar.get(Calendar.MONTH) + 1);
        if (monthIndex.length() == 1)
            monthIndex = "0" + monthIndex;
        String currentTime = calendar.getTime().toString();
        currentMonth = new SimpleDateFormat("MMM").format(calendar.getTime());
        currentMonthFullName = new SimpleDateFormat("MMMMM").format(calendar.getTime());
        last2DigitsOfYear = String.valueOf(Time.currentYear);
        last2DigitsOfYear = last2DigitsOfYear.substring(last2DigitsOfYear.length() - 2);
        dateToday = new SimpleDateFormat("d MMMMM").format(calendar.getTime());
    }

    @Test
    public void test() {
        Time.calenderInfo();
        System.out.println(Time.dateToday);
    }



}
