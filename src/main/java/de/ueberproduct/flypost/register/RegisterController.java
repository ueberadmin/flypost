package de.ueberproduct.flypost.register;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import de.ueberproduct.flypost.domain.User;

@SessionAttributes("user")
@Controller
public class RegisterController {
	
	@Resource
	private MongoOperations mongoOperations;
	
	@RequestMapping(value = "/registrieren", method = RequestMethod.GET) 
	public String showForm() {
		return "register";
	}
	
	@RequestMapping(value = "/registrieren", method = RequestMethod.POST)
	public ModelAndView register(@RequestParam("username") String username, @RequestParam("password1") String password1, @RequestParam("password2") String password2) {
		if (username.isEmpty()) {
			throw new IllegalArgumentException("Username is empty.");
		}
		
		if (password1.isEmpty()) {
			throw new IllegalArgumentException("Password1 is empty.");
		}
		
		if (!password1.equals(password2)) {
			throw new IllegalArgumentException("Password1 not equal password2");
		}
		
		User user = new User(username, hash(password1));
		mongoOperations.save(user);
		
		ModelAndView mav = new ModelAndView("redirect:home");
		mav.addObject("user", user);
		
		return mav;
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
