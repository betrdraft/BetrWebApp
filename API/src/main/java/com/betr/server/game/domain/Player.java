package com.betr.server.game.domain;

import java.util.Date;

public abstract class Player {
	
	//"ID":"6923","LastName":"Abbrederis","FirstName":"Jared","JerseyNumber":"84",
	//"Position":"WR","Height":"6'1\"","Weight":"195","BirthDate":"1990-12-17","Age":"25","BirthCity":"West Allis, WI","BirthCountry":"USA","IsRookie":"false"
	
	protected static String JSON_ID = "ID";
	protected static String JSON_LAST_NAME = "LastName";
	protected static String JSON_FIRST_NAME = "FirstName";
	protected static String JSON_POSITION = "Position";
	protected static String JSON_JERSEY_NUM = "JerseyNumber";
	protected static String JSON_BIRTH_DATE = "BirthDate";
	
	private String externalId;
	private String name;
	private Integer jerseyNumber;
	private Long value;
	private Date birthDate;
	private Team team;
	
	public Player(String externalId, String name, Integer jerseyNumber, Team team, Date birthDate) {
		this.externalId = externalId;
		this.name = name;
		this.jerseyNumber = jerseyNumber;
		this.birthDate = birthDate;
		this.team = team;
	}
	
	public String getExternalId() {
		return externalId;
	}

	public String getName() {
		return name;
	}

	public Integer getJerseyNumber() {
		return jerseyNumber;
	}

	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
	
	public void setValue(long value) {
		this.value = value;
	}
	
	public Long getValue() {
		return value;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setJerseyNumber(int jerseyNumber) {
		this.jerseyNumber = jerseyNumber;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	
}
