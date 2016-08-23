package com.betr.server.repo;

import org.bson.Document;

import com.betr.server.game.domain.NFLPlayer;
import com.betr.server.mongodb.MongoConnectionStore;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class NFLPlayerRepository {

	//TODO ADD DEFAULT VALUE TO PROPERTIES
	private long DEFAULT_VALUE = 3000L;
	
	private MongoDatabase db;
	
	private MongoCollection<Document> nflPlayerCollection;
	
	public NFLPlayerRepository() {
		this.db = MongoConnectionStore.getInstance().getDb();
		this.nflPlayerCollection = this.db.getCollection("nflPlayers");
		//nflPlayerCollection.createIndex(new Document("name", 1), new IndexOptions().unique(true));
	}
	
	public Long getPlayerValue(NFLPlayer player) {
		FindIterable<Document> nflPlayers = nflPlayerCollection.find(new Document("name", player.getName()).append("birthDate", player.getBirthDate()));
		
		for(Document nflPlayer : nflPlayers) {
			return nflPlayer.getLong("playerValue");
		}
		return null;
	}
	
	public void addPlayerWithValue(NFLPlayer player, long value) {
		nflPlayerCollection.insertOne(player.toDocument().append("playerValue", value));
	}
	
	public void setExistingPlayerValue(NFLPlayer player, long value) {
		nflPlayerCollection.updateOne(new Document("name", player.getName()).append("birthDate", player.getBirthDate()), new Document("$set", new Document("playerValue", value)));
	}
	
	/*
	private static final String GET_PLAYER_VALUE = "SELECT playerValue FROM NFLPlayer WHERE name = ? AND jerseyNumber = ? AND birthDate = ?";
	
	public long getPlayerValue(Connection conn, NFLPlayer player) throws SQLException {
		
		try(PreparedStatement ps = conn.prepareStatement(GET_PLAYER_VALUE)) {
			ps.setString(1, player.getName());
			ps.setInt(2, player.getJerseyNumber());
			ps.setDate(3, player.getBirthDate());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getLong(1);
			} else {
				addPlayerWithValue(conn, player, DEFAULT_VALUE);
				return DEFAULT_VALUE;
			}
		}
	}
	
	private static final String ADD_PLAYER_WITH_VALUE = "INSERT INTO NFLPlayer (name, jerseyNumber, birthDate, playerValue) VALUES (?,?,?,?)";
	
	public void addPlayerWithValue(Connection conn, NFLPlayer player, long value) throws SQLException {
		try(PreparedStatement ps = conn.prepareStatement(ADD_PLAYER_WITH_VALUE)) {
			ps.setString(1, player.getName());
			ps.setInt(2, player.getJerseyNumber());
			ps.setDate(3, player.getBirthDate());
			ps.setLong(4, value);
			
			ps.executeUpdate();
		}
	}
	
	private static final String UPDATE_PLAYER_VALUE = "UPDATE NFLPlayer SET playerValue = ? WHERE name = ? AND jerseyNumber = ? AND birthDate = ?";
	
	public void updatePlayerValue(Connection conn, NFLPlayer player, long value) throws SQLException {
		if(doesPlayerExist(conn, player)) {	
			try(PreparedStatement ps = conn.prepareStatement(UPDATE_PLAYER_VALUE)) {
				ps.setLong(1, value);
				ps.setString(2, player.getName());
				ps.setInt(3, player.getJerseyNumber());
				ps.setDate(4, player.getBirthDate());
				
				ps.executeUpdate();
			}
		} else {
			addPlayerWithValue(conn, player, value);
		}
	}
	
	private static final String DOES_PLAYER_EXIST = "SELECT id FROM NFLPlayer WHERE name = ? AND jerseyNumber = ? AND birthDate = ?";
	
	public boolean doesPlayerExist(Connection conn, NFLPlayer player) throws SQLException {
		try(PreparedStatement ps = conn.prepareStatement(DOES_PLAYER_EXIST)) {
			ps.setString(1, player.getName());
			ps.setInt(2, player.getJerseyNumber());
			ps.setDate(3, player.getBirthDate());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			return false;
		}
	}
	*/
}
