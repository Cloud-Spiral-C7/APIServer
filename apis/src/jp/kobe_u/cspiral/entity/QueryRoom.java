package jp.kobe_u.cspiral.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="room")
public class QueryRoom {

	private String userId;
	private String name;
	private String gameMode;
	private int wordNum;
	private int limitTime;

	@XmlElement(name="userId")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@XmlElement(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name="gameMode")
	public String getGameMode() {
		return gameMode;
	}

	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}

	@XmlElement(name="wordNum")
	public int getWordNum() {
		return wordNum;
	}

	public void setWordNum(int wordNum) {
		this.wordNum = wordNum;
	}

	@XmlElement(name="limitTime")
	public int getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(int limitTime) {
		this.limitTime = limitTime;
	}



}
