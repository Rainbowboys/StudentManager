package com.student.util;

import java.util.Random;

public class RandomData {

	public static String[] getRandom(int index) {
		String[] firstname = { "ÕÔ", "Ëï", "Àî", "ÖÜ", "Îâ", "Íõ", "ÕÅ", "Áõ", "Ñî", "·¶", "Ò¦", "Ö£", "³Â", "Ç®", "ÇØ", "·ë", "ËÎ" };
		String[] lastname = { "¸Õ", "Óî", "ºÆ", "½Ü", "½à", "ÎÄ", "ÁÁ", "Ã÷", "±ù", "µ¤", "¼Ñ", "À¤", "Ôó", "Ğã", "·É", "À¼", "ÔÆ", "Ğ¡Ã÷",
				"ºêÓî", "Ğ¡±¦", "»³Óñ", "ÃÈÃÈ", "½ÜÂ×", "¹úÇ¿", "ÏéÈğ", "Ã÷ÔÂ" };

		Random random = new Random();

		int len = 0;
		String[] p = new String[index];
		for (int i = 0; i < index; i++) {
			int x = random.nextInt(firstname.length);
			int y = random.nextInt(lastname.length);
			String name = firstname[x] + lastname[y];

			while (!check(p, len, name)) {
				x = random.nextInt(firstname.length);
				y = random.nextInt(lastname.length);
				name = firstname[x] + lastname[y];
			}
			p[len] = name;
			len++;
		}

		return p;
	}

	public static String[] getRandomPassword(int index) {
		String[] p = new String[index];
		Random random = new Random();
		for (int i = 0; i < index; i++) {
			int x1 = random.nextInt(10);
			int x2 = random.nextInt(10);
			int x3 = random.nextInt(10);
			int x4 = random.nextInt(10);
			int x5 = random.nextInt(10);
			int x6 = random.nextInt(10);
			p[i] = "" + x1 + x2 + x3 + x4 + x5 + x6;
		}
		return p;
	}
	public static String[] getRandomTel(int index) {
		String[] p = new String[index];
		Random random = new Random();
		for (int i = 0; i < index; i++) {
			int x1 = random.nextInt(10);
			int x2 = random.nextInt(10);
			int x3 = random.nextInt(10);
			int x4 = random.nextInt(10);
			int x5 = random.nextInt(10);
			int x6 = random.nextInt(10);
			int x7 = random.nextInt(10);
			int x8 = random.nextInt(10);
			int x9 = random.nextInt(10);
			String []heads={"13","15","17","18"};
			int headindex=random.nextInt(3);
			p[i] = heads[headindex] + x1 + x2 + x3 + x4 + x5 + x6 +x7 + x8 + x9;
		}
		return p;
	}

	public static String[] getRandomAccount(int index) {
		String[] p = new String[index];
		for (int i = 0; i < index; i++) {
			p[i] = 2016001 + i + "";
		}
		return p;
	}

	public static int[] getRandomId(int index) {
		int[] p = new int[index];
		for (int i = 0; i < index; i++) {
			p[i] = i + 1;
		}
		return p;
	}

	public static double[] getRandomGrade(int index) {
		double[] p = new double[index];
		Random random = new Random();
		for (int i = 0; i < index; i++) {
			p[i] = random.nextInt(61) + 40;
		}
		return p;
	}

	public static boolean check(String[] p, int len, String name) {

		for (int i = 0; i < len; i++) {
			if (p[i].equals(name)) {
				return false;
			}
		}

		return true;
	}

}
