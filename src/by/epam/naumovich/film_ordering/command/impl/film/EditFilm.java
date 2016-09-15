package by.epam.naumovich.film_ordering.command.impl.film;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.ErrorMessages;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.command.util.SuccessMessages;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.film.EditFilmServiceException;

public class EditFilm implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		
		int filmID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.FILM_ID));
		if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) == null |
				!Boolean.parseBoolean(session.getAttribute(RequestAndSessionAttributes.IS_ADMIN).toString())) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.EDIT_FILM_RESTRICTION);
			request.getRequestDispatcher("/Controller?command=open_single_film&filmID=" + filmID).forward(request, response);
		}
		else {
			String name = request.getParameter(RequestAndSessionAttributes.NAME);
			String year = request.getParameter(RequestAndSessionAttributes.YEAR);
			String director = request.getParameter(RequestAndSessionAttributes.DIRECTOR);
			String cast = request.getParameter(RequestAndSessionAttributes.CAST);
			String[] countries = request.getParameterValues(RequestAndSessionAttributes.COUNTRY);
			String composer = request.getParameter(RequestAndSessionAttributes.COMPOSER);
			String[] genres = request.getParameterValues(RequestAndSessionAttributes.GENRE);
			String length = request.getParameter(RequestAndSessionAttributes.LENGTH);
			String price = request.getParameter(RequestAndSessionAttributes.PRICE);
			String description = request.getParameter(RequestAndSessionAttributes.DESCRIPTION);
			try {
				IFilmService filmService = ServiceFactory.getInstance().getFilmService();
				filmService.editFilm(filmID, name, year, director, cast, countries, composer, genres, length, price, description);
				request.setAttribute(RequestAndSessionAttributes.SUCCESS_MESSAGE, SuccessMessages.FILM_EDITED);
				request.getRequestDispatcher("/Controller?command=open_single_film&filmID=" + filmID).forward(request, response);
			} catch (EditFilmServiceException e) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher("/Controller?command=open_film_edit_page&filmID=" + filmID).forward(request, response);
			} catch (ServiceException e) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			}
		}
	}
}
