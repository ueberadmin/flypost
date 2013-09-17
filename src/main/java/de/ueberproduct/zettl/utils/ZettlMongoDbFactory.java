package de.ueberproduct.zettl.utils;

import java.net.UnknownHostException;

import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.DB;
import com.mongodb.MongoURI;
import com.mongodb.WriteConcern;

public class ZettlMongoDbFactory implements MongoDbFactory {
	
	private final SimpleMongoDbFactory mongoDbFactory;
	
	public ZettlMongoDbFactory(MongoURI uri) throws UnknownHostException {
		mongoDbFactory = new SimpleMongoDbFactory(uri);
		mongoDbFactory.setWriteConcern(WriteConcern.ACKNOWLEDGED);
	}

	@Override
	public DB getDb() throws DataAccessException {
		return mongoDbFactory.getDb();
	}

	@Override
	public DB getDb(String dbName) throws DataAccessException {
		return mongoDbFactory.getDb(dbName);
	}

}
