package by.epam.naumovich.film_ordering.service;

import java.util.Set;

import by.epam.naumovich.film_ordering.bean.Discount;
import by.epam.naumovich.film_ordering.bean.User;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public interface IUserService {
	
	int addUser(String login, String name, String surname, String password, 
			String sex, String bDate, String phone, String email, String about, String avatar) throws ServiceException;
	
	void updateUser(int id, String name, String surname, String password, 
			String sex, String bDate, String phone, String email, String about, String avatar) throws ServiceException;
	void deleteUser(int id) throws ServiceException;
	
	User authenticate(String login, String password) throws ServiceException;
	
	User getUserByLogin(String login) throws ServiceException;
	User getUserByID(int id) throws ServiceException;
	String getLoginByID(int id) throws ServiceException;
	
	Discount getCurrentUserDiscountByID(int id) throws ServiceException;
	void addDiscount(int userID, String amount, String endDate, String endTime) throws ServiceException;
	void editDiscount(int discountID, String amount, String endDate, String endTime) throws ServiceException;
	void deleteDiscount(int discountID) throws ServiceException;
	
	Set<User> getAllUsers() throws ServiceException;
	Set<User> getUsersInBanNow() throws ServiceException;
	
	boolean userIsInBan(int id) throws ServiceException;
	void banUser(int userID, String length, String reason) throws ServiceException;
	void unbanUser(int userID) throws ServiceException;
}
