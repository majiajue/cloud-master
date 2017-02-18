package com.datababys.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IdCardUtil {
	
	public static String convert15to18(String idcard){
		 if (idcard != null && idcard.trim().length() == 15) {
		StringBuffer card18=new StringBuffer(idcard.trim());
		card18.insert(6, "19");
		char[] check = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
		int sum=0;
		 for (int i= 0; i < card18.length(); i++) {
			    char c = card18.charAt(i);
			    int a = Integer.parseInt(new Character(c).toString());
			    int e = ((int) Math.pow(2, card18.length() - i)) % 11;
			    sum = sum + a * e;
			   }
		 int indexOfCheck = sum % 11; // 取模
		 card18.append(check[indexOfCheck]);
		return card18.toString();
		 }else{
		 return idcard;
		 }
	}
	
	public static String getGenderByidcard(String idcard){
		String card18=IdCardUtil.convert15to18(idcard);
		Integer genderNum=Integer.parseInt(card18.substring(16, 17));
		String gender=new String();
		//偶数为女性，基数为男性,居民信息表中1为男2为女
		if(genderNum%2==0){
			gender="2";
		}else{
			gender="1";
		}
		return gender;
	}
	
	public static Date getBirthDayByIdcard(String idcard) throws ParseException{
		String birthday = idcard.substring(6, 14);   
        Date birthdate = new SimpleDateFormat("yyyyMMdd").parse(birthday);   
		return birthdate;
	}
	

}
