package com.betr.server.external.api;

import java.awt.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.betr.server.exception.ThirdPartyAPIException;
import com.betr.server.game.domain.LiveNFLPlayer;
import com.betr.server.game.domain.NFLPlayer;
import com.betr.server.game.domain.Team;
import com.betr.server.repo.NFLPlayerRepository;

public class MySportsFeedAPI implements SportsAPI {

	
	//TODO Make this map have TTL!
	private Map<String, LiveNFLPlayer> externalIdToLiveNFLPlayerMap = new HashMap<String, LiveNFLPlayer>();
	
	private static String URL_BASE = "https://%s@mysportsfeeds.com/api/";
	
	/**
	 * 
	 * FINISH SEASON!!
	 * 
	 */
	//TODO SEASON
	private static String SEASON = "2015-2016-regular";
	//TODO SEASON
	private static String GET_PLAYERS = "feed/pull/nfl/%s/roster_players.json?fordate=%s&force=true";
	
	//TODO USERNAME PASSWORD
	private static String AUTH = "betrdraft:nomoreok3";
	
	private MySportsFeedAPI() {};
	
	private static final MySportsFeedAPI instance = new MySportsFeedAPI();
	
	public static final MySportsFeedAPI getInstance() {
		return instance;
	}
	
	@Override
	public Set<NFLPlayer> retrieveNFLPlayers() throws ThirdPartyAPIException {
		NFLPlayerRepository repo = new NFLPlayerRepository();
		
		Set<NFLPlayer> playerSet = new HashSet<>();
		
		JSONObject baseJson = HttpUtil.peformJSONGetRequest(formatURL(URL_BASE + GET_PLAYERS));
		
		JSONObject activePlayers = (JSONObject)baseJson.get("rosterplayers");
		
		JSONArray playerArray = (JSONArray)activePlayers.get("playerentry");
		
		for(int i=0; i < playerArray.size(); i++) {
			JSONObject playerObj = (JSONObject)playerArray.get(i);
			try {
				NFLPlayer player = NFLPlayer.fromJSON((JSONObject)playerObj.get("player"));
				
				if(player.getPosition().isPlayableType()) {
					JSONObject teamObj = (JSONObject)playerObj.get("team");
					player.setTeamName((String)teamObj.get("Name"));
					playerSet.add(player);
					
					Long playerValue = null;
					//New player we haven't seen. Add default value
					if((playerValue = repo.getPlayerValue(player)) == null) {
						repo.addPlayerWithValue(player, DEFAULT_PLAYER_VALUE);
						playerValue = DEFAULT_PLAYER_VALUE;
					}
					player.setValue(playerValue);
				}
			} catch (ParseException e) {
				throw new ThirdPartyAPIException(e);
			}
		}
		
		return playerSet;
	}
	
	private static String formatURL(String fullURL) {
		Calendar c1 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return String.format(fullURL, AUTH, SEASON, sdf.format(c1.getTime()));
	}
	
	public LiveNFLPlayer getCurrentLiveStatistics(NFLPlayer player) {
		if(isNFLGameLive(player.getTeam())) {
			return externalIdToLiveNFLPlayerMap.get(player.getExternalId());
		}
		return null;
	}
	
	public boolean isNFLGameLive(Team team) {
		return false;
	}
	
	public static void main(String[] args) {
		
		MySportsFeedAPI MySportsFeedAPI = new MySportsFeedAPI();
		
		Set<NFLPlayer> players;
		try {
			players = MySportsFeedAPI.retrieveNFLPlayers();
			System.out.println(players.isEmpty());
		} catch (ThirdPartyAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
}
