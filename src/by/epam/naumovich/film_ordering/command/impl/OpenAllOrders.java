package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.naumovich.film_ordering.bean.Order;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.IOrderService;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class OpenAllOrders implements Command {

	private static final String VIEW_TYPE = "all";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		IOrderService orderService = ServiceFactory.getInstance().getOrderService();
		IFilmService filmService = ServiceFactory.getInstance().getFilmService();
		IUserService userService = ServiceFactory.getInstance().getUserService();
		
		String query = QueryUtil.createHttpQueryString(request);
		request.getSession(true).setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		try {
			List<Order> orders = orderService.getAllOrders();
			Collections.reverse(orders);
			
			List<String> filmNames = new ArrayList<String>();
			List<String> userLogins = new ArrayList<String>();
			for (Order o : orders) {
				String filmName = filmService.getFilmByID(o.getFilmId()).getName();
				filmNames.add(filmName);
				
				String userLogin = userService.getLoginByID(o.getUserId());
				userLogins.add(userLogin);
			}
			
			request.setAttribute(RequestAndSessionAttributes.ORDERS, orders);
			request.setAttribute(RequestAndSessionAttributes.FILM_NAMES, filmNames);
			request.setAttribute(RequestAndSessionAttributes.USER_LOGINS, userLogins);
			request.setAttribute(RequestAndSessionAttributes.ORDER_VIEW_TYPE, VIEW_TYPE);
			
			String url = response.encodeRedirectURL(JavaServerPageNames.ORDERS_PAGE);
			request.getRequestDispatcher(url).forward(request, response);
		}
		catch (ServiceException e) {
			request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
		}
	}
}