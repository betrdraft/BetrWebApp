package com.betr.server.game.domain;

import java.util.Date;

public class Game {

	private String homeTeam;
	private String awayTeam;
	
	private int homeTeamScore;
	private int awayTeamScore;
	
	private Date scheduledTime;
	
	public Game(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore, Date scheduledTime) {
		super();
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeTeamScore = homeTeamScore;
		this.awayTeamScore = awayTeamScore;
		this.scheduledTime = scheduledTime;
	}
	public String getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}
	public String getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}
	public int getHomeTeamScore() {
		return homeTeamScore;
	}
	public void setHomeTeamScore(int homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}
	public int getAwayTeamScore() {
		return awayTeamScore;
	}
	public void setAwayTeamScore(int awayTeamScore) {
		this.awayTeamScore = awayTeamScore;
	}
	public void setScheduledTime(Date date) {
		this.scheduledTime = date;
	}
	public Date getScheduledTime() {
		return scheduledTime;
	}
	
}
