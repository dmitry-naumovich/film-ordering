package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.naumovich.film_ordering.bean.User;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceAuthException;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class Login implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String login = request.getParameter(RequestAndSessionAttributes.LOGIN);
		String password = request.getParameter(RequestAndSessionAttributes.PASSWORD);
		
		IUserService userService = ServiceFactory.getInstance().getUserService();

		String query = QueryUtil.createHttpQueryString(request);
		request.getSession(true).setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		HttpSession session = request.getSession();
		
		/*String curLanguage = (String)session.getAttribute(LANGUAGE);
		Locale currentLocale = null;
		if(curLanguage != null) {
			currentLocale = new Locale(curLanguage);
		} else {
			currentLocale = new Locale("en");
		}*/
		
		try {
			User user = userService.getUserByLogin(login);
			userService.checkUserPassword(login, password);
			request.setAttribute(RequestAndSessionAttributes.USER, user);
			session.setAttribute(RequestAndSessionAttributes.AUTHORIZED_USER, user.getLogin());
			session.setAttribute(RequestAndSessionAttributes.USER_ID, user.getId());
			session.setAttribute(RequestAndSessionAttributes.IS_ADMIN, 'a' == user.getType());
			request.getRequestDispatcher(JavaServerPageNames.INDEX_PAGE).forward(request, response);
		} catch (ServiceAuthException e) {
			session.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
			
			request.getRequestDispatcher(JavaServerPageNames.LOGINATION_PAGE).forward(request, response);
			
		}  catch (ServiceException e) {
			request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);		
		}

	}

}
