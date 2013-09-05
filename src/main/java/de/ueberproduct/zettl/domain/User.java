package de.ueberproduct.zettl.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	
	public final static String PROP_USERNAME = "username";
	public final static String PROP_PWDHASH = "pwdHash";
	
	@Id
	public String username;
	public String pwdHash;
	
	User(){
		
	}
	
	public User(String username, String pwdHash) {
		this.username = username;
		this.pwdHash = pwdHash;
	}

	public String getPwdHash() {
		return pwdHash;
	}
	
	public String getUsername() {
		return username;
	}

}
