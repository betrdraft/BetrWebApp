package com.betr.server.game.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.Document;
import org.json.simple.JSONObject;

public class NFLPlayer extends Player {

	//TODO Implement hashcode and comparable
	
	public NFLPosition position;
	
	public NFLPlayer(String externalId, String name, Integer jerseyNumber, Team team, Date birthDate, NFLPosition position) {
		super(externalId, name, jerseyNumber, team, birthDate);
		this.position = position;
	}

	public static NFLPlayer fromJSON(JSONObject json) throws NumberFormatException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		return new NFLPlayer((String)json.get(JSON_ID),
				(String)json.get(JSON_FIRST_NAME) + " " + (String)json.get(JSON_LAST_NAME), 
				json.get(JSON_JERSEY_NUM) != null ? Integer.parseInt((String)json.get(JSON_JERSEY_NUM)) : null, 
				null, 
				json.get(JSON_BIRTH_DATE) != null ? new Date(sdf.parse((String)json.get(JSON_BIRTH_DATE)).getTime()) : null, 
				NFLPosition.valueOf((String)json.get(JSON_POSITION)));
	}

	public NFLPosition getPosition() {
		return position;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		
		doc.append("name", getName());
		doc.append("jerseyNumber", getBirthDate());
		doc.append("playerValue", getValue());
		doc.append("position", getPosition().name());
		
		return doc;
	}

	
	
	
	//"ID":"6923","LastName":"Abbrederis","FirstName":"Jared","JerseyNumber":"84",
	//"Position":"WR","Height":"6'1\"","Weight":"195","BirthDate":"1990-12-17","Age":"25","BirthCity":"West Allis, WI","BirthCountry":"USA","IsRookie":"false"
	
}
