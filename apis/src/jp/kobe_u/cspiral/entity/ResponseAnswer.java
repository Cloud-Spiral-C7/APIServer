package jp.kobe_u.cspiral.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="answer")
public class ResponseAnswer {

	private String result;
	private String nextStringWith;
	private String locationName;
	private String phonetic;

	public ResponseAnswer(){

	}

	@XmlElement(name="result")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@XmlElement(name="nextStringWith")
	public String getNextStringWith() {
		return nextStringWith;
	}

	public void setNextStringWith(String nextStringWith) {
		this.nextStringWith = nextStringWith;
	}

	@XmlElement(name="locationName")
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@XmlElement(name="phonetic")
	public String getPhonetic() {
		return phonetic;
	}

	public void setPhonetic(String phonetic) {
		this.phonetic = phonetic;
	}



}
