package by.epam.naumovich.film_ordering.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import by.epam.naumovich.film_ordering.bean.Discount;
import by.epam.naumovich.film_ordering.bean.User;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;

public interface IUserDAO {

	int addUser(User user) throws DAOException;
	void updateUser(int id, User updatedUser) throws DAOException;
	void deleteUser(int id) throws DAOException;
	
	Set<User> getAllUsers() throws DAOException;
	Set<User> getUsersInBan() throws DAOException;
	
	User getUserByID(int id) throws DAOException;
	User getUserByLogin(String login) throws DAOException;
	
	String getPasswordByLogin(String login) throws DAOException;
	Discount getCurrentUserDiscountByID(int id) throws DAOException;
	void addDiscount(Discount discount) throws DAOException;
	void editDiscount(Discount editedDisc) throws DAOException;
	void deleteDiscount(int discountID) throws DAOException;
	
	boolean userIsInBan(int id) throws DAOException;
	void banUser(int userID, Date startDate, Time startTime, int length, String reason) throws DAOException;
	void unbanUser(int userID) throws DAOException;
	String getCurrentBanEnd(int userID) throws DAOException;
	String getCurrentBanReason(int userID) throws DAOException;
	
}
