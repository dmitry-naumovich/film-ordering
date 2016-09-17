package by.epam.naumovich.film_ordering.command.impl.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.LogMessages;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;

public class Logout implements Command {
	
	private static final Logger logger = LogManager.getLogger(Logger.class.getName());
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		String prev_query = (String) session.getAttribute(RequestAndSessionAttributes.PREV_QUERY);
		
		if (session != null) {
			String login = session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER).toString();
			int id = Integer.parseInt(session.getAttribute(RequestAndSessionAttributes.USER_ID).toString());
			logger.debug(String.format(LogMessages.USER_LOGGED_OUT, login, id));
			session.invalidate();
		}
		
		if (prev_query != null) {
			response.sendRedirect(prev_query);
		}
		else {
			request.getRequestDispatcher(JavaServerPageNames.INDEX_PAGE).forward(request, response);
		}
	}
}
