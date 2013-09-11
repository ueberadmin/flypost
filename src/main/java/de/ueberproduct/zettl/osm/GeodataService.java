package de.ueberproduct.zettl.osm;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import de.ueberproduct.zettl.osm.domain.Address;
import de.ueberproduct.zettl.osm.domain.Place;
import de.ueberproduct.zettl.utils.HttpClientProvider;
import de.ueberproduct.zettl.utils.StringUtils;

@Service
public class GeodataService {

	@Resource
	private HttpClientProvider httpClientProvider;

	private final Gson gson = new Gson();

	public List<Geodata> find(final String street, final String postcode, final String city) throws IOException {
		StringBuilder url = new StringBuilder("http://nominatim.openstreetmap.org/search?format=json&accept-language=de_DE&countrycode=de&country=Deutschland&addressdetails=1");
		
		append("street", street, url);
		append("postalcode", postcode, url);
		append("city", city, url);
		
		HttpGet get = new HttpGet(url.toString());
		return httpClientProvider.getHttpClient().execute(get, new ResponseHandler<List<Geodata>>() {

			@Override
			public List<Geodata> handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
				InputStreamReader reader = new InputStreamReader(response.getEntity().getContent());
				
				
				Type listOfPlaces = new TypeToken<List<Place>>(){}.getType();
				List<Place> places = gson.fromJson(reader, listOfPlaces);
				List<Geodata> geodatas = new ArrayList<Geodata>();
				if (places != null) {
					for (Place place : places) {
						Geodata geodata = new Geodata();
						geodata.setOsmId(place.getOsmType() + "-" + place.getOsmId());
						geodata.setStreet(street);
						geodata.setPostcode(postcode);
						geodata.setCity(city);
						geodata.setLatitude(place.getLat());
						geodata.setLongitude(place.getLon());
						geodata.setDisplayName(place.getDisplayName());
						geodatas.add(geodata);
					}
				}
				
				return geodatas;
			}
		});
	}
	
	public Geodata getByOsmId(String osmId) throws IOException {
		String[] osm = osmId.split("-");
		String osmType = osm[0].equals("node")?"N":"W";
		String url = "http://open.mapquestapi.com/nominatim/v1/reverse.php?format=json&osm_type="+osmType+"&osm_id="+osm[1];
		
		HttpGet get = new HttpGet(url);
		
		return httpClientProvider.getHttpClient().execute(get, new ResponseHandler<Geodata>() {
			@Override
			public Geodata handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
				InputStreamReader reader = new InputStreamReader(response.getEntity().getContent());
				Place place = gson.fromJson(reader, Place.class);
				Address address = place.getAddress();
				Geodata geodata = new Geodata();
				geodata.setStreet(address.getRoad()+" "+address.getHouseNumber());
				geodata.setPostcode(address.getPostcode());
				if (!StringUtils.isEmtpy(address.getCity())) {
					geodata.setCity(address.getCity());
				} else {
					geodata.setCity(address.getState());
				}
				geodata.setLatitude(place.getLat());
				geodata.setLongitude(place.getLon());
				geodata.setDisplayName(place.getDisplayName());
				
				return geodata;
			}
		});
	}

	private void append(String key, String value, StringBuilder url) {
		try {
			if (!StringUtils.isEmtpy(value)) {
				url.append("&").append(key).append("=")
						.append(URLEncoder.encode(value, "utf8"));
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	

}
