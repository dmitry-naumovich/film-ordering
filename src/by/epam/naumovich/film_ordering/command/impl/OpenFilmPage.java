package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;
import java.util.ArrayList;
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
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.GetReviewsServiceException;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class OpenFilmPage implements Command {

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int movieID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.FILM_ID));
		IFilmService filmService = ServiceFactory.getInstance().getFilmService();
		IReviewService reviewService = ServiceFactory.getInstance().getReviewService();
		IUserService userService = ServiceFactory.getInstance().getUserService();
		
		String query = QueryUtil.createHttpQueryString(request);
		request.getSession(true).setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		try {
			Film film = filmService.getFilmByID(movieID);
			request.setAttribute(RequestAndSessionAttributes.FILM, film);
			
			List<Review> reviews = reviewService.getReviewsByFilmId(movieID);
			request.setAttribute(RequestAndSessionAttributes.REVIEWS, reviews);
			
			List<String> reviewLogins = new ArrayList<String>();
			for (Review r : reviews) {
				reviewLogins.add(userService.getLoginByID(r.getAuthor()));
			}
			
			request.setAttribute(RequestAndSessionAttributes.LOGINS, reviewLogins);
			
			String url = response.encodeRedirectURL(JavaServerPageNames.FILM_JSP_PAGE);
			//String url = request.getContextPath() + "/jsp/film.jsp";
			//response.sendRedirect(url);
			request.getRequestDispatcher(url).forward(request, response);
			
			
			
		} catch(GetReviewsServiceException e) {
			request.getRequestDispatcher(JavaServerPageNames.FILM_JSP_PAGE).forward(request, response);
		}
		
		catch (ServiceException e) {
			request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
		}

	}

}
