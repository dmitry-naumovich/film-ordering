package by.epam.naumovich.film_ordering.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {
	
	public static boolean validateWithPattern(String toCheck, String pattern) {
		
		if (toCheck == null || toCheck.isEmpty()) {
			return false;
		}
		
		Pattern pat = Pattern.compile(pattern);
        Matcher matcher = pat.matcher(toCheck);
        return matcher.matches();
	}
	
	public static boolean validateStrings(String... strings) {
		
		for (String str : strings) {
			if (str == null || str.isEmpty()) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean validateObject(Object obj) {
		return obj == null ? false : true;
	}
	
	public static boolean validateInt(int i) {
		return i != 0;
	}
	
	public static boolean validateStringArray(String[] array) {
		for (String s : array) {
			if (!validateStrings(s)) {
				return false;
			}
		}
		return true;
	}
}
