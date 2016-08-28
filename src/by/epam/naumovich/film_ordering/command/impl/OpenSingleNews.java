package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.naumovich.film_ordering.bean.News;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.service.INewsService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.GetNewsServiceException;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class OpenSingleNews implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int newsID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.NEWS_ID));
		INewsService newsService = ServiceFactory.getInstance().getNewsService();
		
		String query = QueryUtil.createHttpQueryString(request);
		request.getSession(true).setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		try {
			News news = newsService.getNewsById(newsID);
			request.setAttribute(RequestAndSessionAttributes.NEWS, news);
			
			request.getRequestDispatcher(JavaServerPageNames.SINGLE_NEWS_PAGE).forward(request, response);
		} catch (GetNewsServiceException e) {
			
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
			request.getRequestDispatcher(JavaServerPageNames.SINGLE_NEWS_PAGE).forward(request, response);
		}
		
		catch (ServiceException e) {
			request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
		}

	}

}
