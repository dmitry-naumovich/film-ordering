package by.epam.naumovich.film_ordering.command.impl.film;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.ErrorMessages;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.LogMessages;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.command.util.SuccessMessages;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.film.AddFilmServiceException;

public class AddFilm implements Command {

	private static final Logger logger = LogManager.getLogger(Logger.class.getName());
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) == null) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.ADD_FILM_RESTRICTION);
			request.getRequestDispatcher(JavaServerPageNames.LOGINATION_PAGE).forward(request, response);
		}
		else if (!Boolean.parseBoolean(session.getAttribute(RequestAndSessionAttributes.IS_ADMIN).toString())) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.ADD_FILM_RESTRICTION);
			request.getRequestDispatcher("/Controller?command=open_all_films").forward(request, response);
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
				int filmID = filmService.addNewFilm(name, year, director, cast, countries, composer, 
						genres, length, price, description);
				
				logger.debug(String.format(LogMessages.FILM_ADDED, name, filmID));
				request.setAttribute(RequestAndSessionAttributes.SUCCESS_MESSAGE, SuccessMessages.FILM_ADDED);
				request.getRequestDispatcher("/Controller?command=open_single_film&filmID=" + filmID).forward(request, response);
			} catch (AddFilmServiceException e) {
				logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(), this.getClass().getSimpleName(), e.getMessage()));
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.FILM_ADDING_PAGE).forward(request, response);
			} catch (ServiceException e) {
				logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(), this.getClass().getSimpleName(), e.getMessage()));
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			}
		}

	}

}
