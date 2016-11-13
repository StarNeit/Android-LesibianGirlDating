package com.lesgirls.utils;

import android.net.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by victor on 26.06.16.
 */
public class DateOperator {
    public static final String dateFormat = "yyyy-MM-dd hh:mm:ss";
    public static final String dateFormatShort = "yyyy-MM-dd hh:mm";
    public static final String dateFormatSuperShort = "yyyy-MM-dd";
    public static Date StringToDate(String s){

        String ss = s.replace("/","-");

        SimpleDateFormat simpleDate = new SimpleDateFormat(dateFormatShort);

        switch (s.length()){
            case 10: {simpleDate = new SimpleDateFormat(dateFormatSuperShort);break;}
            case 16: {simpleDate = new SimpleDateFormat(dateFormatShort);break;}
            case 19: {simpleDate = new SimpleDateFormat(dateFormat);break;}
        }

        Date convertedDate = new Date();
        try {
            convertedDate = simpleDate.parse(ss);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }
    public static String DateToString(Date date){
        SimpleDateFormat simpleDate =  new SimpleDateFormat(dateFormatShort);
        return simpleDate.format(date);
    }
}

