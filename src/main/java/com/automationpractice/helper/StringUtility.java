package com.automationpractice.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtility {
	private static final Logger log = LoggerFactory.getLogger(StringUtility.class);
	
	public static float getPriceFromString(String price) {
		price = price.replaceAll("[$]", "");
		log.info("Price: "+price);
		return Float.parseFloat(price.trim());
	}
}
