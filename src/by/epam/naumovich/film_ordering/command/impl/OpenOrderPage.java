package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.naumovich.film_ordering.command.Command;

public class OpenOrderPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int filmID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.FILM_ID));
		
	}

}
