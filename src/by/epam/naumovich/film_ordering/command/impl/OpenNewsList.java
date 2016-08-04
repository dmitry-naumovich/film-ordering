package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.naumovich.film_ordering.bean.News;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.service.INewsService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.GetNewsServiceException;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class OpenNewsList implements Command {

	private static final String NEWS = "news";
	private static final String PREV_QUERY = "prev_query";
	
	private static final String NEWS_JSP_PAGE = "jsp/news.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		INewsService newsService = ServiceFactory.getInstance().getNewsService();
		
		String query = QueryUtil.createHttpQueryString(request);
		request.getSession(true).setAttribute(PREV_QUERY, query);
		System.out.println(query);
		
		try {
			List<News> news = newsService.getAllNews();
			Collections.reverse(news); // reverse for showing most recent news first
			request.setAttribute(NEWS, news);
			
			String url = response.encodeRedirectURL(NEWS_JSP_PAGE);
			request.getRequestDispatcher(url).forward(request, response);
			
			
		} catch(GetNewsServiceException e) {
			request.getRequestDispatcher(NEWS_JSP_PAGE).forward(request, response);
		}
		
		catch (ServiceException e) {
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
	}

}
