package by.epam.naumovich.film_ordering.command.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public final class QueryUtil {

	private QueryUtil() {
	}
	
	public static String createHttpQueryString(HttpServletRequest request){
		
		Enumeration<String> params = request.getParameterNames();
		String query = "";
		
		String key;
		String value;
		while(params.hasMoreElements()){
			key = params.nextElement();
			value  = request.getParameter(key);
			query = query + "&" + key + "=" + value;			
		}
		
		query = request.getRequestURL() + "?" + query;
		
		return query;		
	}

}
