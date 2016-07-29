package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.naumovich.film_ordering.command.Command;

public class ChangeLanguage implements Command {

	private final static String LANGUAGE = "language";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String language = request.getParameter(LANGUAGE);
		request.getSession().setAttribute(LANGUAGE, language);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
