package moe.lisp.jaxrswebapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;


@Entity
@NoSql(dataFormat = DataFormatType.MAPPED)
public class Room {

	@Id
	@GeneratedValue
	@Field(name = "id")
	private String id;

	@Field(name = "name")
	private String name;

	@Field(name = "gameMode")
	private String gameMode;

	@Field(name = "status")
	private String status;

	@Field(name = "currentWord")
	private String currentWord;

	@Field(name = "wordNum")
	private int wordNum;

	@Field(name = "limitTime")
	private int limitTime;

	@Field(name = "areaId")
	private int areaId;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGameMode() {
		return gameMode;
	}

	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurrentWord() {
		return currentWord;
	}

	public void setCurrentWord(String currentWord) {
		this.currentWord = currentWord;
	}

	public int getWordNum() {
		return wordNum;
	}

	public void setWordNum(int wordNum) {
		this.wordNum = wordNum;
	}

	public int getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(int limitTime) {
		this.limitTime = limitTime;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

}
