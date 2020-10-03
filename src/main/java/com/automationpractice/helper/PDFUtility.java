package com.automationpractice.helper;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.framework.utilities.GetConfig;
import com.testautomationguru.utility.PDFUtil;

public class PDFUtility {
	private static final Logger log = LoggerFactory.getLogger(PDFUtility.class);
	private static final String pdfFolderPath = System.getProperty("user.dir")+GetConfig.getConfigProperty("downloadFilePath")+File.separator;
	private File directory = new File(pdfFolderPath);
	
	public String getPDFText(String fileName) throws IOException {
		String pdfText = new PDFUtil().getText(pdfFolderPath+fileName);
		log.info(fileName+" page details:\n"+pdfText);
		return pdfText;
	}

	public int getPDFPageCount(String fileName) throws IOException {
		int count = new PDFUtil().getPageCount(pdfFolderPath+fileName);
		log.info(fileName+" page count: "+count);
		return count;
	}
	
	public void deleteFiles() {		
		if(!directory.exists()) {
			directory.mkdir();
		}
		for(File file: directory.listFiles()) {
			file.deleteOnExit();
		}
		log.info("Deleted all the files");
	}
	
	public String waitForFileDownload() throws Exception {
		long startTime = System.currentTimeMillis();	
		boolean waitForDownload = false;
		File[] files = null;
		String fileName = null;
		while(!waitForDownload) {			
			for(File file: directory.listFiles()) {
				if(file.getName().contains(".crdownload")) {
					waitForDownload = true;
					log.info("File Name: "+file.getName());
					fileName = file.getName().replace(".crdownload", "").trim();
					log.info("Download started!!");
					break;
				}
			}
			if((System.currentTimeMillis()-startTime)/60000 == 1) {	
				throw new Exception("FILE DOWNLOAD TIMEOUT");
			}
		}	
		while(waitForDownload) {			
			files = directory.listFiles();
			for(int i=0; i < files.length; i++) {
				if(files[i].getName().contains(".crdownload")) {						
					break;
				} 
				if (i == files.length-1) {
					waitForDownload = false;
				}
			} 			
			if((System.currentTimeMillis()-startTime)/60000 == 1) {	
				throw new Exception("FILE DOWNLOAD TIMEOUT");
			}
		}
		log.info("File download Complete!!");
		return fileName;
	}
}
