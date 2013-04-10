/*
 * StringUtils.java Apr 10, 2013
 * 
 * Copyright 2013 General TECH , Inc. All rights reserved.
 */
package com.gtc.bmi.core.util;

/**
 * @description
 * @author AndyHun
 * 
 * @Version 1.0
 */
public class StringUtils {
	public static boolean isEmpty(String value) {
		if (value == null || value.trim().length() <= 0) {
			return true;
		} else {
			return false;
		}
	}
}
