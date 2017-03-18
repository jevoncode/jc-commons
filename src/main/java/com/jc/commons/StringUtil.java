package com.jc.commons;

public class StringUtil {
	public static boolean isEmpty(String s) {
		return null == s || "".equals(s.trim());
	}

	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}

	public static boolean isEmpty(StringBuffer sb) {
		return null == sb || sb.length() == 0 || "".equals(sb.toString().trim());
	}

	public static String trim(String s) {
		return s == null ? "" : s.trim();
	}

}
