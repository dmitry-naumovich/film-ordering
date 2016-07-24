package by.epam.naumovich.film_ordering.service;

import by.epam.naumovich.film_ordering.bean.User;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public interface IUserService {
	
	User getUserByLogin(String login) throws ServiceException;
	boolean checkUserPassword(String login, String password) throws ServiceException;
	
}
