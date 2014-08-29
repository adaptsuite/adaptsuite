package main.java.org.adaptsuite.coverage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CoverageFile {
	
	private File file;
	
	public CoverageFile ()
	{
		file = new File("Coverage");
	}
	
	public double getCoverage(String testName)
	{
		String prop[];
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			try {
				for(String line; (line = br.readLine()) != null; ) {
				    prop = line.split(" ");
				    
				    if(isTheRightTest(prop[0], testName)) {
				    		br.close();
				    		return Double.parseDouble(prop[1]);
				    }
				}
				br.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't open the 'Coverage' file");
		}
		
		addTest(testName);
		return 1.0;
		
		

	}

	private void addTest(String testName) {
		
        try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			
			bw.write("\n" + testName + " 1.0");
			bw.close();
			
		} catch (IOException e) {
			System.out.println("Couldn't open the 'Coverage' file");
		}

	}

	private boolean isTheRightTest(String curTestName, String testName) {
		return curTestName.equals(testName);
	}

}
