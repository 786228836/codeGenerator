package com.suning.util;

public class FirstUp {

	public  static String toUpperCaseFirstOne(String s){
		
		char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (i == 0) {
                ch[0] = Character.toUpperCase(ch[0]);
            } else {
                ch[i] = Character.toLowerCase(ch[i]);
            }
        }
        StringBuffer a = new StringBuffer();
        a.append(ch);
        return a.toString();
	}
	

	
}
