package com.im.vent.utils;

import java.text.SimpleDateFormat;

/**
 * Created by hecy on 2018/7/17.
 */
public class DateUtils {

    public static  String yyyyMMdd = "yyyy-MM-dd";
    public static  String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static  SimpleDateFormat yyyyMMddHHmmssSDF = new SimpleDateFormat(yyyyMMddHHmmss);
    public static  SimpleDateFormat yyyyMMddSDF = new SimpleDateFormat(yyyyMMdd);


    public static void main(String[] args) {
        String urlPrefix = "http://activitytest.minshenglife.com/stargate_i/rest/casasda";
        urlPrefix =  urlPrefix.substring(urlPrefix.indexOf("/stargate") );
        System.out.println(urlPrefix);

    }

}
