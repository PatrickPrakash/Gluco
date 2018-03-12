package com.projectx.gluco.Fragments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Patrick Prakash on 10-Mar-18.
 */

public class DateBuilder {
    private static final int DATE_STRING_TYPE = 0;
    private static final int DATE_DATE_TYPE = 1;


    private int DATE_TYPE;
    private String dateString = null;
    private Date date = null;


    public DateBuilder(String dateString) {
        this.dateString = dateString;
        this.DATE_TYPE = DATE_STRING_TYPE;
    }

    public DateBuilder(Date date) {
        this.date = date;
        this.DATE_TYPE = DATE_DATE_TYPE;
    }

    /**
     * @param dateFloatVal
     * @return
     */
    public static Date getDate(float dateFloatVal) throws ParseException {
        Date date;

        String dateStr = String.valueOf(dateFloatVal).split("\\.")[0];
        StringBuilder sb = new StringBuilder();
        sb.append(dateStr.substring(0, 2));
        sb.append("-");
        sb.append(dateStr.substring(2, 4));
        sb.append("-");
        sb.append(dateStr.substring(4, 6));

        dateStr = sb.toString();
        date = new SimpleDateFormat("yy-MM-dd").parse(dateStr);

        return date;
    }

    public static String getDate(float dateFloatVal, SimpleDateFormat dateFormat) throws ParseException {
        Date date;
        String dateStr = String.valueOf(dateFloatVal).split("\\.")[0];
        StringBuilder sb = new StringBuilder();
        sb.append(dateStr.substring(0, 2));
        sb.append("-");
        sb.append(dateStr.substring(2, 4));
        sb.append("-");
        sb.append(dateStr.substring(4, 6));

        dateStr = sb.toString();

        date = new SimpleDateFormat("yy-MM-dd").parse(dateStr);

        dateStr = dateFormat.format(date);
        return dateStr;
    }

    /**
     * will return as float of yymmdd.
     *
     * @return date
     */
    public float toFloat() throws ParseException {
        String dateStr;
        Date date;
        float dateFloatVal = -1;


        if (DATE_TYPE == DATE_DATE_TYPE) {
            dateStr = new SimpleDateFormat("yy-MM-dd").format(this.date);
        } else {
            date = new SimpleDateFormat("dd-MM-yy").parse(dateString);
            dateStr = new SimpleDateFormat("yy-MM-dd").format(date);
        }

        dateStr = dateStr.trim();
        dateStr = dateStr.replace("-", "");
        dateFloatVal = Float.parseFloat(dateStr);

        return dateFloatVal;
    }


}
