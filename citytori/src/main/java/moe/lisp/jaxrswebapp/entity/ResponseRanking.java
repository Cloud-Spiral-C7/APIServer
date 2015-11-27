package moe.lisp.jaxrswebapp.entity;

import java.util.ArrayList;

public class ResponseRanking {
	private String userName;
	private ArrayList<Rank> ranking;
	public ResponseRanking(){

	}
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




}
