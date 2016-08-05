package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.naumovich.film_ordering.bean.User;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class OpenProfile implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int userID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.USER_ID));
		IUserService userService = ServiceFactory.getInstance().getUserService();
		
		try {
			User user = userService.getUserByLogin(userService.getLoginByID(userID));
			request.setAttribute(RequestAndSessionAttributes.USER, user);
			
			request.getRequestDispatcher(JavaServerPageNames.PROFILE_PAGE).forward(request, response);	
		} catch (ServiceException e) {
			request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
		}

	}

}
