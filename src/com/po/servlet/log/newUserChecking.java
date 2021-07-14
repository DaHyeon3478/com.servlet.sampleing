package com.po.servlet.log;

import com.po.db.UserVo;

public class newUserChecking {
	
	public newUserChecking(){}
	

	public static boolean setnewUserChecking(UserVo vo) {
		
		
		boolean id=false,pw=false,name=false,bir=false,gender=false,number=false,add=false,email=false;
		
		if(vo.getU_id().length()>3) {
			id=true;
		}
		if(vo.getU_pw().equals(vo.getU_pw2()) && vo.getU_pw().length()>3) {
			pw=true;
		}
		if(vo.getU_name().length()>2) {
			name=true;
		}
		System.out.println(vo.getU_birthday());
		if(vo.getU_birthday().length()==8) {//String의 하나의 변수로 jsp에서 받기 ().val 이용
			bir=true;
		}
		if(vo.getU_gender().equals("남") || vo.getU_gender().equals("여")) {
			gender=true;
		}
		if(vo.getU_phon().length()==13) {//String의 하나의 변수로 jsp에서 받기 ().val 이용
			number=true;
		}
		if(vo.getU_add() != null) {
			add=true;
		}
		if(vo.getU_email() !=null) {
			email=true;
		}
		
		if(id && pw && bir && gender && number && add && email) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	
}
