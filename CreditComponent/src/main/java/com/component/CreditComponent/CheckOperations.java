package com.component.CreditComponent;

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
	public static boolean daty(Operations operation, String dataFrom, String dataTo){
		List<String> dataF = Arrays.asList(dataFrom.split("\\."));
		List<String> dataC = Arrays.asList(operation.getDate().split("\\."));
		List<String> dataT = Arrays.asList(dataTo.split("\\."));
		double w1 = Integer.parseInt(dataF.get(0)) * 0.0001 + Integer.parseInt(dataF.get(1)) * 0.01 + Integer.parseInt(dataF.get(2));
		double w2 = Integer.parseInt(dataC.get(0)) * 0.0001 + Integer.parseInt(dataC.get(1)) * 0.01 + Integer.parseInt(dataC.get(2));
		double w3 = Integer.parseInt(dataT.get(0)) * 0.0001 + Integer.parseInt(dataT.get(1)) * 0.01 + Integer.parseInt(dataT.get(2));
		if(w1 < w2 && w2 <= w3) {
			return true;
		}
		return false;
	}
}

