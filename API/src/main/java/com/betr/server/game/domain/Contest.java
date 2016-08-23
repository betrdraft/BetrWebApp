package com.betr.server.game.domain;

import org.bson.Document;

public class Contest {

	private String gameName;
	private long numEntries;
	private int numMatches;
	private int buyIn;
	private long prizePool;
	private int deadLine;
	private long salaryCap;
	

	public Contest() {}
	
	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public long getNumEntries() {
		return numEntries;
	}

	public void setNumEntries(long numEntries) {
		this.numEntries = numEntries;
	}

	public int getNumMatches() {
		return numMatches;
	}

	public void setNumMatches(int numMatches) {
		this.numMatches = numMatches;
	}

	public int getBuyIn() {
		return buyIn;
	}

	public void setBuyIn(int buyIn) {
		this.buyIn = buyIn;
	}

	public long getPrizePool() {
		return prizePool;
	}

	public void setPrizePool(long prizePool) {
		this.prizePool = prizePool;
	}

	public int getDeadLine() {
		return deadLine;
	}
	
	public long getSalaryCap() {
		return salaryCap;
	}
	
	public void setSalaryCap(long salaryCap) {
		this.salaryCap = salaryCap;
	}

	public void setDeadLine(int deadLine) {
		this.deadLine = deadLine;
	}

	public static Contest fromDocument(Document doc) {
		Contest contest = new Contest();
		contest.setGameName(doc.getString("name"));
		contest.setDeadLine(doc.getInteger("deadline"));
		contest.setNumEntries(doc.getLong("numberEntries"));
		contest.setNumMatches(doc.getInteger("numberMatches"));
		contest.setPrizePool(doc.getLong("prizePool"));
		contest.setBuyIn(doc.getInteger("buyIn"));
		contest.setSalaryCap(doc.getLong("salaryCap"));
		
		return contest;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		
		doc.append("name", getGameName());
		doc.append("deadline", getDeadLine());
		doc.append("numberEntries", getNumEntries());
		doc.append("numberMatches", getNumMatches());
		doc.append("prizePool", getPrizePool());
		doc.append("buyIn", getBuyIn());
		doc.append("salaryCap", getSalaryCap());
		
		return doc;
	}
}
