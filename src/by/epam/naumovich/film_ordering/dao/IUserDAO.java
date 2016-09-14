package by.epam.naumovich.film_ordering.dao;

import java.util.Set;

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
	
	boolean userIsInBan(int id) throws DAOException;
	String getPasswordByLogin(String login) throws DAOException;
	int getCurrentUserDiscountByID(int id) throws DAOException;
	
}
