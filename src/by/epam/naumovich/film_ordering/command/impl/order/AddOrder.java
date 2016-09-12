package by.epam.naumovich.film_ordering.command.impl.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.ErrorMessages;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.command.util.SuccessMessages;
import by.epam.naumovich.film_ordering.service.IOrderService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.order.AddOrderServiceException;

public class AddOrder implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) == null) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.ADD_ORDER_RESTRICTION);
			request.getRequestDispatcher(JavaServerPageNames.LOGINATION_PAGE).forward(request, response);
		}
		else if (Boolean.parseBoolean(session.getAttribute(RequestAndSessionAttributes.IS_ADMIN).toString())) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.ADMIN_CAN_NOT_ORDER);
			request.getRequestDispatcher("/Controller?open_film_page&filmID=" + Integer.parseInt(request.getParameter(RequestAndSessionAttributes.FILM_ID)));
		}
		else {
			int filmID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.FILM_ID));
			int userID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.USER_ID));
			String price = request.getParameter(RequestAndSessionAttributes.PRICE);
			String discount = request.getParameter(RequestAndSessionAttributes.DISCOUNT);
			String payment = request.getParameter(RequestAndSessionAttributes.PAYMENT);
			
			try {
				IOrderService orderService = ServiceFactory.getInstance().getOrderService();
				int orderNum = orderService.addOrder(filmID, userID, price, discount, payment);
				
				request.setAttribute(RequestAndSessionAttributes.SUCCESS_MESSAGE, SuccessMessages.ORDER_ADDED);
				request.getRequestDispatcher("/Controller?command=open_single_orders&orderNum=" + orderNum).forward(request, response);
			} catch (AddOrderServiceException e) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher("/Controller?command=open_order_page&filmID=" + filmID).forward(request, response);
			} catch (ServiceException e) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			}
		}

	}

}