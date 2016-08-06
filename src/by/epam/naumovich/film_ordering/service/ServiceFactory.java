package by.epam.naumovich.film_ordering.service;

import by.epam.naumovich.film_ordering.service.impl.FilmServiceImpl;
import by.epam.naumovich.film_ordering.service.impl.NewsServiceImpl;
import by.epam.naumovich.film_ordering.service.impl.OrderServiceImpl;
import by.epam.naumovich.film_ordering.service.impl.ReviewServiceImpl;
import by.epam.naumovich.film_ordering.service.impl.UserServiceImpl;

public class ServiceFactory {
	
	private static final ServiceFactory factory = new ServiceFactory();
	
	public static ServiceFactory getInstance() {
		return factory;
	}
	
	public IUserService getUserService() {
		return new UserServiceImpl();
	}
	
	public IFilmService getFilmService() {
		return new FilmServiceImpl();
	}
	
	public IReviewService getReviewService() {
		return new ReviewServiceImpl();
	}
	
	public INewsService getNewsService() {
		return new NewsServiceImpl();
	}
	
	public IOrderService getOrderService() {
		return new OrderServiceImpl();
	}
	

}
