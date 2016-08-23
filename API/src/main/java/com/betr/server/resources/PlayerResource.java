package com.betr.server.resources;

import java.sql.SQLException;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.betr.server.exception.ThirdPartyAPIException;
import com.betr.server.external.api.MySportsFeedAPI;
import com.betr.server.external.api.SportsAPI;
import com.betr.server.game.domain.NFLPlayer;
import com.betr.server.repo.NFLPlayerRepository;
	
@Controller
public class PlayerResource {

	@RequestMapping(value = "/api/retrieve/players/values/all", method = RequestMethod.GET)
	public @ResponseBody Set<NFLPlayer> retrieveAllPlayerValues() throws ThirdPartyAPIException, SQLException {
		SportsAPI api = MySportsFeedAPI.getInstance();
		
		Set<NFLPlayer> players = api.retrieveNFLPlayers();

		return players;
	}
	
	@RequestMapping(value = "/api/set/players/values", method = RequestMethod.PUT)
	public void setPlayerValues(@RequestBody NFLPlayer player) throws ThirdPartyAPIException, SQLException {
		NFLPlayerRepository repo = new NFLPlayerRepository();
		
		repo.setExistingPlayerValue(player, player.getValue());
	}
}
