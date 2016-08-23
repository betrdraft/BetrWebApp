package com.betr.server.util;

import java.util.Calendar;

public class TimeUtils {

    private static final String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December" };
    
    public static String getCurrentMonthName() {
    	 Calendar cal = Calendar.getInstance();
    	  return monthName[cal.get(Calendar.MONTH)];
    }
	
}
