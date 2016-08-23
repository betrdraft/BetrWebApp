package com.betr.server.repo;

import org.bson.Document;

import com.betr.server.domain.User;
import com.betr.server.mongodb.MongoConnectionStore;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;

public class UserRepository {
	
	private MongoDatabase db;

	private MongoCollection<Document> userCollection;
	
	public UserRepository() {
		this.db = MongoConnectionStore.getInstance().getDb();
		this.userCollection = this.db.getCollection("users");
		userCollection.createIndex(new Document("email", 1), new IndexOptions().unique(true));
	}
	
	public User getUserByEmailAndPassword(String email, String password) {
		FindIterable<Document> users = userCollection.find(new Document("email", email).append("password", password));
		for(Document document : users) {
			
			return User.fromDocument(document);
		}
		
		return null;
	}
	
	public User getUserByEmail(String email) {
		FindIterable<Document> users = userCollection.find(new Document("email", email));
		
		for(Document document : users) {
			return User.fromDocument(document);
		}
		
		return null;
	}
	
	public void createUser(User user) {
		userCollection.insertOne(user.toDocument());
	}
}
