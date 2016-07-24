package by.epam.naumovich.film_ordering.service;

import by.epam.naumovich.film_ordering.service.impl.UserServiceImpl;

public class ServiceFactory {
	
	private static final ServiceFactory factory = new ServiceFactory();
	
	public static ServiceFactory getInstance() {
		return factory;
	}
	
	public IUserService getUserService() {
		return new UserServiceImpl();
	}

}
