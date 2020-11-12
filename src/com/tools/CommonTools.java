package com.tools;

import java.util.Calendar;
import java.util.Date;

public class CommonTools {
	
	/**
	 * 
	 * @return
	 */
	public  static int getQuarterOfYear() {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date());
	    return calendar.get(Calendar.MONTH) / 3 + 1;
	}
	
	/**
	 * 输入一个月份然后判断是哪个季度
	 * @param month
	 * @return
	 */
	public  static int getQuarterOfYear(int month) {
		int q = 1;
	    if (month <1 || month >12) {
			System.out.println("输入的月份错误");
		}else {
			if (month == 12) {
				q = 4;
			}else {
				q = month / 3 + 1;
			}
		}
	    return q;
	}
	
    /**
     * 获取某个月的最大天数
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	

}
