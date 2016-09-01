package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.naumovich.film_ordering.bean.Order;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.IOrderService;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.order.GetOrdersServiceException;

public class OpenUserOrders implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		String query = QueryUtil.createHttpQueryString(request);
		session.setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) == null) {
			if (request.getParameter(RequestAndSessionAttributes.USER_ID).isEmpty() || request.getParameter(RequestAndSessionAttributes.USER_ID) == null) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, "Sign in to see your orders");
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
			IOrderService orderService = ServiceFactory.getInstance().getOrderService();
			IFilmService filmService = ServiceFactory.getInstance().getFilmService();
			IUserService userService = ServiceFactory.getInstance().getUserService();
			
			try {
				List<Order> orders = orderService.getOrdersByUserId(userID);
				Collections.reverse(orders);
				
				List<String> filmNames = new ArrayList<String>();
				for (Order o : orders) {
					String filmName = filmService.getFilmByID(o.getFilmId()).getName();
					filmNames.add(filmName);
				}
				String userLogin = userService.getLoginByID(userID);
				
				request.setAttribute(RequestAndSessionAttributes.ORDERS, orders);
				request.setAttribute(RequestAndSessionAttributes.FILM_NAMES, filmNames);
				request.setAttribute(RequestAndSessionAttributes.USER_LOGIN, userLogin);
				request.setAttribute(RequestAndSessionAttributes.USER_ID, userID);
				request.setAttribute(RequestAndSessionAttributes.ORDER_VIEW_TYPE, RequestAndSessionAttributes.VIEW_TYPE_USER);
				
				String url = response.encodeRedirectURL(JavaServerPageNames.ORDERS_PAGE);
				request.getRequestDispatcher(url).forward(request, response);
				
			} catch (GetOrdersServiceException e) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.ORDERS_PAGE).forward(request, response);
				
			} catch (ServiceException e) {
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			}
		}
	}

}
