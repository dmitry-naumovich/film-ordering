package by.epam.naumovich.film_ordering.command.impl.film;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.bean.Order;
import by.epam.naumovich.film_ordering.bean.Review;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.LogMessages;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.IOrderService;
import by.epam.naumovich.film_ordering.service.IReviewService;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.order.GetOrderServiceException;
import by.epam.naumovich.film_ordering.service.exception.review.GetReviewServiceException;

/**
 * Performs the command that gets a single film from the service layer and passes it to the relevant JSP.
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 */
public class OpenSingleFilm implements Command {

	private static final Logger logger = LogManager.getLogger(Logger.class.getName());
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		String query = QueryUtil.createHttpQueryString(request);
		session.setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		String lang = null;
		try {
			lang = session.getAttribute(RequestAndSessionAttributes.LANGUAGE).toString();
		} catch (NullPointerException e) {
			lang = RequestAndSessionAttributes.ENG_LANG;
		}
		
		int filmID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.FILM_ID));
		int pageNum = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.PAGE_NUM));
		
		try {
			ServiceFactory sFactory = ServiceFactory.getInstance();
			IFilmService filmService = sFactory.getFilmService();
			IReviewService reviewService = sFactory.getReviewService();
			IUserService userService = sFactory.getUserService();
			IOrderService orderService = sFactory.getOrderService();
			
			Film film = filmService.getFilmByID(filmID, lang);
			request.setAttribute(RequestAndSessionAttributes.FILM, film);
			
			if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) != null) {
				if (!Boolean.parseBoolean(session.getAttribute(RequestAndSessionAttributes.IS_ADMIN).toString())) {
					int userID = Integer.parseInt(session.getAttribute(RequestAndSessionAttributes.USER_ID).toString());
					try {
						reviewService.getReviewByUserAndFilmId(userID, filmID);
						request.setAttribute(RequestAndSessionAttributes.OWN_REVIEW_EXISTS, true);
					} catch (GetReviewServiceException e) {
						request.setAttribute(RequestAndSessionAttributes.OWN_REVIEW_EXISTS, false);
					}
					
					try {
						Order order = orderService.getOrderByUserAndFilmId(userID, filmID);
						request.setAttribute(RequestAndSessionAttributes.OWN_ORDER_EXISTS, true);
						request.setAttribute(RequestAndSessionAttributes.ORDER_NUM, order.getOrdNum());
					} catch (GetOrderServiceException e) {
						request.setAttribute(RequestAndSessionAttributes.OWN_ORDER_EXISTS, false);
					}
					
				}
			}
			
			Set<Review> reviews = reviewService.getReviewsPartByFilmId(filmID, pageNum);
			request.setAttribute(RequestAndSessionAttributes.REVIEWS, reviews);
			request.setAttribute(RequestAndSessionAttributes.REVIEW_VIEW_TYPE, RequestAndSessionAttributes.VIEW_TYPE_FILM);
			
			int totalPageAmount = reviewService.getNumberOfFilmReviewsPages(filmID);
			request.setAttribute(RequestAndSessionAttributes.NUMBER_OF_PAGES, totalPageAmount);
			request.setAttribute(RequestAndSessionAttributes.CURRENT_PAGE, pageNum);
			
			List<String> reviewLogins = new ArrayList<String>();
			for (Review r : reviews) {
				reviewLogins.add(userService.getLoginByID(r.getAuthor()));
			}
			request.setAttribute(RequestAndSessionAttributes.LOGINS, reviewLogins);
			request.getRequestDispatcher(JavaServerPageNames.SINGLE_FILM_PAGE).forward(request, response);
		
		} catch (GetReviewServiceException e) {
			request.getRequestDispatcher(JavaServerPageNames.SINGLE_FILM_PAGE).forward(request, response);
		}
		catch (ServiceException e) {
			logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(), this.getClass().getSimpleName(), e.getMessage()));
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
			request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
		}

	}

}
