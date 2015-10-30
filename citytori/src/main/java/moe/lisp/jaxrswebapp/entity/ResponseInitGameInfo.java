package moe.lisp.jaxrswebapp.entity;

public class ResponseInitGameInfo {

	private String latLon;
	private String theme;
	private int wordNum;

	public String getLatLon() {
		return latLon;
	}
	public void setLatLon(String latLon) {
		this.latLon = latLon;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public int getWordNum() {
		return wordNum;
	}
	public void setWordNum(int wordNum) {
		this.wordNum = wordNum;
	}


}
