package com.betr.server.game.domain;

import java.sql.Timestamp;
import java.util.Set;

public class Team {
	
	private GameType type;
	private String name;
	private Set<Player> players;
	private int wins;
	private int losses;
	private Game nextGame;
	
	public Team(String name, GameType type) {
		this.name = name;
		this.type = type;
	}
	
	public GameType getType() {
		return type;
	}
	public void setType(GameType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Player> getPlayers() {
		return players;
	}
	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public Game getNextGame() {
		return nextGame;
	}
	public void setNextGame(Game game) {
		this.nextGame = game;
	}
	
}
