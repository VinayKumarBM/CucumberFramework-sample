package com.automationpractice.helper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class URLUtility {
	private static final Logger log = LoggerFactory.getLogger(URLUtility.class);
	
	public static boolean verifyLink(String link) {
        try 
        {
           URL url = new URL(link);           
           HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();           
           httpURLConnect.setConnectTimeout(3000);           
           httpURLConnect.connect();    
           int status = httpURLConnect.getResponseCode();
           String message = httpURLConnect.getResponseMessage();
           if(status >= HttpURLConnection.HTTP_OK && status < HttpURLConnection.HTTP_MULT_CHOICE) {
               return true;
            }
           else {
               log.info(link+" --> "+status+" -- "+message);               
            }
        } catch (IOException e) {
           log.info("Exception occurred: "+e.getMessage());
        }
        return false;
    } 
}
