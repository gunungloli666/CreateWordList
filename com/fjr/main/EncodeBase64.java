package com.fjr.main;
//
//public class Example {
//
//}

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

public class EncodeBase64{
	
	public static void main(String[] args) {
		  File fInput = new File("./" + args[0]);  
		  String encodstring = encodeFileToBase64Binary(fInput);
          File fout = new File("./" + args[1]); 
          FileOutputStream ouput  = null; 
          try {
			ouput = new FileOutputStream(fout);
			byte[] data = encodstring.getBytes();
			ouput.write(data);
			ouput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
	        if( ouput != null) {
	        	try {
					ouput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		}
	}
	
	  private static String encodeFileToBase64Binary(File file){
          byte[] encodedfile = null;
          FileInputStream reader  = null ; 
          try {
              reader = new FileInputStream(file);
              byte[] bytes = new byte[(int)file.length()];
//              System.out.println(bytes.length); 
              reader.read(bytes);
              encodedfile = Base64.encodeBase64(bytes);
          } catch (IOException e) {
              e.printStackTrace();
          }finally {
        	  if( reader != null) {
        		  try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	  }
          }
          return new String(encodedfile) ;
      }
}
