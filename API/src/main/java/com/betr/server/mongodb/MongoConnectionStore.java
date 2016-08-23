package com.betr.server.mongodb;

import java.util.Properties;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoConnectionStore {

	private static MongoConnectionStore instance = new MongoConnectionStore();
	
	private MongoDatabase db;
	
	//TODO Convert to connection pool
	private MongoConnectionStore() {
		//Properties prop = FileUtils.loadProps(FileUtils.getConfigFile("db.properties"));
		//MongoClient mongoClient = new MongoClient(createURI(prop));
		MongoClient mongoClient = new MongoClient("localhost");
		this.db = mongoClient.getDatabase("betr");
	}
	
	public static MongoConnectionStore getInstance() {
		return instance;
	}
	
	private MongoDatabase getDb() {
		return db;
	}
	
	private MongoClientURI createURI(Properties prop) {
		return new MongoClientURI(String.format("mongodb://%s:%s@%s/?authSource=%s", prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("hostname"), prop.getProperty("databaseName")));
	}
	
}
