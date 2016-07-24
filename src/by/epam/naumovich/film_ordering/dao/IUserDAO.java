package by.epam.naumovich.film_ordering.dao;

import java.util.List;

import by.epam.naumovich.film_ordering.bean.User;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;

public interface IUserDAO {

	void addUser(User user) throws DAOException;
	void deleteUser(User user) throws DAOException;
	
	List<User> getAllUsers() throws DAOException;
	List<User> getUsersInBan() throws DAOException;
	
	User getUserByLogin(String login) throws DAOException;
	
	boolean userIsInBan(int id) throws DAOException;
	void updateUser(int id, User updatedUser) throws DAOException;
	String getPasswordByLogin(String login) throws DAOException;
	
}
