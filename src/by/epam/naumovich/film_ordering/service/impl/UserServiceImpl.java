package by.epam.naumovich.film_ordering.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

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
import by.epam.naumovich.film_ordering.service.util.ExceptionMessages;
import by.epam.naumovich.film_ordering.service.util.Validator;

public class UserServiceImpl implements IUserService {

	private static final String MYSQL = "mysql";
	private static final int MIN_YEAR = 1920;
	private static final int PHONE_NUM_LENGTH = 9;
	private static final char USER_TYPE_CLIENT = 'c';
	
	private static final String EMAIL_PATTERN = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[a-zA-Z]{2,4}$";
	private static final String LOGIN_PATTERN = "(^[a-zA-Z]{3,})[a-zA-Z0-9]*";
	private static final String PASSWORD_PATTERN = "^[a-zA-Zà-ÿÀ-ß0-9_-]{4,30}$";
	
	@Override
	public int addUser(String login, String name, String surname, String password, String sex, String bDate,
			String phone, String email, String about, String avatar) throws ServiceException {

		try {
			IUserDAO userDAO = DAOFactory.getDAOFactory(MYSQL).getUserDAO();
			User existingUser = userDAO.getUserByLogin(login);
			if (existingUser != null) {
				throw new ServiceSignUpException(ExceptionMessages.ALREADY_TAKEN_LOGIN);
			}
			
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		if (!Validator.validateWithPattern(login, LOGIN_PATTERN)) {
			throw new ServiceSignUpException(ExceptionMessages.INVALID_LOGIN);
		}
		if (!Validator.validateWithPattern(password, PASSWORD_PATTERN)) {
			throw new ServiceSignUpException(ExceptionMessages.INVALID_PASSWORD);
		}
		if (!Validator.validateWithPattern(email, EMAIL_PATTERN)) {
			throw new ServiceSignUpException(ExceptionMessages.INVALID_EMAIL);
		}
		if (!Validator.validateStrings(name, surname, sex)) {
			throw new ServiceSignUpException(ExceptionMessages.CORRUPTED_NAME_SURN_SEX);
		}
		
		User newUser = new User();
		newUser.setLogin(login);
		newUser.setName(name);
		newUser.setSurname(surname);
		newUser.setPassword(password);
		newUser.setSex(sex.charAt(0));
		newUser.setType(USER_TYPE_CLIENT);
		
		newUser.setRegDate(Date.valueOf(LocalDate.now()));
		newUser.setRegTime(Time.valueOf(LocalTime.now()));
		
		if (Validator.validateStrings(bDate)) { 
			try {
				Date currentDate = Date.valueOf(LocalDate.now());
				Date birthDate = Date.valueOf(bDate);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(birthDate);
				
				if (birthDate.after(currentDate) | calendar.get(Calendar.YEAR) <= MIN_YEAR) {
					throw new UserUpdateServiceException(String.format(ExceptionMessages.INVALID_BIRTHDATE, MIN_YEAR));
				}
				newUser.setBirthDate(Date.valueOf(bDate));
			} catch (IllegalArgumentException e) {
				throw new UserUpdateServiceException(ExceptionMessages.BIRTHDATE_RIGHT_FORMAT);
			}
		}
		if (Validator.validateStrings(phone)) { 
			if (phone.length() != PHONE_NUM_LENGTH) {
				throw new UserUpdateServiceException(String.format(ExceptionMessages.INVALID_PHONE_NUM, PHONE_NUM_LENGTH));
			}
			newUser.setPhone(phone); 
		}
		newUser.setEmail(email);
		
		if (Validator.validateStrings(about)) { newUser.setAbout(about); }
		
		int newUserID = 0;
		try {
			IUserDAO userDAO = DAOFactory.getDAOFactory(MYSQL).getUserDAO();
			newUserID = userDAO.addUser(newUser);
			if (newUserID == 0) {
				throw new ServiceSignUpException(ExceptionMessages.UNSUCCESSFULL_SIGN_UP);
			}
			newUser.setId(newUserID);
			
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		return newUserID;
	}

	@Override
	public void updateUser(int id, String name, String surname, String password, String sex, String bDate, String phone,
			String email, String about, String avatar) throws ServiceException {

		if (!Validator.validateWithPattern(password, PASSWORD_PATTERN)) {
			throw new UserUpdateServiceException(ExceptionMessages.INVALID_PASSWORD);
		}
		
		if (!Validator.validateWithPattern(email, EMAIL_PATTERN)) {
			throw new UserUpdateServiceException(ExceptionMessages.INVALID_EMAIL);
		}
		
		if (!Validator.validateStrings(name, surname, sex)) {
			throw new UserUpdateServiceException(ExceptionMessages.CORRUPTED_NAME_SURN_SEX);
		}
		
		User updUser = new User();
		updUser.setName(name);
		updUser.setSurname(surname);
		updUser.setPassword(password);
		updUser.setSex(sex.charAt(0));
		updUser.setEmail(email);

		if (Validator.validateStrings(bDate)) { 
			try {
				Date currentDate = Date.valueOf(LocalDate.now());
				Date birthDate = Date.valueOf(bDate);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(birthDate);
				
				if (birthDate.after(currentDate) | calendar.get(Calendar.YEAR) <= MIN_YEAR) {
					throw new UserUpdateServiceException(String.format(ExceptionMessages.INVALID_BIRTHDATE, MIN_YEAR));
				}
				updUser.setBirthDate(Date.valueOf(bDate));
			} catch (IllegalArgumentException e) {
				throw new UserUpdateServiceException(ExceptionMessages.BIRTHDATE_RIGHT_FORMAT);
			}
		}
		if (Validator.validateStrings(phone)) { 
			if (phone.length() != PHONE_NUM_LENGTH) {
				throw new UserUpdateServiceException(String.format(ExceptionMessages.INVALID_PHONE_NUM, PHONE_NUM_LENGTH));
			}
			updUser.setPhone(phone); 
		}
		if (Validator.validateStrings(about)) { updUser.setAbout(about); }
		
		try {
			IUserDAO userDAO = DAOFactory.getDAOFactory(MYSQL).getUserDAO();
			userDAO.updateUser(id, updUser);
			
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
	}
	
	@Override
	public void deleteUser(int id) throws ServiceException {
		if (!Validator.validateInt(id)) {
			throw new ServiceException(ExceptionMessages.CORRUPTED_USER_ID);
		}
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IUserDAO dao = daoFactory.getUserDAO();
			dao.deleteUser(id);
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		
		
	}
	
	@Override
	public User getUserByLogin(String login) throws ServiceException {
		if(!Validator.validateStrings(login)){
			throw new ServiceAuthException(ExceptionMessages.CORRUPTED_LOGIN);
		}
		
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IUserDAO dao = daoFactory.getUserDAO();
			User user = dao.getUserByLogin(login);
			if (user == null) {
				throw new ServiceAuthException(ExceptionMessages.LOGIN_NOT_REGISTRATED);
			}
			return user;
			
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}
	

	@Override
	public User getUserByID(int id) throws ServiceException {
		if(!Validator.validateInt(id)){
			throw new GetUserServiceException(ExceptionMessages.CORRUPTED_USER_ID);
		}
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IUserDAO dao = daoFactory.getUserDAO();
			User user = dao.getUserByID(id);
			if (user == null) {
				throw new GetUserServiceException(ExceptionMessages.USER_NOT_FOUND);
			}
			return user;
			
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	@Override
	public void checkUserPassword(String login, String password) throws ServiceException {
		
		if(!Validator.validateStrings(login, password)){
			throw new ServiceAuthException(ExceptionMessages.CORRUPTED_LOGIN_OR_PWD);
		}
		
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IUserDAO dao = daoFactory.getUserDAO();
			
			String realPassw = dao.getPasswordByLogin(login);
			
			if (!realPassw.equals(password)) {
				throw new ServiceAuthException(ExceptionMessages.WRONG_PASSWORD);
			}
			
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);	
		}
	}

	@Override
	public String getLoginByID(int id) throws ServiceException {
		
		if(!Validator.validateInt(id)){
			throw new ServiceException(ExceptionMessages.CORRUPTED_USER_ID);
		}
		
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IUserDAO dao = daoFactory.getUserDAO();
			
			User user = dao.getUserByID(id);
			if (user == null) {
				throw new GetUserServiceException(ExceptionMessages.USER_NOT_FOUND);
			}
			return user.getLogin();
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);	
		}
	}

	@Override
	public int getCurrentUserDiscountByID(int id) throws ServiceException {
		if(!Validator.validateObject(id)){
			throw new ServiceException(ExceptionMessages.CORRUPTED_USER_ID);
		}
		
		try {
			
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IUserDAO dao = daoFactory.getUserDAO();
			int discount = dao.getCurrentUserDiscountByID(id);
			return discount;
			
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);	
		}	
	}
}
