package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.naumovich.film_ordering.bean.User;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.ErrorMessages;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.user.ServiceAuthException;

public class Login implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		
		if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) != null) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.LOG_OUT_FOR_ANOTHER_ACC);
			request.getRequestDispatcher(JavaServerPageNames.INDEX_PAGE).forward(request, response);
		}
		else {
			String login = request.getParameter(RequestAndSessionAttributes.LOGIN);
			String password = request.getParameter(RequestAndSessionAttributes.PASSWORD);
			
			try {
				IUserService userService = ServiceFactory.getInstance().getUserService();
				User user = userService.authenticate(login, password);
				request.setAttribute(RequestAndSessionAttributes.USER, user);
				session.setAttribute(RequestAndSessionAttributes.AUTHORIZED_USER, user.getLogin());
				session.setAttribute(RequestAndSessionAttributes.USER_ID, user.getId());
				session.setAttribute(RequestAndSessionAttributes.IS_ADMIN, 'a' == user.getType());
				
				String prev_query = (String) session.getAttribute(RequestAndSessionAttributes.PREV_QUERY);
				if (prev_query != null) {
					response.sendRedirect(prev_query);
				}
				else {
					request.getRequestDispatcher(JavaServerPageNames.INDEX_PAGE).forward(request, response);
				}
			} catch (ServiceAuthException e) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.LOGINATION_PAGE).forward(request, response);
			}  catch (ServiceException e) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);		
			}
		}
	}
}
