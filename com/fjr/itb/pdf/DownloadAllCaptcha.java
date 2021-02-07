package com.fjr.itb.pdf;

import java.io.FileOutputStream;
import java.io.InputStream;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

public class DownloadAllCaptcha {
	String url = "https://helpdesk.bkn.go.id/sscn/captcha";
	String dir = "F:/test-captcha" + "/"  ; 
	WebClient webClient = new WebClient();
	public DownloadAllCaptcha() {
		webClient.getCookieManager().clearCookies();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		try {
			for( int i = 0 ; i< 10000; i++) {
				Page page = webClient.getPage (url);
				InputStream inputStream = page.getWebResponse().getContentAsStream(); 
				FileOutputStream outputStream = new 
						FileOutputStream(dir +  Integer.toString(i+1) + ".jpg" );
		        int read = 0;
		        byte[] bytes = new byte[1024];
		        while ((read = inputStream.read(bytes, 0, 1024)) != -1) {
		            outputStream.write(bytes, 0, read);
		        }
		        outputStream.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("FINISH"); 
	}
	
	public static void main(String[] args) {
		new DownloadAllCaptcha();
	}

}
