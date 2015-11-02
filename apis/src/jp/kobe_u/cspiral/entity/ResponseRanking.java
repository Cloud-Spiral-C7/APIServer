package jp.kobe_u.cspiral.entity;

import java.util.ArrayList;

public class ResponseRanking {
	private String userName;
	private ArrayList<Rank> ranking;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ArrayList<Rank> getRankings() {
		return ranking;
	}

	public void setRankings(ArrayList<Rank> rankings) {
		this.ranking = rankings;
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
