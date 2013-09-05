package de.ueberproduct.zettl.web;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SessionData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Set<String> tokens = new HashSet<String>();
	
	public Set<String> getTokens() {
		return tokens;
	}
}
