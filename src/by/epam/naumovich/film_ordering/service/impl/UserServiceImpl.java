package by.epam.naumovich.film_ordering.service.impl;

import by.epam.naumovich.film_ordering.bean.User;
import by.epam.naumovich.film_ordering.dao.DAOFactory;
import by.epam.naumovich.film_ordering.dao.IUserDAO;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.exception.ServiceAuthException;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.util.Validator;

public class UserServiceImpl implements IUserService {

	private static final String MYSQL = "mysql";
	
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
				throw new ServiceException("No user found by this ID");
			}
			return user.getLogin();
		} catch (DAOException e) {
			throw new ServiceException("Error in source!", e);	
		}
	}
		

}
