package com.bni.main.page;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;


public class ViewOriginalPage {
	
	public ViewOriginalPage() {
		try {
			DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder dbbuilder = dbfac.newDocumentBuilder();
			Document doc = dbbuilder.parse(new FileInputStream("F:/test-baca/m.html") );  
//			NodeList nodelist = doc.getElementsByTagName("tr") ;
//			System.out.println(nodelist.getLength());
		}catch(Exception e) {
			System.out.println("salah: " + e); 
		}
	}
	
	
	public static void main(String[] args) {
		new ViewOriginalPage();
	}
}
