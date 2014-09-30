package main.java.org.adaptsuite.coverage;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RetrieveCoverage {
	
	
	public Double searchParam (String testName) {
		
		File file = new File("Cov/" + testName +".html");
		Document doc;
		Double missed = 0.0, param = 1.0;
		
		try {
			int col = 0;
			doc = Jsoup.parse(file, "UTF-8", "http://"+ testName +".com/");
	        Elements tableElements = doc.select("table");
	        
	        Elements tableHeaderEles = tableElements.select("thead tr td");
	        
	        for (int i = 0; i < tableHeaderEles.size(); i++) {
	            if (tableHeaderEles.get(i).text().equals("Lines"))
	            	{
	            		col = i;
	            		break;
	            	}
	        }
	        
	        Elements tableRowElements = tableElements.select(":not(thead) tr");
	        Element row = tableRowElements.get(0);
            Elements rowItems = row.select("td");
            missed = Double.parseDouble( rowItems.get(col-1).text() );
            param = Double.parseDouble( rowItems.get(col).text() );
            
	        
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 1.0 - (missed/param);
		
	}

}
