package com.student.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringUtil {

	/**
	 * String杞琁nt
	 * 
	 * @param str
	 * @return
	 */
	public static int StringToInt(String str) {
		int result = 0;
		try {
			result = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			result = 0;
			// e.printStackTrace();
		}
		return result;
	}

	/**
	 * 鑾峰彇鐩愶拷?锛堥殢鏈哄瓧绗︿覆锟�?
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static List<String> splitProperties(String properties) {
		List<String> options = new ArrayList<String>();
		String option = null;
		String[] strings = properties.split(",");
		for (String string : strings) {
			if (!"0".equals(string)) {
				option = string.charAt(3) + "";
				options.add(option);

			}
		}
		return options;
	}
	public static String[] splitString(String properties) {
		String option = null;
		String[] strings = properties.split(",");
		return strings;
	}

	/**
	 * String杞琭loat
	 * 
	 * @param str
	 * @return
	 */
	public static float strToFlo(String str) {
		float i = 0;
		if (str.lastIndexOf(".") != -1) {
			str = str.substring(0, str.lastIndexOf("."));
		}
		try {
			i = Integer.valueOf(str).floatValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public static int longToInt(long i) {
		int j = 0;
		j = Integer.valueOf(String.valueOf(i));
		return j;
	}

}
