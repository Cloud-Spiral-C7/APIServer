package jp.kobe_u.cspiral.entity;

import javax.xml.bind.annotation.XmlElement;

public class ResponseSession {

	private String userId;

	@XmlElement(name="userId")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



}
