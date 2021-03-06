package de.ueberproduct.zettl.web;


public class Urls {
	
	public final static String FOR_OVERVIEW = "/anschauen/{id}";
	public final static String FOR_EDIT = "/bearbeiten/{id}";
	public final static String FOR_IMAGE = "/bilder/{id}";
	
	public static String forOverview(String id) {
		return "/anschauen/"+id;
	}

	public static String forEdit(String id) {
		return "/bearbeiten/"+id;
	}

	public static String forImage(String imageId) {
		return "/bilder/"+imageId;
	}

}
