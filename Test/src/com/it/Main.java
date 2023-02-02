package com.it;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) {
        try {
            String urlString = "https://cloudfinch-public.s3.ap-south-1.amazonaws.com/countries.json";
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            
            int status = con.getResponseCode();
            
            if(status != 200) {
            	throw new RuntimeException("Status : "+status);
            }else {
            	StringBuilder str=new StringBuilder();
            	Scanner sc = new Scanner(url.openStream());
            	
            	 while(sc.hasNext()) {
            		 str.append(sc.nextLine());
            	    }
            	
            	 sc.close();
            }
            
            

            JSONArray countriesArray = new JSONArray(str.toString());

            Map<String, List<Country>> regionToCountries = new HashMap<>();
            
            for (int i = 0; i < countriesArray.length(); i++) {
            	
                JSONObject countryObject = countriesArray.getJSONObject(i);
                
                String name = countryObject.getString("name");
                
                String region = countryObject.getString("region");
                
                double area = countryObject.getDouble("area");
                
                Country country = new Country(name, region, area);
                
                List<Country> countries = regionToCountries.get(region);
                
                if (countries == null) {
                    countries = new ArrayList<>();
                    regionToCountries.put(region, countries);
                }
                countries.add(country);
            }
            
            
            
            for (Map.Entry<String, List<Country>> entry : regionToCountries.entrySet()) {
            	
                System.out.println("Top 5 largest countries in " + entry.getKey());
                
                List<Country> countries = entry.getValue();
                
                countries.sort(Comparator.comparing(Country::getArea).reversed());
                
                for (int i = 0; i < Math.min(5, countries.size()); i++) {
                	
                    Country country = countries.get(i);
                    
                    System.out.println("- " + country.getName() + ": " + country.getArea() + " sq km");
                }
            }
        }catch (Exception e) {
			
        	e.printStackTrace();
		}
        
    }
    
}
        
    