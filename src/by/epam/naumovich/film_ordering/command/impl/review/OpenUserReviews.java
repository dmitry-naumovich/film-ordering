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
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class OpenUserReviews implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		String query = QueryUtil.createHttpQueryString(request);
		session.setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		if (request.getParameter(RequestAndSessionAttributes.USER_ID).equals(RequestAndSessionAttributes.EMPTY_STRING)) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, "Sign in to see your reviews");
			request.getRequestDispatcher(JavaServerPageNames.LOGINATION_PAGE).forward(request, response);
		}
		else {
			int userId = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.USER_ID));
			
			IReviewService reviewService = ServiceFactory.getInstance().getReviewService();
			IFilmService filmService = ServiceFactory.getInstance().getFilmService();
			
			try {
				List<Review> reviews = reviewService.getReviewsByUserId(userId);
				Collections.reverse(reviews);
				
				List<String> reviewFilmNames = new ArrayList<String>();
				for (Review r : reviews) {
					reviewFilmNames.add(filmService.getFilmByID(r.getFilmId()).getName());
				}
				
				request.setAttribute(RequestAndSessionAttributes.REVIEWS, reviews);
				request.setAttribute(RequestAndSessionAttributes.FILM_NAMES, reviewFilmNames);
				
				String url = response.encodeRedirectURL(JavaServerPageNames.REVIEWS_PAGE);
				request.getRequestDispatcher(url).forward(request, response);
			}
			catch (ServiceException e) {
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			}
		}
		
	}

}
