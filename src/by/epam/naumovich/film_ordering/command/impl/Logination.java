package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		
		IUserService userService = ServiceFactory.getInstance().getUserService();

		String query = QueryUtil.createHttpQueryString(request);
		request.getSession(true).setAttribute("prev_query", query);
		
		
		System.out.println(query);
		
		
		try {
			User user = userService.getUserByLogin(login);
			userService.checkUserPassword(login, password); // boolean
			
			request.setAttribute("user", user);
			
			request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request, response);
		} catch (ServiceAuthException e) {
			
			request.setAttribute("errorMessage", "Wrong login or password");
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}  catch (ServiceException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);		
		}

	}

}
