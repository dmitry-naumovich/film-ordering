package by.epam.naumovich.film_ordering.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {
	
	public static boolean validateWithPattern(String toCheck, String pattern) {
		
		if (toCheck == null) {
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
}
