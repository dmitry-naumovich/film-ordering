package by.epam.naumovich.film_ordering.command.impl.review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.naumovich.film_ordering.bean.Review;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.LogMessages;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.IReviewService;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.review.GetReviewServiceException;

public class OpenAllReviews implements Command {

	private static final Logger logger = LogManager.getLogger(Logger.class.getName());
	
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
			
			Set<Review> reviews = reviewService.getAllReviews();
			
			List<Review> reviewList = new ArrayList<Review>(reviews);
			Collections.reverse(reviewList);
			reviews = new LinkedHashSet<Review>(reviewList);
			
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
			
		} catch (GetReviewServiceException e) {
			logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(), this.getClass().getSimpleName(), e.getMessage()));
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
			request.getRequestDispatcher(JavaServerPageNames.REVIEWS_PAGE).forward(request, response);
		} catch (ServiceException e) {
			logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(), this.getClass().getSimpleName(), e.getMessage()));
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
			request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
		}
	}

}
