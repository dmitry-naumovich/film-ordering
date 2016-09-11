package by.epam.naumovich.film_ordering.command.impl.film;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.ErrorMessages;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.command.util.SuccessMessages;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class DeleteFilm implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		int filmID = Integer.valueOf(request.getParameter(RequestAndSessionAttributes.FILM_ID));
		 
		if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) == null) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.DELETE_FILM_RESTRICTION);
			request.getRequestDispatcher(JavaServerPageNames.LOGINATION_PAGE).forward(request, response);
		}
		else if (!Boolean.parseBoolean(session.getAttribute(RequestAndSessionAttributes.IS_ADMIN).toString())) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.DELETE_FILM_RESTRICTION);
			request.getRequestDispatcher("/Controller?command=open_film_page&filmID=" + filmID).forward(request, response);
		}
		else {
			try {
				IFilmService filmService = ServiceFactory.getInstance().getFilmService();
				filmService.deleteFilm(filmID);
				
				request.setAttribute(RequestAndSessionAttributes.SUCCESS_MESSAGE, SuccessMessages.FILM_DELETED);
				request.getRequestDispatcher("/Controller?command=open_film_list").forward(request, response);
			} catch (ServiceException e) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			}
		}

	}

}
