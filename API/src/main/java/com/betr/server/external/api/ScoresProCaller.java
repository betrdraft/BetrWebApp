package com.betr.server.external.api;

/**
 * This should be a singleton per instance per game type
 * 
 * This will hold all current non-update information (season, country, etc) and also assist in updating
 * real time data.
 * @author James
 *
 */
@Deprecated
public class ScoresProCaller {
/*
	//TODO Put these in config
	//FIXME
	private final static String username = "16betdraft";
	private final static String password = "UBd89TySs";
	
	private final static String ITEM_ATTR = "Item";
	private final static String ID_ATTR = "id";
	
	private static final String SP_URL = "http://data2.scorespro.com/exporter/?usr=" + username + "&pwd=" + password;
	
	private GameType type;
	
	private Map<Integer, Team> teamMap = null;
	private Integer historyId = null;
	
	private ScoresProCaller(GameType type) throws ThirdPartyAPIException {
		this.type = type;
		
		initialize();
	}
	
	public void initialize() throws ThirdPartyAPIException {
		historyId = getHistoryId();
		
		int currentSeasonId = getCurrentSeasonId();
		int countryId = getCountry(currentSeasonId);
		int activeCompetitionId = getActiveCompetition(countryId);
		int regularSeasonPhaseId = getRegularSeasonPhase(activeCompetitionId); 
		int currentRoundId = getCurrentRound(regularSeasonPhaseId, countryId);
		
		teamMap = getTeams(countryId, activeCompetitionId);
		
		System.out.println("Sucess");
	}
	
	private final String SP_URL_CURRENT_SEASON = SP_URL + "?state=ClientStructure&type=12&id=%s";
	private int getCurrentSeasonId() throws ThirdPartyAPIException {
		Document doc = HttpUtil.performXMLHttpGetRequest(String.format(SP_URL_CURRENT_SEASON, type.getSportsNum()));
		NodeList itemList = doc.getElementsByTagName("Item");
		System.out.println(itemList.getLength());
		//Get the last item because it's the last season
		Element e = (Element)itemList.item(itemList.getLength() - 1);
		
		return Integer.parseInt(e.getAttribute(ID_ATTR));
	}
	
	//Hardcoded USA for right now
	private final String SP_URL_GET_COUNTRY = SP_URL + "?state=ClientStructure&type=13&id=%s";
	private Integer getCountry(int seasonId) throws ThirdPartyAPIException {
		Document doc = HttpUtil.performXMLHttpGetRequest(String.format(SP_URL_GET_COUNTRY, seasonId));
		NodeList itemList = doc.getElementsByTagName(ITEM_ATTR);
		
		for(int i=0; i < itemList.getLength(); i++) {
			Element e = (Element)itemList.item(i);
			if(e.getAttribute("code").equals("USA")) {
				return Integer.parseInt(e.getAttribute(ID_ATTR));
			}
		}
		return null;	
	}
	
	private final String SP_URL_GET_ACTIVE_COMPETITIONS = SP_URL + "?state=ClientStructure&type=14&id=%s";
	private int getActiveCompetition(int countryId) throws ThirdPartyAPIException {
		Document doc = HttpUtil.performXMLHttpGetRequest(String.format(SP_URL_GET_ACTIVE_COMPETITIONS, countryId));
		
		NodeList itemList = doc.getElementsByTagName(ITEM_ATTR);
		
		//Only 1 for MLB? Is this true for the others
		Element e = (Element)itemList.item(0);
		return Integer.parseInt(e.getAttribute(ID_ATTR));
	}
	
	//We want regular season
	private static final String SP_URL_GET_PHASES = SP_URL + "?state=ClientStructure&type=14&id=%s";
	private Integer getRegularSeasonPhase(int competitionId) throws ThirdPartyAPIException {
		Document doc = HttpUtil.performXMLHttpGetRequest(String.format(SP_URL_GET_PHASES, competitionId));
		
		NodeList itemList = doc.getElementsByTagName(ITEM_ATTR);
		
		for(int i = 0; i < itemList.getLength(); i++) {
			Element e = (Element)itemList.item(i);
			//Look for regular season node
			if(e.getAttribute("code").equals(type.name() + "-RS")) {
				return Integer.parseInt(e.getAttribute(ID_ATTR));
			}
		}
		return null;
	}
	
	//This goes by month - If it's end of month what do we do? Will this matter?
	private static final String SP_URL_GET_CURRENT_ROUND = SP_URL + "?state=ClientStructure&type=14&id=%s&c=%s";
	private Integer getCurrentRound(int phaseId, int countryId) throws ThirdPartyAPIException {
		Document doc = HttpUtil.performXMLHttpGetRequest(String.format(SP_URL_GET_CURRENT_ROUND, phaseId, countryId));
		
		NodeList itemList = doc.getElementsByTagName(ITEM_ATTR);
		String currentMonth = TimeUtils.getCurrentMonthName();
		
		for(int i = 0; i < itemList.getLength(); i++) {
			Element e = (Element)itemList.item(i);
			if(e.getAttribute("name").equals(currentMonth)) {
				return Integer.parseInt(e.getAttribute(ID_ATTR));
			}
		}
		return null;
	}
	
	//http://data2.scorespro.com/exporter/?usr=16betdraft&pwd=UBd89TySs?state=clientUpdate&type=17&s=6&c=36789&id=50381
	private static final String SP_URL_GET_TEAMS = SP_URL + "?state=ClientUpdate&type=17&s=%s&c=%s&id=%s";
	private Map<Integer, Team> getTeams(int countryId, int competitionId) throws ThirdPartyAPIException {
		Map<Integer, Team> teamMap = new HashMap<>();
		
		Document doc = HttpUtil.performXMLHttpGetRequest(String.format(SP_URL_GET_TEAMS, type.getSportsNum(), countryId, competitionId));
		
		NodeList itemList = doc.getElementsByTagName(ITEM_ATTR);
		
		for(int i = 0; i < itemList.getLength(); i++) {
			Element e = (Element)itemList.item(i);
			teamMap.put(Integer.parseInt(e.getAttribute(ID_ATTR)), new Team(e.getAttribute("name"), type));
		}
		return teamMap;
	}
	
	private static final String SP_URL_HISTORY_ID = SP_URL + "?state=clientStructure&type=11";
	private Integer getHistoryId() throws ThirdPartyAPIException {
		Document doc = HttpUtil.performXMLHttpGetRequest(SP_URL_HISTORY_ID);
		
		NodeList itemList = doc.getElementsByTagName(ITEM_ATTR);
		
		for(int i = 0; i < itemList.getLength(); i++) {
			Element e = (Element)itemList.item(i);
			if(e.getAttribute(ID_ATTR).equals(String.valueOf(type.getSportsNum()))) {
				return Integer.parseInt(e.getAttribute("hid"));
			}
		}
		return null;
	}
	
	/*public static void main(String[] args) {
		
		try {
			ScoresProCaller c = new ScoresProCaller(GameType.MLB);
		} catch (ThirdPartyAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
}
