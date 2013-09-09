package de.ueberproduct.zettl.utils;

public class StringUtils {
	
	public static boolean isEmtpy(String toTest) {
		if (toTest == null) {
			return true;
		}
		
		return toTest.replaceAll("\n", "").trim().isEmpty();
	}

}
