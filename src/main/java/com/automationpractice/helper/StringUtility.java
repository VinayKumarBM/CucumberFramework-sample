package com.automationpractice.helper;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtility {
	private static final Logger log = LoggerFactory.getLogger(StringUtility.class);
	
	public static float getPriceFromString(String price) {
		price = price.replaceAll("[$]", "");
		log.info("Price: "+price);
		return Float.parseFloat(price.trim());
	}
	
	public static String getAlphaNumericString(int length) {
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(length);
		Random random = new Random();
		int stringLength = alphaNumericString.length();
		for(int i=0; i < length; i++) {
			sb.append(alphaNumericString.charAt(random.nextInt(stringLength))); 
		}		
		return sb.toString();
	}
}
