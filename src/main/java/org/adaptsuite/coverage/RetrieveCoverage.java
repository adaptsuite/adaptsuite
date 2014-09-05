package main.java.org.adaptsuite.coverage;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RetrieveCoverage {
	
	
	public double getCoverage(String testName) {
		
		String strCoverage = searchTable(testName);
		
		return Double.parseDouble(strCoverage.substring(0, strCoverage.length()-1)) /100.0;
	}
	
	
	private String searchTable (String testName) {
		
		File file = new File("Cov/" + testName +".html");
		Document doc;
		
		try {
			int col = 0;
			doc = Jsoup.parse(file, "UTF-8", "http://"+ testName +".com/");
	        Elements tableElements = doc.select("table");
	        
	        Elements tableHeaderEles = tableElements.select("thead tr td");
	        
	        for (int i = 0; i < tableHeaderEles.size(); i++) {
	            if (tableHeaderEles.get(i).text().equals("Cov."))
	            	{
	            		col = i;
	            		break;
	            	}
	        }
	        
	        Elements tableRowElements = tableElements.select(":not(thead) tr");
	        Element row = tableRowElements.get(0);
            Elements rowItems = row.select("td");
            return rowItems.get(col).text();
            
	        
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return " ";
	}

}
