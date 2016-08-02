package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.GetReviewsServiceException;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class OpenFilmList implements Command {
	
	private static final String FILMS = "films";
	private static final String PREV_QUERY = "prev_query";
	
	private static final String MOVIES_JSP_PAGE = "jsp/movies.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		IFilmService filmService = ServiceFactory.getInstance().getFilmService();
		
		String query = QueryUtil.createHttpQueryString(request);
		request.getSession(true).setAttribute(PREV_QUERY, query);
		System.out.println(query);
		
		try {
			List<Film> films = filmService.getAllFilms();
			request.setAttribute(FILMS, films);
			
			String url = response.encodeRedirectURL(MOVIES_JSP_PAGE);
			request.getRequestDispatcher(url).forward(request, response);
			
			
		} catch(GetReviewsServiceException e) {
			request.getRequestDispatcher(MOVIES_JSP_PAGE).forward(request, response);
		}
		
		catch (ServiceException e) {
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
	}

}
