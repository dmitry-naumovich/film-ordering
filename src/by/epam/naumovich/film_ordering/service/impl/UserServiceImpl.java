package by.epam.naumovich.film_ordering.service.impl;

import by.epam.naumovich.film_ordering.bean.User;
import by.epam.naumovich.film_ordering.dao.DAOFactory;
import by.epam.naumovich.film_ordering.dao.IUserDAO;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.exception.GetUserServiceException;
import by.epam.naumovich.film_ordering.service.exception.ServiceAuthException;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.ServiceSignUpException;
import by.epam.naumovich.film_ordering.service.util.Validator;

public class UserServiceImpl implements IUserService {

	private static final String MYSQL = "mysql";
	private static final String EMAIL_PATTERN = "^\\w(\\w\\.{4,})@(\\w+\\.)([a-zA-Z]{2,4})$";
	
	@Override
	public User getUserByLogin(String login) throws ServiceException {
		
		if(!Validator.validateStrings(login)){
			throw new ServiceAuthException("Corrupted login!");
		}
		
		User user = null;
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IUserDAO dao = daoFactory.getUserDAO();
			
			user = dao.getUserByLogin(login);
			
			if (user == null) {
				throw new ServiceAuthException("No such login registrated!");
			}
			
		} catch (DAOException e) {
			throw new ServiceException("Error in data source!", e);
		}
		
		return user;
	}

	@Override
	public void checkUserPassword(String login, String password) throws ServiceException {
		
		if(!Validator.validateStrings(login, password)){
			throw new ServiceAuthException("Corrupted login or password!");
		}
		
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IUserDAO dao = daoFactory.getUserDAO();
			
			String realPassw = dao.getPasswordByLogin(login);
			
			if (!realPassw.equals(password)) {
				throw new ServiceAuthException("Wrong password!");
			}
			
		} catch (DAOException e) {
			throw new ServiceException("Error in source!", e);	
		}
	}

	@Override
	public String getLoginByID(int id) throws ServiceException {
		
		if(!Validator.validateObject(id)){
			throw new ServiceException("Corrupted user ID");
		}
		
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IUserDAO dao = daoFactory.getUserDAO();
			
			User user = dao.getUserByID(id);
			if (user == null) {
				throw new GetUserServiceException("No user found by this ID");
			}
			return user.getLogin();
		} catch (DAOException e) {
			throw new ServiceException("Error in source!", e);	
		}
	}

	@Override
	public void addUser(User user) throws ServiceException {
		if (Validator.validateStrings(user.getLogin(), user.getName(), user.getSurname(), user.getPassword())) {
			throw new ServiceSignUpException("At least one of the next fields is corrupted or empty: login, password, name, surname");
		}
		
		if (Validator.validateObject(user.getRegDate())) {
			throw new ServiceSignUpException("Corrupted or empty registration date!");
		}
		
		if (!Validator.validateWithPattern(user.getEmail(), EMAIL_PATTERN)) {
			throw new ServiceSignUpException("Corrupted email!");
		}
		
		try {
			IUserDAO userDAO = DAOFactory.getDAOFactory(MYSQL).getUserDAO();
			// to do
			
			
			
		} catch (DAOException e) {
			throw new ServiceException("Error in source!", e);
		}
		
	}

	@Override
	public int getCurrentUserDiscountByID(int id) throws ServiceException {
		if(!Validator.validateObject(id)){
			throw new ServiceException("Corrupted user ID");
		}
		
		try {
			
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IUserDAO dao = daoFactory.getUserDAO();
			int discount = dao.getCurrentUserDiscountByID(id);
			return discount;
			
		} catch (DAOException e) {
			throw new ServiceException("Error in source!", e);	
		}	
	}
}
