package com.fjr.main;

import java.util.Calendar;

public class TestCalendar {
	
	public static void main(String[] args) {
		Calendar c1 = Calendar.getInstance() ;
		c1.set(1987, 02, 01);
		Calendar c2 = Calendar.getInstance(); 
		c2.set(1988, 12, 11);
		System.out.println(c1.compareTo(c2)); 
	}

}
