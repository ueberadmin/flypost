package de.ueberproduct.zettl.utils;

public class StringUtils {
	
	public static boolean isEmtpy(String toTest) {
		if (toTest == null) {
			return true;
		}
		
		return toTest.replaceAll("\n", "").trim().isEmpty();
	}
	
	public static boolean isEqual(String one, String two) {
		if (one == two) {
			return true;
		}
		
		if (one == null || two == null) {
			return false;
		}
		
		return one.equals(two);
	}

}
