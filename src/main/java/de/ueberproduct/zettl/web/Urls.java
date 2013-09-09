package de.ueberproduct.zettl.web;


public class Urls {
	
	public final static String FOR_OVERVIEW = "/anschauen/{id}";
	
	public static String forOverview(String id) {
		return "/anschauen/"+id;
	}

}
