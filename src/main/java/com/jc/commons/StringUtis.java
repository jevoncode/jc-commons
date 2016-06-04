package com.jc.commons;

public class StringUtis {
	public static boolean isEmpty(String s) {
		return null == s || "".equals(s.trim());
	}

	public static boolean isEmpty(StringBuffer sb) {
		return null==sb||sb.length()==0||"".equals(sb.toString().trim());
	}
}
