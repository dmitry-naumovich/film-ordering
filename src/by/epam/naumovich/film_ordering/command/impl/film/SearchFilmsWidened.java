package by.epam.naumovich.film_ordering.command.impl.film;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.command.util.SuccessMessages;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.film.GetFilmsServiceException;

public class SearchFilmsWidened implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		String query = QueryUtil.createHttpQueryString(request);
		session.setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		String name = request.getParameter(RequestAndSessionAttributes.NAME);
		String yearFrom = request.getParameter(RequestAndSessionAttributes.YEAR_FROM);
		String yearTo = request.getParameter(RequestAndSessionAttributes.YEAR_TO);
		String[] genres = request.getParameterValues(RequestAndSessionAttributes.GENRE);
		String[] countries = request.getParameterValues(RequestAndSessionAttributes.COUNTRY);
		
		try {
			IFilmService filmService = ServiceFactory.getInstance().getFilmService();
			Set<Film> foundFilms = filmService.searchWidened(name, yearFrom, yearTo, genres, countries);
			request.setAttribute(RequestAndSessionAttributes.SUCCESS_MESSAGE, SuccessMessages.FILMS_FOUND);
			request.setAttribute(RequestAndSessionAttributes.FILMS, foundFilms);
			request.getRequestDispatcher(JavaServerPageNames.FILMS_JSP_PAGE).forward(request, response);
		} catch (GetFilmsServiceException e) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
			request.getRequestDispatcher("/Controller?command=open_widened_search_page").forward(request, response);
		} catch (ServiceException e) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
			request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
		}
	}

}
