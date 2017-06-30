package com.component.CreditComponent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CheckOperations {
	public static int getmonth(){
		Date date= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		return month + 1;
	}
	public static int getday(){
		Date date= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DATE);
		return day;
	}
	public static int getyear(){
		Date date= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		return year;
	}
	public static boolean daty(Operations operation, String dataFrom, String dataTo) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		Date today = formatter.parse(operation.getDate());
		Date past = formatter.parse(dataFrom);
		Date future = formatter.parse(dataTo);
		if (past.before(today) && !future.before(today))return true;
		return false;
	}
}

