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

public class Logination implements Command {

	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String AUTHORIZED_USER = "authUser";
	private static final String USER_ID = "userId";
	private static final String USER_TYPE = "userType";
	private static final String ERROR_MESSAGE = "errorMessage";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		
		IUserService userService = ServiceFactory.getInstance().getUserService();

		String query = QueryUtil.createHttpQueryString(request);
		request.getSession(true).setAttribute("prev_query", query);
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
			request.setAttribute("user", user);
			session.setAttribute(AUTHORIZED_USER, user.getLogin());
			session.setAttribute(USER_ID, user.getId());
			session.setAttribute(USER_TYPE, user.getType());
			request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request, response);
		} catch (ServiceAuthException e) {
			session.setAttribute(ERROR_MESSAGE, e.getMessage());
			request.setAttribute(ERROR_MESSAGE, e.getMessage());
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}  catch (ServiceException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);		
		}

	}

}
