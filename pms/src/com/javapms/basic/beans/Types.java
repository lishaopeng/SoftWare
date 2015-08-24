package com.javapms.basic.beans;

import java.util.HashMap;
import java.util.Map;

public class Types {
	public static final String VARCHAR = "VARCHAR";
	public static final String INT = "INT";
	public static final String BOOLEAN = "BOOLEAN";
	public static final int STRING_LENGTH = 32;
	public static final int INT_LENGTH = 10;
	public static Map<String, Integer> map = new HashMap();

	static {
		map.put("VARCHAR", Integer.valueOf(32));
		map.put("INT", Integer.valueOf(10));
		map.put("BOOLEAN", Integer.valueOf(0));
	}

	public static String getType(String type, int length) {
		if (type == null) {
			throw new RuntimeException("Not recognized the type  :" + type);
		}

		if (length > 0) {
			return type + "(" + length + ")";
		}
		return type;
	}

	public static String getString() {
		return getStirng("VARCHAR", 32);
	}

	public static String getString(int length) {
		return getStirng("VARCHAR", length);
	}

	public static String getInt() {
		return getStirng("INT", 10);
	}

	public static String getInt(int length) {
		return getStirng("INT", length);
	}

	public static String getBoolean() {
		return "BOOLEAN";
	}

	private static String getStirng(String str, int length) {
		return str + "(" + length + ")";
	}
}