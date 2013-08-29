package de.ueberproduct.flypost.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	
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
