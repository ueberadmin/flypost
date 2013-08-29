package de.ueberproduct.flypost.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/einloggen", method = RequestMethod.GET) 
	public String showForm() {
		return "login";
	}

}
