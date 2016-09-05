package by.epam.naumovich.film_ordering.command.impl.review;

import java.io.IOException;

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

public class OpenSingleReview implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		String query = QueryUtil.createHttpQueryString(request);
		session.setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		int userID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.USER_ID));
		int filmID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.FILM_ID));
		
		try {
			IReviewService reviewService = ServiceFactory.getInstance().getReviewService();
			Review rev = reviewService.getReviewByUserAndFilmId(userID, filmID);
			
			IUserService userService = ServiceFactory.getInstance().getUserService();
			String userLogin = userService.getLoginByID(userID);
			
			IFilmService filmService = ServiceFactory.getInstance().getFilmService();
			String filmName = filmService.getFilmByID(filmID).getName();
			
			request.setAttribute(RequestAndSessionAttributes.REVIEW, rev);
			request.setAttribute(RequestAndSessionAttributes.LOGIN, userLogin);
			request.setAttribute(RequestAndSessionAttributes.FILM_NAME, filmName);
			System.out.println();
			request.getRequestDispatcher(JavaServerPageNames.SINGLE_REVIEW_PAGE).forward(request, response);
		} catch (GetReviewsServiceException e) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
			request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
		} catch (ServiceException e) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
			request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
		}

	}

}