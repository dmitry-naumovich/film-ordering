package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;

public class ChangeLanguage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String language = request.getParameter(RequestAndSessionAttributes.LANGUAGE);
		request.getSession().setAttribute(RequestAndSessionAttributes.LANGUAGE, language);
		
		String query = QueryUtil.createHttpQueryString(request);
		request.getSession(true).setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		
		/*if (query != null) {
			response.sendRedirect(query);
		} else {
			request.getRequestDispatcher(JavaServerPageNames.INDEX_PAGE).forward(request, response);
		}*/
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
