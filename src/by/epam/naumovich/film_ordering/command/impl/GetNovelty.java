package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class GetNovelty implements Command {
		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		IFilmService filmService = ServiceFactory.getInstance().getFilmService();
		String query = QueryUtil.createHttpQueryString(request);
		request.getSession(true).setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		
		try {
			List<Film> filmList = filmService.getTwelveLastAddedFilms();
			request.setAttribute(RequestAndSessionAttributes.NOVELTY_LIST, filmList);
		} catch (ServiceException e) {
			request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);		
		}
	}

}
