package com.betr.server.game.domain;

import java.util.Date;

public class LiveNFLPlayer extends NFLPlayer {

	public int numOfYards;
	public int numOfPts;

	public LiveNFLPlayer(String externalId, String name, Integer jerseyNumber, Team team, Date birthDate, NFLPosition position, int numOfYards, int numOfPts) {
		super(externalId, name, jerseyNumber, team, birthDate, position);
		this.numOfYards = numOfYards;
		this.numOfPts = numOfPts;
	}

	public int getNumOfYards() {
		return numOfYards;
	}

	public void setNumOfYards(int numOfYards) {
		this.numOfYards = numOfYards;
	}

	public int getNumOfPts() {
		return numOfPts;
	}

	public void setNumOfPts(int numOfPts) {
		this.numOfPts = numOfPts;
	}

	
}
