package com.betr.server.game.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bson.Document;

public class NFLContestEntry {

	private int id;
	private int userId;
	private int contestId;
	private Set<NFLPlayer> players;
	private GameType type;
	
	private int numPoints;
	private int total
	
	public NFLContestEntry(int id, int userId, Set<NFLPlayer> players, GameType type, int contestId) {
		this.id = id;
		this.userId = userId;
		this.players = players;
		this.type = type;
		this.contestId = contestId;
	}

	public int getId() {
		return id;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Set<NFLPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(Set<NFLPlayer> players) {
		this.players = players;
	}

	public GameType getType() {
		return type;
	}

	public void setType(GameType type) {
		this.type = type;
	}
	
	public int getContestId() {
		return contestId;
	}
	
	public void setContestId(int contestId) {
		this.contestId = contestId;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		
		doc.append("userId", getUserId());
		List<Document> playerList = new ArrayList<>();
		for(NFLPlayer player : getPlayers()) {
			playerList.add(player.toDocument());
		}
		doc.append("players", playerList);
		doc.append("gameType", getType().name());
		doc.append("contestId", getContestId());
		
		return doc;
	}
	
	public static NFLContestEntry fromDocument(Document doc) {
		return new NFLContestEntry(doc.getInteger("id"), doc.getInteger("userId"), (Set<NFLPlayer>)doc.get("players"), GameType.valueOf(doc.getString("gameType")), doc.getInteger("contestId"));
	}
	
}
