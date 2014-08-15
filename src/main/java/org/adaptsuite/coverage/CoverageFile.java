package main.java.org.adaptsuite.coverage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CoverageFile {
	
	private File file;
	
	public CoverageFile ()
	{
		file = new File("Coverage");
	}
	
	public void getCoverage(String testName)
	{
		String prop[];
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			try {
				for(String line; (line = br.readLine()) != null; ) {
				    line.length();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't open the 'Coverage' file");
		}
		
		

	}

}
