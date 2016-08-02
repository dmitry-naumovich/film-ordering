package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.naumovich.film_ordering.command.Command;

public class OpenMoviePage implements Command {

	private static final String MOVIE_ID = "movieID";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int movieID = Integer.parseInt(request.getParameter(MOVIE_ID));
		//get openmovie service
		// get movie by ID

	}

}
