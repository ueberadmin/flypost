package de.ueberproduct.flypost.usecase.login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import de.ueberproduct.flypost.domain.User;

@Component
public class LoginApplication {
	
	@Resource
	private MongoOperations mongoOperations;
	
	public User register(String username, String password) {
		User user = new User(username, hash(password));
		mongoOperations.save(user);
		return user;
	}
	

	public User findUser(String username, String password) {
		String pwdHash = hash(password);
		Query query = new Query(Criteria.where(User.PROP_USERNAME).is(username).and(User.PROP_PWDHASH).is(pwdHash));
		return mongoOperations.findOne(query, User.class);
	}
	

	private String hash(String toHash) {
		try {
			String algorithm = "SHA";
			MessageDigest md = MessageDigest.getInstance( algorithm ); 
			byte[] digest = md.digest(toHash.getBytes() );
			return new String(digest);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
