package com.lesgirls.utils;

/**
 * Created by victor on 23.07.16.
 */
public class LesGirlsUtil {
    public static String SexTypeToText(int type){
        String result = "Undefined";
        switch (type){
            case 0:{result = "Lesbian";break;}
            case 1:{result = "Bisexual";break;}
            case 2:{result = "Pansexual";break;}
        }
        return result;
    }
}
