package com.limaefdua.bootcamp.utils;
//
// Created by maftuhin on 10/24/2019.
//

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateUtils {
    private Calendar c = Calendar.getInstance();
    private static String defFormat = "dd MMM yyyy HH:mm";
    private static SimpleDateFormat sdf = new SimpleDateFormat(defFormat, Locale.ROOT);

    public String getDateNow(){
        return sdf.format(c.getTime());
    }
}
