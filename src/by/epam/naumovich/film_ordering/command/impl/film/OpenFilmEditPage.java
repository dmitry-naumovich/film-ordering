package by.epam.naumovich.film_ordering.command.impl.film;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.ErrorMessages;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class OpenFilmEditPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		String query = QueryUtil.createHttpQueryString(request);
		session.setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		int filmID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.FILM_ID));
		if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) == null |
				!Boolean.parseBoolean(session.getAttribute(RequestAndSessionAttributes.IS_ADMIN).toString())) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.EDIT_FILM_RESTRICTION);
			request.getRequestDispatcher("/Controller?command=open_single_film&filmID=" + filmID).forward(request, response);
		}
		else {
			try {
				IFilmService filmService = ServiceFactory.getInstance().getFilmService();
				Film film = filmService.getFilmByID(filmID);
				String[] genres = filmService.getAvailableGenres();
				String[] countries = filmService.getAvailableCountries();
				
				request.setAttribute(RequestAndSessionAttributes.AVAILABLE_GENRES, genres);
				request.setAttribute(RequestAndSessionAttributes.AVAILABLE_COUNTRIES, countries);
				request.setAttribute(RequestAndSessionAttributes.FILM, film);
				request.getRequestDispatcher(JavaServerPageNames.EDIT_FILM_JSP_PAGE).forward(request, response);	
			} catch (ServiceException e) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			}
		}
	}
}
