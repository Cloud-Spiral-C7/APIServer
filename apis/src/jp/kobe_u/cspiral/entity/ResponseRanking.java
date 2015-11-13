package jp.kobe_u.cspiral.entity;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rank")
public class ResponseRanking {
	private String userName;
	private ArrayList<Ranking> ranking;

	@XmlElement(name="userName")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@XmlElement(name="ranking")
	public ArrayList<Ranking> getRankings() {
		return ranking;
	}

	public void setRankings(ArrayList<Ranking> ranking2) {
		this.ranking = ranking2;
	}



	private class Rank{
		private String name;
		private String score;

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
}
