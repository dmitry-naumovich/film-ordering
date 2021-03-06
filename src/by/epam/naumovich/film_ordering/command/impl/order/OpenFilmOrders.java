package by.epam.naumovich.film_ordering.command.impl.order;

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

import by.epam.naumovich.film_ordering.bean.Order;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.ErrorMessages;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.LogMessages;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.IOrderService;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.order.GetOrderServiceException;

/**
 * Performs the command that gets all concrete film orders from the service layer and passes it to the relevant JSP.
 * Checks the access rights of the user who is performing this action.
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 */
public class OpenFilmOrders implements Command {

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
		
		if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) == null) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.FILM_ORDERS_RESTRICTION);
			request.getRequestDispatcher(JavaServerPageNames.LOGINATION_PAGE).forward(request, response);
		}
		else if (!Boolean.parseBoolean(session.getAttribute(RequestAndSessionAttributes.IS_ADMIN).toString())) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.FILM_ORDERS_RESTRICTION);
			request.getRequestDispatcher("/Controller?command=open_single_film&filmID=" + filmID + "&pageNum=1").forward(request, response);
		}
		else {
		
			try {
				IOrderService orderService = ServiceFactory.getInstance().getOrderService();
				IFilmService filmService = ServiceFactory.getInstance().getFilmService();
				IUserService userService = ServiceFactory.getInstance().getUserService();
				
				Set<Order> orders = orderService.getOrdersByFilmId(filmID);
				
				List<String> userLogins = new ArrayList<String>();
				for (Order o : orders) {
					String userLogin = userService.getLoginByID(o.getUserId());
					userLogins.add(userLogin);
				}
				String filmName = filmService.getFilmNameByID(filmID, lang);
				
				int totalPageAmount = orderService.getNumberOfFilmOrdersPages(filmID);
				request.setAttribute(RequestAndSessionAttributes.NUMBER_OF_PAGES, totalPageAmount);
				request.setAttribute(RequestAndSessionAttributes.CURRENT_PAGE, pageNum);
				
				request.setAttribute(RequestAndSessionAttributes.ORDERS, orders);
				request.setAttribute(RequestAndSessionAttributes.FILM_NAME, filmName);
				request.setAttribute(RequestAndSessionAttributes.USER_LOGINS, userLogins);
				request.setAttribute(RequestAndSessionAttributes.FILM_ID, filmID);
				request.setAttribute(RequestAndSessionAttributes.ORDER_VIEW_TYPE, RequestAndSessionAttributes.VIEW_TYPE_FILM);
				request.getRequestDispatcher(JavaServerPageNames.ORDERS_PAGE).forward(request, response);
			} catch (GetOrderServiceException e) {
				logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(), this.getClass().getSimpleName(), e.getMessage()));
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher("/Controller?command=open_single_film&filmID=" + filmID + "&pageNum=1").forward(request, response);
			} catch (ServiceException e) {
				logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(), this.getClass().getSimpleName(), e.getMessage()));
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			}
		}
	}

}
