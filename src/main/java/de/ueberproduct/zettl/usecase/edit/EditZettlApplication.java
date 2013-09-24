package de.ueberproduct.zettl.usecase.edit;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import de.ueberproduct.shorturl.ShortUrlService;
import de.ueberproduct.zettl.domain.Geodata;
import de.ueberproduct.zettl.domain.Zettl;
import de.ueberproduct.zettl.email.EmailService;
import de.ueberproduct.zettl.osm.GeodataService;
import de.ueberproduct.zettl.utils.StringUtils;
import de.ueberproduct.zettl.web.Urls;

@Component
public class EditZettlApplication {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private MongoOperations mongoOperations;
	
	@Resource
	private GeodataService geodataService;
	
	@Resource
	private ZettlUpdater zettlUpdater;
	
	@Resource
	private EmailService emailService;
	
	@Resource
	private ShortUrlService shortUrlService;
	
	Zettl getZettl(String id) {
		Zettl zettl = loadZettl(id);
		return zettl;
	}
	
	void setDescription(String id, String description, Set<String> tokens) {
		Zettl zettl = loadZettl(id);		
		
		zettl.setDescription(description);
		
		zettlUpdater.save(zettl, tokens);
	}

	

	List<Geodata> setLocationAndEmailAddress(String id, EditZettlController.ViewModel viewModel, Set<String> tokens, String contextUrl) throws IOException, MessagingException {
		String street = viewModel.getStreet();
		String postcode = viewModel.getPostCode();
		String city = viewModel.getCity();
		
		Zettl zettl = loadZettl(id);
		String oldEmailAddress = zettl.getEmailAddress();
		String newEmailAddress = viewModel.getEmailAddress();
		zettl.setEmailAddress(newEmailAddress);
		zettl.setStreet(street);
		zettl.setPostCode(postcode);
		zettl.setCity(city);
		
		try {
			zettl.setRadius(viewModel.getRadius());
		} catch (NumberFormatException e) {
			logger.info("Invalid radius", e);
		}
		
		List<Geodata> geodatas = geodataService.find(street, postcode, city);
		if (geodatas.size() == 1) {
			zettl.setGeodata(geodatas.get(0));
		}
		
		zettlUpdater.save(zettl, tokens);
		
		String shortUrl = shortUrlService.getShortUrl(contextUrl + Urls.forOverview(id), contextUrl);
		
		if (!StringUtils.isEqual(newEmailAddress, oldEmailAddress)) {			
			emailService.sentEditToken(newEmailAddress, contextUrl + Urls.forEdit(id)+"?auth="+zettl.getEditToken(), shortUrl);
		}
		
		return geodatas;
	}
	

	public void setLocationByOsmId(String id, String osmId, Set<String> tokens) throws IOException {		
		Geodata geodata = geodataService.getByOsmId(osmId);
		Zettl zettl = loadZettl(id);
		zettl.setGeodata(geodata);
		zettl.setStreet(geodata.getStreet());
		zettl.setPostCode(geodata.getPostcode());
		zettl.setCity(geodata.getCity());
		
		zettlUpdater.save(zettl, tokens);
		
	}



	

	private Zettl loadZettl(String id) {
		Zettl zettl = mongoOperations.findById(id, Zettl.class);
		if (zettl == null) {
			throw new IllegalArgumentException("No Zettl with id "+id+" found");
		}
		return zettl;
	}



	
	
}
