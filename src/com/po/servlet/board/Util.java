package com.po.servlet.board;

public class Util {
	public static int parseStringToInt(String str) {//문자->정수
		int result=0;
		
		try {
			result=Integer.parseInt(str);//숫자인지 확인 0이라면 애러나 입력값이0임.
		}catch (Exception e) {}
		
		return result;
	}
	
	public static String parseIntToSting(int in) {//정수->문자
		String result=null;
		
		try {
			result=Integer.toString(in);//숫자인지 확인 0이라면 애러나 입력값이0임.
		}catch (Exception e) {}
		
		return result;
	}
	
}
