package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.naumovich.film_ordering.bean.News;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.service.INewsService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class GetSidebarNews implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		INewsService newsService = ServiceFactory.getInstance().getNewsService();
		
		try {
			List<News> newsList = newsService.getFourLastNews();
			request.setAttribute(RequestAndSessionAttributes.SIDEBAR_NEWS, newsList);
		} catch (ServiceException e) {
			request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);		
		}

	}

}
