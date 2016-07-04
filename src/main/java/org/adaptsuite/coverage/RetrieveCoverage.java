package org.adaptsuite.coverage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RetrieveCoverage {
	
	
	public Double[] getCoverages (String testName) {
		
		File file = new File("Cov/" + testName +".html");
		Document doc;
		Double [] coverages = new Double[2];
		Double [] lineCoverage;
		Double [] reachedClasses;
		
		
		try {

			doc = Jsoup.parse(file, "UTF-8", "http://"+ testName +".com/");
	        lineCoverage = searchParam(doc, "Lines");
	        coverages[0] = 1.0 - (lineCoverage[0]/lineCoverage[1]); 
	        
	        
		} catch (FileNotFoundException e) {
			coverages[0] = 1.0;
			coverages[1] = 1.0;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return coverages;
		
	}
	
	Double [] searchParam (Document doc, String param) {
		
		int col = 0;		
		Double [] params = new Double[2];
		
		Elements tableElements = doc.select("table");
        
        Elements tableHeaderEles = tableElements.select("thead tr td");
        
        for (int i = 0; i < tableHeaderEles.size(); i++) {
            if (tableHeaderEles.get(i).text().equals(param))
            	{
            		col = i;
            		break;
            	}
        }
        
        Elements tableRowElements = tableElements.select(":not(thead) tr");
        Element row = tableRowElements.get(0);
        Elements rowItems = row.select("td");
        params[0] = Double.parseDouble( rowItems.get(col-1).text() );
        params[1] = Double.parseDouble( rowItems.get(col).text() );
        
        return params;
        
	}

}
