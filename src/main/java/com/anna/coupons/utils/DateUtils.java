package com.anna.coupons.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtils {
	
	public static String dateToStr(GregorianCalendar date) {
		
		String string =  date.get(Calendar.DAY_OF_MONTH) +"/"+ (date.get(Calendar.MONTH)+1) + "/" + date.get(Calendar.YEAR);
		
		return string;
		
		
	}

}
