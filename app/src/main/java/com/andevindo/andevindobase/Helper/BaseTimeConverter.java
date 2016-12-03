package com.andevindo.andevindobase.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by heendher on 6/25/2016.
 */
public class BaseTimeConverter {

    private static final String sLDCalendar = "dd-MM-yyyy";
    private static final String sLTCalendar = "k:m";
    private static final String sLD = "dd MMM yyyy";
    private static final String sLT = "kk:mm";
    private static final String sLDT = "dd MMM yyyy kk:mm";
    private static final String sSDT = "yyyy-MM-dd HH:mm:ss";
    private static final String sSD = "yyyy-MM-dd";

    public static int getDifferentDay(String startDate, String endDate){
        long different;
        try {
            different = new SimpleDateFormat(sLD).parse(endDate).getTime() - new SimpleDateFormat(sLD).parse(startDate).getTime();
            return (int)different/(1000*60*60*24);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String formatDateFromNewDate(Date date) {
        SimpleDateFormat localDateFormat = new SimpleDateFormat(sLDT);
        return localDateFormat.format(date).toString();
    }

    public static String fromLDCalendarToLD(String srcDate) {
        SimpleDateFormat calendarDateFormat = new SimpleDateFormat(sLDCalendar);
        SimpleDateFormat localDateFormat = new SimpleDateFormat(sLD);
        try {
            String date = localDateFormat.format(calendarDateFormat.parse(srcDate));
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String fromLTCalendarToLT(String srcDate) {
        SimpleDateFormat calendarTimeFormat = new SimpleDateFormat(sLTCalendar);
        SimpleDateFormat localTimeFormat = new SimpleDateFormat(sLT);
        try {
            String time = localTimeFormat.format(calendarTimeFormat.parse(srcDate));
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }


    public static String fromSDTToLDT(String srcDate) {
        SimpleDateFormat serverDateFormat = new SimpleDateFormat(sSDT);
        SimpleDateFormat localDateFormat = new SimpleDateFormat(sLDT);
        try {
            String date = localDateFormat.format(serverDateFormat.parse(srcDate));
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String fromLDToSD(String srcDate) {
        SimpleDateFormat serverDateFormat = new SimpleDateFormat(sSD);
        SimpleDateFormat localDateFormat = new SimpleDateFormat(sLD);
        try {
            String date = serverDateFormat.format(localDateFormat.parse(srcDate));
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String fromLDTToSDT(String srcDate) {
        SimpleDateFormat serverDateFormat = new SimpleDateFormat(sSDT);
        SimpleDateFormat localDateFormat = new SimpleDateFormat(sLDT);
        try {
            String date = serverDateFormat.format(localDateFormat.parse(srcDate));
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getLDFromLDT(String srcDate) {
        SimpleDateFormat localDateFormat = new SimpleDateFormat(sLD);
        SimpleDateFormat localFormat = new SimpleDateFormat(sLDT);
        try {
            String date = localDateFormat.format(localFormat.parse(srcDate));
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getLTFromLDT(String srcDate) {
        SimpleDateFormat localTimeFormat = new SimpleDateFormat(sLT);
        SimpleDateFormat localFormat = new SimpleDateFormat(sLDT);
        try {
            String date = localTimeFormat.format(localFormat.parse(srcDate));
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean isDifferentDay(String todayDate, String yesterdayDate) {
        Long todayD;
        Long yesterdayD;
        SimpleDateFormat localDateFormat = new SimpleDateFormat(sLD);
        try {
            todayD = localDateFormat.parse(todayDate).getTime();
            yesterdayD = localDateFormat.parse(yesterdayDate).getTime();
            return (todayD > yesterdayD);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

}