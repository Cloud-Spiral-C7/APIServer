package moe.lisp.jaxrswebapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;


@Entity
@NoSql(dataFormat = DataFormatType.MAPPED)
public class Ranking {

	@Id
	@GeneratedValue
	@Field(name = "id")
	private String id;

	@Field(name = "gameMode")
	private String gameMode;

	@Field(name = "name")
	private String name;

	@Field(name = "score")
	private String score;

	public String getId() {
		return id;
	}

	public String getGameMode() {
		return gameMode;
	}

	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}



}
