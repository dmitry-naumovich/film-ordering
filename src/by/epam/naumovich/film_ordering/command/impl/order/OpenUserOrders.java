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
 * Performs the command that gets all concrete user orders from the service layer and passes it to the relevant JSP.
 * Checks the access rights of the user who is performing this action.
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 */
public class OpenUserOrders implements Command {
	
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
		
		int pageNum = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.PAGE_NUM));
		
		if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) == null) {
			if (request.getParameter(RequestAndSessionAttributes.USER_ID).isEmpty() || request.getParameter(RequestAndSessionAttributes.USER_ID) == null) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.SIGN_IN_FOR_YOUR_ORDERS);
			}
			
			request.getRequestDispatcher(JavaServerPageNames.LOGINATION_PAGE).forward(request, response);
		}
		else {
			int userID = 0;
			if (session.getAttribute(RequestAndSessionAttributes.USER_ID).toString() != request.getParameter(RequestAndSessionAttributes.USER_ID)
					&& !(boolean)session.getAttribute(RequestAndSessionAttributes.IS_ADMIN)) {
				userID = Integer.parseInt(session.getAttribute(RequestAndSessionAttributes.USER_ID).toString());
			}
			else {
				userID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.USER_ID));
			}
			try {
				IOrderService orderService = ServiceFactory.getInstance().getOrderService();
				IFilmService filmService = ServiceFactory.getInstance().getFilmService();
				IUserService userService = ServiceFactory.getInstance().getUserService();
				Set<Order> orders = orderService.getOrdersPartByUserId(userID, pageNum);
				
				List<String> filmNames = new ArrayList<String>();
				for (Order o : orders) {
					String filmName = filmService.getFilmNameByID(o.getFilmId(), lang);
					filmNames.add(filmName);
				}
				String userLogin = userService.getLoginByID(userID);
				
				int totalPageAmount = orderService.getNumberOfUserOrdersPages(userID);
				request.setAttribute(RequestAndSessionAttributes.NUMBER_OF_PAGES, totalPageAmount);
				request.setAttribute(RequestAndSessionAttributes.CURRENT_PAGE, pageNum);
				
				request.setAttribute(RequestAndSessionAttributes.ORDERS, orders);
				request.setAttribute(RequestAndSessionAttributes.FILM_NAMES, filmNames);
				request.setAttribute(RequestAndSessionAttributes.USER_LOGIN, userLogin);
				request.setAttribute(RequestAndSessionAttributes.USER_ID, userID);
				request.setAttribute(RequestAndSessionAttributes.ORDER_VIEW_TYPE, RequestAndSessionAttributes.VIEW_TYPE_USER);
				request.getRequestDispatcher(JavaServerPageNames.ORDERS_PAGE).forward(request, response);
				
			} catch (GetOrderServiceException e) {
				logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(), this.getClass().getSimpleName(), e.getMessage()));
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher("/Controller?command=open_user_profile&userID=" + userID).forward(request, response);
			} catch (ServiceException e) {
				logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(), this.getClass().getSimpleName(), e.getMessage()));
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			}
		}
	}
}
