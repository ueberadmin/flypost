package de.ueberproduct.zettl.osm;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.ueberproduct.zettl.domain.Geodata;
import de.ueberproduct.zettl.osm.domain.Place;
import de.ueberproduct.zettl.utils.HttpClientProvider;
import de.ueberproduct.zettl.utils.StringUtils;

@Service
public class GeodataService {
	
	@Resource
	private HttpClientProvider httpClientProvider;
	
	private final Gson gson = new Gson();
	
	public Geodata find(String street, String postcode, String city) throws IOException {
		StringBuilder url = new StringBuilder("http://nominatim.openstreetmap.org/search?format=json&accept-language=de_DE&countrycode=de");
		
		append("street", street, url);
		append("postalcode", postcode, url);
		append("city", city, url);
		
		HttpGet get = new HttpGet(url.toString());
		return httpClientProvider.getHttpClient().execute(get, new ResponseHandler<Geodata>() {

			@Override
			public Geodata handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
				InputStreamReader reader = new InputStreamReader(response.getEntity().getContent());
				
				
				Type listOfPlaces = new TypeToken<List<Place>>(){}.getType();
				List<Place> places = gson.fromJson(reader, listOfPlaces);
				if (places == null || places.isEmpty()) {
					return null;
				}
				
				Place firstPlace = places.get(0);
				return new Geodata(firstPlace.getLat(), firstPlace.getLon());
			}
		});
	}
	
	private void append(String key, String value, StringBuilder url) {
		try {
			if (!StringUtils.isEmtpy(value)) {
				url.append("&").append(key).append("=").append(URLEncoder.encode(value, "utf8"));
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

}
