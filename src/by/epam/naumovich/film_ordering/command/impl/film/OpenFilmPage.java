package by.epam.naumovich.film_ordering.command.impl.film;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.naumovich.film_ordering.bean.Film;
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

public class OpenFilmPage implements Command {

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		String query = QueryUtil.createHttpQueryString(request);
		session.setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		int filmID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.FILM_ID));
		try {
			IFilmService filmService = ServiceFactory.getInstance().getFilmService();
			IReviewService reviewService = ServiceFactory.getInstance().getReviewService();
			IUserService userService = ServiceFactory.getInstance().getUserService();
			
			Film film = filmService.getFilmByID(filmID);
			request.setAttribute(RequestAndSessionAttributes.FILM, film);
			
			List<Review> reviews = reviewService.getReviewsByFilmId(filmID);
			request.setAttribute(RequestAndSessionAttributes.REVIEWS, reviews);
			
			List<String> reviewLogins = new ArrayList<String>();
			for (Review r : reviews) {
				reviewLogins.add(userService.getLoginByID(r.getAuthor()));
			}
			request.setAttribute(RequestAndSessionAttributes.LOGINS, reviewLogins);
			
			if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) != null) {
				if (!Boolean.parseBoolean(session.getAttribute(RequestAndSessionAttributes.IS_ADMIN).toString())) {
					try {
						int userID = Integer.parseInt(session.getAttribute(RequestAndSessionAttributes.USER_ID).toString());
						reviewService.getReviewByUserAndFilmId(userID, filmID);
						request.setAttribute(RequestAndSessionAttributes.OWN_REVIEW_EXISTS, true);
					} catch (GetReviewsServiceException e) {
						request.setAttribute(RequestAndSessionAttributes.OWN_REVIEW_EXISTS, false);
					}
				}
			}
			request.getRequestDispatcher(JavaServerPageNames.FILM_JSP_PAGE).forward(request, response);
		} catch(GetReviewsServiceException e) {
			request.getRequestDispatcher(JavaServerPageNames.FILM_JSP_PAGE).forward(request, response);
		}
		
		catch (ServiceException e) {
			request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
		}

	}

}
