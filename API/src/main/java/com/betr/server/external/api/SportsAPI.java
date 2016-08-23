package com.betr.server.external.api;

import java.util.Set;

import com.betr.server.exception.ThirdPartyAPIException;
import com.betr.server.game.domain.NFLPlayer;

public interface SportsAPI {

	public static final long DEFAULT_PLAYER_VALUE = 5000L;
	
	public Set<NFLPlayer> retrieveNFLPlayers() throws ThirdPartyAPIException;
	
}
