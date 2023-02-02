package com.it;

public class Country  {
	
	
	private String name;
    private String region;
    private double area;
    
    public Country(String name, String region, double area) {
        this.name = name;
        this.region = region;
        this.area = area;
    }
    
    public Country() {
    	
    }

    
    
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}


}