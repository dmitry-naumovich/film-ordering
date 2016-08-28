package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.naumovich.film_ordering.bean.User;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.GetUserServiceException;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class OpenUserProfile implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession(true);
		String query = QueryUtil.createHttpQueryString(request);
		session.setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		if (request.getParameter(RequestAndSessionAttributes.USER_ID).equals(RequestAndSessionAttributes.EMPTY_STRING)) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, "Sign in to open profile page");
			request.getRequestDispatcher(JavaServerPageNames.LOGINATION_PAGE).forward(request, response);
		}
		else {
			int userID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.USER_ID));
			IUserService userService = ServiceFactory.getInstance().getUserService();
			try {
				User user = userService.getUserByLogin(userService.getLoginByID(userID));
				request.setAttribute(RequestAndSessionAttributes.USER, user);
				
				request.getRequestDispatcher(JavaServerPageNames.PROFILE_PAGE).forward(request, response);	
			} catch (GetUserServiceException e) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.PROFILE_PAGE).forward(request, response);
				
			} catch (ServiceException e) {
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			}
		}
	}

}
