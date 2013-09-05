package de.ueberproduct.zettl.usecase.create;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.ueberproduct.zettl.web.SessionData;

@Controller
public class CreateFlypostController {
	

	@Resource
	private SessionData sessionData;
	
	@Resource
	private CreateFlypostApplication application;
	
	@RequestMapping(value = "/neu", method = RequestMethod.GET) 
	public String showForm(HttpServletRequest request) throws UnsupportedEncodingException {
		String token = UUID.randomUUID().toString();
		sessionData.getTokens().add(token);
		String id = application.create(token);
		return "redirect:/bearbeiten/"+id;
	}
	


}
