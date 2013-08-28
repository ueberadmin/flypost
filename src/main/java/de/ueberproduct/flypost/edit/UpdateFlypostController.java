package de.ueberproduct.flypost.edit;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UpdateFlypostController {
	
	@Resource
	private UpdateFlypostApplication application;
	
	@RequestMapping(value = "/aushaenge/{id}", method = RequestMethod.POST) 
	public String update(@PathVariable("id") String id, @ModelAttribute(value="command") ViewModel viewModel) throws IOException {
		application.update(id, viewModel);
		return "redirect:/aushaenge/"+id;
	}
	


}
