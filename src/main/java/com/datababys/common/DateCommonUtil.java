package com.datababys.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCommonUtil {

	/**
	 * 字符串转换成日期 按照默认formatStr的格式，转化dateTimeStr为Date类型
	 * 
	 * @param dateTimeStr
	 * @param formatStr
	 *            :yyyy/MM/dd
	 * @return
	 */
	public static Date stringToDate(String dateTimeStr, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = format.parse(dateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Timestamp转化为String
	 * 
	 * @param formatStr
	 *            :yyyy-MM-dd HH:mm:ss
	 */
	public static String timestampToString(Timestamp timestamp, String formatStr) {
		SimpleDateFormat df = new SimpleDateFormat(formatStr);
		String date = "";
		if (timestamp != null) {
			date = df.format(timestamp);
		}
		return date;
	}

	/**
	 * 将多选的组织机构转换为in需要的字符
	 * 
	 * @param organs
	 *            ：321002014411679,321002466002980
	 * @return String:'321002014411679','321002466002980'
	 */
	public static String addQuotes(String organs) {
		String returnString = "";
		if (organs != null) {
			String[] items = organs.split(",");
			for (int i = 0; i < items.length; i++) {
				if (i == 0) {
					returnString = returnString + "'" + items[i] + "'";
				} else {
					returnString = returnString + ",'" + items[i] + "'";
				}

			}
		}
		return returnString;
	}

	/**
	 * 将timestamp转换成date
	 * 
	 * @author hellostoy
	 * @param tt
	 * @return
	 */
	public static Date timestampToDate(Timestamp tt) {
		if (tt != null) {
			return new Date(tt.getTime());
		} else {
			return null;
		}
	}

}
