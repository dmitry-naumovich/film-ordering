package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class OpenOrderPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int filmID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.FILM_ID));
		IFilmService filmService = ServiceFactory.getInstance().getFilmService();
		IUserService userService = ServiceFactory.getInstance().getUserService();
		
		String query = QueryUtil.createHttpQueryString(request);
		request.getSession(true).setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		try {
			Film film = filmService.getFilmByID(filmID);
			int discount = userService.getCurrentUserDiscountByID((int)request.getSession().getAttribute(RequestAndSessionAttributes.USER_ID));
			
			request.setAttribute(RequestAndSessionAttributes.FILM, film);
			request.setAttribute(RequestAndSessionAttributes.DISCOUNT_AMOUNT, discount);
			
			float orderSum = film.getPrice() * (1.0f - discount/100f);   
			request.setAttribute(RequestAndSessionAttributes.ORDER_SUM, orderSum);
			
			request.getRequestDispatcher(JavaServerPageNames.ORDER_PAGE).forward(request, response);
			
		} catch (ServiceException e) {
			request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
		}
		
	}

}
