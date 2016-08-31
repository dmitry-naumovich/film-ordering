package by.epam.naumovich.film_ordering.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import by.epam.naumovich.film_ordering.bean.User;
import by.epam.naumovich.film_ordering.dao.DAOFactory;
import by.epam.naumovich.film_ordering.dao.IUserDAO;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.user.GetUserServiceException;
import by.epam.naumovich.film_ordering.service.exception.user.ServiceAuthException;
import by.epam.naumovich.film_ordering.service.exception.user.ServiceSignUpException;
import by.epam.naumovich.film_ordering.service.exception.user.UserUpdateServiceException;
import by.epam.naumovich.film_ordering.service.exception.user.WrongEmailFormException;
import by.epam.naumovich.film_ordering.service.util.ExceptionMessages;
import by.epam.naumovich.film_ordering.service.util.Validator;

public class UserServiceImpl implements IUserService {

	private static final String MYSQL = "mysql";
	//private static final String EMAIL_PATTERN = "^\\w(\\w\\.{4,})@(\\w+\\.)([a-zA-Z]{2,4})$";
	private final static String EMAIL_PATTERN = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[a-zA-Z]{2,4}$";
	private final static String LOGIN_PATTERN = "(^[a-zA-Z]{3,})[a-zA-Z0-9]*";
	private final static String PASSWORD_PATTERN = "^[a-zA-Zà-ÿÀ-ß0-9_-]{4,30}$";
	
	@Override
	public void addUser(String login, String name, String surname, String password, String sex, String bDate,
			String phone, String email, String about, String avatar) throws ServiceException {

		if (Validator.validateStrings(login, name, surname, password)) {
			throw new ServiceSignUpException("At least one of the next fields is corrupted or empty: login, password, name, surname");
		}
		
		if (!Validator.validateWithPattern(login, LOGIN_PATTERN)) {
			throw new ServiceSignUpException("Login must start with the letter and consist at least 3 symbols (latin letters and numbers)");
		}
		
		if (!Validator.validateWithPattern(password, PASSWORD_PATTERN)) {
			throw new ServiceSignUpException("Password must be at least 4 symbols");
		}
		
		if (!Validator.validateWithPattern(email, EMAIL_PATTERN)) {
			throw new WrongEmailFormException(ExceptionMessages.INVALID_EMAIL_MESSAGE);
		}
		
		User newUser = new User();
		newUser.setLogin(login);
		newUser.setName(name);
		newUser.setPassword(password);
		
		newUser.setRegDate(Date.valueOf(LocalDate.now())); // current date
		
		try {
			IUserDAO userDAO = DAOFactory.getDAOFactory(MYSQL).getUserDAO();
			userDAO.addUser(newUser);
			
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR_MESSAGE, e);
		}
	}

	@Override
	public void updateUser(int id, String name, String surname, String password, String sex, String bDate, String phone,
			String email, String about, String avatar) throws ServiceException {

		if (!Validator.validateWithPattern(password, PASSWORD_PATTERN)) {
			throw new UserUpdateServiceException("Password must be at least 4 symbols");
		}
		
		if (!Validator.validateWithPattern(email, EMAIL_PATTERN)) {
			throw new WrongEmailFormException(ExceptionMessages.INVALID_EMAIL_MESSAGE);
		}
		
		if (!Validator.validateStrings(name, surname, sex)) {
			throw new UserUpdateServiceException("At least one of the next fields is corrupted or empty: login, password, name, surname");
		}
		User updUser = new User();
		updUser.setName(name);
		updUser.setSurname(surname);
		updUser.setSex(sex.charAt(0));
		updUser.setPassword(password);

		if (Validator.validateStrings(bDate)) { 
			//try {
			
			//DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-mm-dd" , Locale.getDefault() );
			//ZonedDateTime zdt = formatter.parse ( bDate , ZonedDateTime :: from );
			Date date = Date.valueOf(bDate);
			updUser.setBirthDate(Date.valueOf(bDate)); 
			System.out.println("HERE 3");
			/*} catch (IllegalArgumentException e) {
				throw new UserUpdateServiceException("Birthdate must follow \"YYYY-MM-DD\" format");
			}*/
		}
		if (Validator.validateStrings(phone)) { updUser.setPhone(phone); }
		if (Validator.validateStrings(about)) { updUser.setAbout(about); }
		
		try {
			IUserDAO userDAO = DAOFactory.getDAOFactory(MYSQL).getUserDAO();
			System.out.println("HERE 3");
			userDAO.updateUser(id, updUser);
			
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR_MESSAGE, e);
		}
		
	}
	
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
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR_MESSAGE, e);
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
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR_MESSAGE, e);	
		}
	}

	@Override
	public String getLoginByID(int id) throws ServiceException {
		
		if(!Validator.validateInt(id)){
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
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR_MESSAGE, e);	
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
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR_MESSAGE, e);	
		}	
	}
}
