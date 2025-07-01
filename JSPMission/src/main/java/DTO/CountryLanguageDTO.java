package DTO;

public class CountryLanguageDTO {
	private String CountryCode;
	private String Language; 
	private String IsOfficial;
	private int Percentage;
	public String getCountryCode() {
		return CountryCode;
	}
	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}
	public String getLanguage() {
		return Language;
	}
	public void setLanguage(String language) {
		Language = language;
	}
	public String getIsOfficial() {
		return IsOfficial;
	}
	public void setIsOfficial(String isOfficial) {
		IsOfficial = isOfficial;
	}
	public int getPercentage() {
		return Percentage;
	}
	public void setPercentage(int percentage) {
		Percentage = percentage;
	}
	
	
}
