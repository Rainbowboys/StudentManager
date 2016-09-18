package com.student.DBDao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDB {
	private static MongoDatabase database = null;

	public MongoDB() {
		// TODO Auto-generated constructor stub
	}

	public static MongoDatabase newInstance() {
		if (database == null) {
			synchronized (MongoDatabase.class) {
				if (database == null) {
					MongoClient client = new MongoClient("hadoop", 27017);
					database = client.getDatabase("user");
				}
			}
		}
		return database;

	}

}
