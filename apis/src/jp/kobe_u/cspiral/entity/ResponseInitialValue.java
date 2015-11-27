package jp.kobe_u.cspiral.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="initialValue")
public class ResponseInitialValue {

//	private String latLon;
	private String theme;
	private int wordNum;

	public ResponseInitialValue(){

	}

//	@XmlElement(name="latLon")
//	public String getLatLon() {
//		return latLon;
//	}
//	public void setLatLon(String latLon) {
//		this.latLon = latLon;
//	}

	@XmlElement(name="theme")
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}

	@XmlElement(name="wordNum")
	public int getWordNum() {
		return wordNum;
	}
	public void setWordNum(int wordNum) {
		this.wordNum = wordNum;
	}


}
