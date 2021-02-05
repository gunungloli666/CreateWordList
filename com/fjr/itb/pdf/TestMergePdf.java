package com.fjr.itb.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;

/*
 * gabung TA ITB yang selesai di download
 */

public class TestMergePdf {
	
	public TestMergePdf() {
		gabungAll();
	}
	
	public void gabungAll() {
		File[] all = new File("E:/TA-ITB/backup 1/").listFiles();
		for( File f : all) {
			String path = f.getAbsolutePath(); 
			System.out.println(path);  
			iteratTesis(path, "hasil-gabung.pdf");
		}
	}
	
	public void sortTesis(List< File> list) {
		Collections.sort(list, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				String file1 = o1.getName(); 
				String file2 = o2.getName(); 
				int a = file1.lastIndexOf("/") + 1;
				file1 = file1.substring(a);
				int b = file2.lastIndexOf("/") + 1; 
				file2 = file2.substring(b);
				file1=file1.replace(".pdf", "").trim(); 
				file2=file2.replace(".pdf", "").trim();
				if(! file1.matches("[0-9]+") || ! file2.matches("[0-9]+")) {
					return 1;
				}
				Integer a1 = Integer.parseInt(file1); 
				Integer a2 = Integer.parseInt(file2);
				return a1.compareTo(a2); 
			}
		} );
	}
	
	public void iteratTesis(String path , String  result ) {
		File[] allFile = new File(path).listFiles();
		List<File> listFile = Arrays.asList(allFile) ;
		sortTesis(listFile);
		Document doc = new Document();
		try {
		PdfCopy copy = new PdfCopy(doc, new FileOutputStream(new File( path + "-"  +result )));
		doc.open();
		for (File ff : listFile) {
			PdfReader reader = new PdfReader(ff.getAbsolutePath());
			int n = reader.getNumberOfPages();
			for (int i = 0; i < n; i++) {
				copy.addPage(copy.getImportedPage(reader, i + 1));
			}
		}
		doc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new TestMergePdf();		
	}

}
