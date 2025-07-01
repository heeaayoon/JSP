package DTO;
//DTO는 값만 저장된 객체
public class CountryDTO {
	private String name;
	private String Code;
	private String Continent; 
	private String Region;
	private float SurfaceArea;
	private int IndepYear;
	private int Population;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getContinent() {
		return Continent;
	}
	public void setContinent(String continent) {
		Continent = continent;
	}
	public String getRegion() {
		return Region;
	}
	public void setRegion(String region) {
		Region = region;
	}
	public float getSurfaceArea() {
		return SurfaceArea;
	}
	public void setSurfaceArea(float surfaceArea) {
		SurfaceArea = surfaceArea;
	}
	public int getIndepYear() {
		return IndepYear;
	}
	public void setIndepYear(int indepYear) {
		IndepYear = indepYear;
	}
	public int getPopulation() {
		return Population;
	}
	public void setPopulation(int population) {
		Population = population;
	}
	
}
