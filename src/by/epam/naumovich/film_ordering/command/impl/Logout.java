package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.naumovich.film_ordering.command.Command;

public class Logout implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			session.invalidate();
		}
		request.getRequestDispatcher(JavaServerPageNames.INDEX_PAGE).forward(request, response);
		response.encodeRedirectURL(JavaServerPageNames.INDEX_PAGE);
	}

}
