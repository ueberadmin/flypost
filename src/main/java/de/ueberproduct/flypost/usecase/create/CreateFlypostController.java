package de.ueberproduct.flypost.usecase.create;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.ueberproduct.flypost.web.SessionData;

@Controller
public class CreateFlypostController {
	

	@Resource
	private SessionData sessionData;
	
	@Resource
	private CreateFlypostApplication application;
	
	@RequestMapping(value = "/aushaenge/neu", method = RequestMethod.GET) 
	public String showForm(HttpServletRequest request) throws UnsupportedEncodingException {
		String username = sessionData.getUsername();
		if (username == null) {
			return "redirect:/einloggen?url="+URLEncoder.encode("/aushaenge/neu", "utf8");
		}
		
		String id = application.create(username);
		return "redirect:/aushaenge/"+id;
	}
	


}
