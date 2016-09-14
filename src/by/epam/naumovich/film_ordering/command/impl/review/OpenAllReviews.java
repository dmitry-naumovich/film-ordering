package by.epam.naumovich.film_ordering.command.impl.review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.naumovich.film_ordering.bean.Review;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.IReviewService;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.review.GetReviewsServiceException;

public class OpenAllReviews implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		String query = QueryUtil.createHttpQueryString(request);
		session.setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		try {
			IReviewService reviewService = ServiceFactory.getInstance().getReviewService();
			IFilmService filmService = ServiceFactory.getInstance().getFilmService();
			IUserService userService = ServiceFactory.getInstance().getUserService();
			
			List<Review> reviews = reviewService.getAllReviews();
			Collections.reverse(reviews);
			
			List<String> reviewLogins = new ArrayList<String>();
			List<String> reviewFilmNames = new ArrayList<String>();
			for (Review r : reviews) {
				reviewLogins.add(userService.getLoginByID(r.getAuthor()));
				reviewFilmNames.add(filmService.getFilmByID(r.getFilmId()).getName());
			}
			
			request.setAttribute(RequestAndSessionAttributes.REVIEWS, reviews);
			request.setAttribute(RequestAndSessionAttributes.LOGINS, reviewLogins);
			request.setAttribute(RequestAndSessionAttributes.FILM_NAMES, reviewFilmNames);
			request.getRequestDispatcher(JavaServerPageNames.REVIEWS_PAGE).forward(request, response);
			
		} catch (GetReviewsServiceException e) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
			request.getRequestDispatcher(JavaServerPageNames.REVIEWS_PAGE).forward(request, response);
		} catch (ServiceException e) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
			request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
		}
	}

}
