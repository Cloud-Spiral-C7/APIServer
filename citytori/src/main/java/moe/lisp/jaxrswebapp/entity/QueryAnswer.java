package moe.lisp.jaxrswebapp.entity;

public class QueryAnswer extends QueryUserInfoBase{

	private String locationName;
	private String phonetic;


	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getPhonetic() {
		return phonetic;
	}
	public void setPhonetic(String phonetic) {
		this.phonetic = phonetic;
	}




}
