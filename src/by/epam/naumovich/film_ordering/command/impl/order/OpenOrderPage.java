package by.epam.naumovich.film_ordering.command.impl.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.ErrorMessages;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.film.GetFilmsServiceException;

public class OpenOrderPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		String query = QueryUtil.createHttpQueryString(request);
		session.setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) == null) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.SIGN_IN_FOR_ORDERING);
			request.getRequestDispatcher(JavaServerPageNames.LOGINATION_PAGE).forward(request, response);
		}
		else {
			int filmID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.FILM_ID));
			IFilmService filmService = ServiceFactory.getInstance().getFilmService();
			IUserService userService = ServiceFactory.getInstance().getUserService();
			
			try {
				Film film = filmService.getFilmByID(filmID);
				int userSessionId = (int)request.getSession().getAttribute(RequestAndSessionAttributes.USER_ID);
				int discount = userService.getCurrentUserDiscountByID(userSessionId);
				float orderSum = film.getPrice() * (1.0f - discount/100f);   
				
				request.setAttribute(RequestAndSessionAttributes.FILM, film);
				request.setAttribute(RequestAndSessionAttributes.DISCOUNT_AMOUNT, discount);
				request.setAttribute(RequestAndSessionAttributes.ORDER_SUM, orderSum);
		
				request.getRequestDispatcher(JavaServerPageNames.ORDER_PAGE).forward(request, response);
				
			} catch (GetFilmsServiceException e) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.ORDER_PAGE).forward(request, response);
				
			} catch (ServiceException e) {
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			}
		}
	}

}
