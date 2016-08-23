package com.betr.server.repo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.betr.server.game.domain.NFLContestEntry;
import com.betr.server.mongodb.MongoConnectionStore;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ContestEntryRepository {


	private MongoDatabase db;

	private MongoCollection<Document> contestEntryCollection;
	
	public ContestEntryRepository() {
		this.db = MongoConnectionStore.getInstance().getDb();
		this.contestEntryCollection = this.db.getCollection("nflContestEntries");
	}
	
	
	public void addContestEntry(NFLContestEntry entry) {
		contestEntryCollection.insertOne(entry.toDocument());
	}
	
	public List<NFLContestEntry> getNFLContestsForUser(int userId) {
		List<NFLContestEntry> contestList = new ArrayList<>();
		
		FindIterable<Document> contests = contestEntryCollection.find(new Document("userId", userId));
		
		for(Document doc : contests) {
			contestList.add(NFLContestEntry.fromDocument(doc));
		}
		return contestList;
	}
	
	public LiveNFLContestEntry insertOrUpdateStatisticsForContest(LiveNFLContestEntry entry) {
		
	}
}
