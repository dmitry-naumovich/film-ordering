package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.IReviewService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.film.GetFilmsServiceException;
import by.epam.naumovich.film_ordering.service.exception.review.GetReviewsServiceException;

public class OpenNewReviewPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {		
		HttpSession session = request.getSession(true);
		String query = QueryUtil.createHttpQueryString(request);
		session.setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) == null) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, "Sign in for writing reviews");
			request.getRequestDispatcher(JavaServerPageNames.LOGINATION_PAGE).forward(request, response);
		}
		else {
			int filmID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.FILM_ID));
			int userID = Integer.parseInt(session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER).toString());
			IFilmService filmService = ServiceFactory.getInstance().getFilmService();
			IReviewService reviewService = ServiceFactory.getInstance().getReviewService();
			Film film = null;
			try {
				film = filmService.getFilmByID(filmID);
				reviewService.getReviewByUserAndFilmId(userID, filmID);
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, "You are not able to write more than one review to a single film");
				request.getRequestDispatcher("/Controller?command=open_single_review&userID=" + userID + "&filmID=" + filmID);
			} catch (GetFilmsServiceException e) { 
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			} catch (GetReviewsServiceException e) {
				request.setAttribute(RequestAndSessionAttributes.FILM, film);
				request.getRequestDispatcher(JavaServerPageNames.NEW_REVIEW_PAGE).forward(request, response);
			} catch (ServiceException e) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			}
			
			
		}
		
		
	}

}
