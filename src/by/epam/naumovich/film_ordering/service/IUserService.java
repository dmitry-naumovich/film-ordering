package by.epam.naumovich.film_ordering.service;

import by.epam.naumovich.film_ordering.bean.User;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public interface IUserService {
	
	int addUser(String login, String name, String surname, String password, 
			String sex, String bDate, String phone, String email, String about, String avatar) throws ServiceException;
	
	void updateUser(int id, String name, String surname, String password, 
			String sex, String bDate, String phone, String email, String about, String avatar) throws ServiceException;
	
	User getUserByLogin(String login) throws ServiceException;
	User getUserByID(int id) throws ServiceException;
	String getLoginByID(int id) throws ServiceException;
	void checkUserPassword(String login, String password) throws ServiceException;
	int getCurrentUserDiscountByID(int id) throws ServiceException;
}
