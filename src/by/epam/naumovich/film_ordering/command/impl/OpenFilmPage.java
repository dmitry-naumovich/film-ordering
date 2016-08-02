package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.bean.Review;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.IReviewService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.GetReviewsServiceException;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class OpenFilmPage implements Command {

	private static final String FILM_ID = "filmID";
	private static final String FILM = "film";
	private static final String REVIEWS = "reviews";
	private static final String PREV_QUERY = "prev_query";
	
	private static final String FILM_JSP_PAGE = "jsp/film.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int movieID = Integer.parseInt(request.getParameter(FILM_ID));
		IFilmService filmService = ServiceFactory.getInstance().getFilmService();
		IReviewService reviewService = ServiceFactory.getInstance().getReviewService();
		
		String query = QueryUtil.createHttpQueryString(request);
		request.getSession(true).setAttribute(PREV_QUERY, query);
		System.out.println(query);
		
		try {
			Film film = filmService.getFilmByID(movieID);
			request.setAttribute(FILM, film);
			
			List<Review> reviews = reviewService.getReviewsByFilmId(movieID);
			request.setAttribute(REVIEWS, reviews);
			
			String url = response.encodeRedirectURL(FILM_JSP_PAGE);
			request.getRequestDispatcher(url).forward(request, response);
			
			
		} catch(GetReviewsServiceException e) {
			request.getRequestDispatcher(FILM_JSP_PAGE).forward(request, response);
		}
		
		catch (ServiceException e) {
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}

	}

}
