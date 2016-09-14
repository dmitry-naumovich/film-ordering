package by.epam.naumovich.film_ordering.command.impl.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.naumovich.film_ordering.bean.User;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.ErrorMessages;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.user.GetUserServiceException;

public class OpenAllUsers implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		String query = QueryUtil.createHttpQueryString(request);
		session.setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) == null) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.OPEN_ALL_USERS_RESTRICTION);
			request.getRequestDispatcher(JavaServerPageNames.LOGINATION_PAGE).forward(request, response);
		}
		else if (!Boolean.parseBoolean(session.getAttribute(RequestAndSessionAttributes.IS_ADMIN).toString())) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.OPEN_ALL_USERS_RESTRICTION);
			request.getRequestDispatcher(JavaServerPageNames.INDEX_PAGE).forward(request, response);
		}
		else {
			try {
				IUserService userService = ServiceFactory.getInstance().getUserService();
				Set<User> users = userService.getAllUsers();
				List<User> list = new ArrayList<User>(users);
				Collections.reverse(list);
				users = new LinkedHashSet<User>(list);
				
				request.setAttribute(RequestAndSessionAttributes.USERS, users);
				request.getRequestDispatcher(JavaServerPageNames.USERS_PAGE).forward(request, response);
				
			} catch (GetUserServiceException e) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.USERS_PAGE).forward(request, response);
				
			} catch (ServiceException e) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			}
		
		}
	}

}
