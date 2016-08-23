package com.betr.server.repo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.betr.server.game.domain.Contest;
import com.betr.server.mongodb.MongoConnectionStore;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;

public class ContestRepository {

	private MongoDatabase db;

	private MongoCollection<Document> contestCollection;
	
	public ContestRepository() {
		this.db = MongoConnectionStore.getInstance().getDb();
		this.contestCollection = this.db.getCollection("contests");
		contestCollection.createIndex(new Document("name", 1), new IndexOptions().unique(true));
	}
	
	public List<Contest> retrieveAllContests() {
		List<Contest> contestList = new ArrayList<>();
		
		FindIterable<Document> contests = contestCollection.find();
		for(Document contest : contests) {
			contestList.add(Contest.fromDocument(contest));
		}
		
		return contestList;
	}
	
	public void createContest(Contest contest) {
		contestCollection.insertOne(contest.toDocument());
	}
	
	
}
