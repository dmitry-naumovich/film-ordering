package by.epam.naumovich.film_ordering.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

import by.epam.naumovich.film_ordering.bean.Order;
import by.epam.naumovich.film_ordering.dao.DAOFactory;
import by.epam.naumovich.film_ordering.dao.IOrderDAO;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;
import by.epam.naumovich.film_ordering.service.IOrderService;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.order.AddOrderServiceException;
import by.epam.naumovich.film_ordering.service.exception.order.GetOrderServiceException;
import by.epam.naumovich.film_ordering.service.util.ExceptionMessages;
import by.epam.naumovich.film_ordering.service.util.Validator;

/**
 * IOrderService interface implementation that works with IOrderDAO implementation
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 */
public class OrderServiceImpl implements IOrderService {

	private static final String MYSQL = "mysql";

	@Override
	public int addOrder(int filmID, int userID, String price, String discount, String payment) throws ServiceException {
		if (!Validator.validateInt(filmID) || !Validator.validateInt(userID) || !Validator.validateStrings(price, discount, payment)) {
			throw new AddOrderServiceException(ExceptionMessages.CORRUPTED_INPUT_PARAMETERS);
		}
		
		Order newOrder = new Order();
		newOrder.setFilmId(filmID);
		newOrder.setUserId(userID);
		newOrder.setDate(Date.valueOf(LocalDate.now()));
		newOrder.setTime(Time.valueOf(LocalTime.now()));
		
		try {
			float fPrice = Float.parseFloat(price);
			int fDiscount = Integer.parseInt(discount);
			float fPayment = Float.parseFloat(payment);
			newOrder.setPrice(fPrice);
			newOrder.setDiscount(fDiscount);
			newOrder.setPayment(fPayment);
			
		} catch (NumberFormatException e) {
			throw new AddOrderServiceException(ExceptionMessages.CORRUPTED_INPUT_PARAMETERS);
		}
		int orderNum = 0;
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IOrderDAO orderDAO = daoFactory.getOrderDAO();
			orderNum = orderDAO.addOrder(newOrder);
			if (orderNum == 0) {
				throw new AddOrderServiceException(ExceptionMessages.ORDER_NOT_ADDED);
			}
			newOrder.setOrdNum(orderNum);
			
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		return orderNum;
	}

	@Override
	public void deleteOrder(int orderNum) throws ServiceException {
		if (!Validator.validateInt(orderNum)) {
			throw new ServiceException(ExceptionMessages.CORRUPTED_INPUT_PARAMETERS);
		}
		
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IOrderDAO orderDAO = daoFactory.getOrderDAO();
			orderDAO.deleteOrder(orderNum);
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}
	
	@Override
	public Order getOrderByOrderNum(int orderNum) throws ServiceException {
		if (!Validator.validateInt(orderNum)) {
			throw new ServiceException(ExceptionMessages.CORRUPTED_INPUT_PARAMETERS);
		}
		
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IOrderDAO orderDAO = daoFactory.getOrderDAO();
			Order order = orderDAO.getOrderByOrderNum(orderNum);
			if (order == null) {
				throw new GetOrderServiceException(ExceptionMessages.ORDER_NOT_FOUND);
			}
			return order;
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}


	@Override
	public Order getOrderByUserAndFilmId(int userID, int filmID) throws ServiceException {
		if (!Validator.validateInt(userID) || !Validator.validateInt(filmID)) {
			throw new ServiceException(ExceptionMessages.CORRUPTED_INPUT_PARAMETERS);
		}
		
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IOrderDAO orderDAO = daoFactory.getOrderDAO();
			Order order = orderDAO.getOrderByUserAndFilmId(userID, filmID);
			if (order == null) {
				throw new GetOrderServiceException(ExceptionMessages.NO_FILM_USER_ORDER);
			}
			return order;
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}
	
	@Override
	public Set<Order> getOrdersByUserId(int id) throws ServiceException {
		if (!Validator.validateInt(id)) {
			throw new ServiceException(ExceptionMessages.CORRUPTED_INPUT_PARAMETERS);
		}
		
		Set<Order> set = new LinkedHashSet<Order>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IOrderDAO orderDAO = daoFactory.getOrderDAO();
			set = orderDAO.getOrdersByUserId(id);
			
			if (set.isEmpty()) {
				throw new GetOrderServiceException(ExceptionMessages.NO_USER_ORDERS_YET);
			}
			
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		return set;
	}

	@Override
	public Set<Order> getOrdersByFilmId(int id) throws ServiceException {
		if (!Validator.validateInt(id)) {
			throw new ServiceException(ExceptionMessages.CORRUPTED_INPUT_PARAMETERS);
		}
		
		Set<Order> set = new LinkedHashSet<Order>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IOrderDAO orderDAO = daoFactory.getOrderDAO();
			set = orderDAO.getOrdersByFilmId(id);
			
			if (set.isEmpty()) {
				throw new GetOrderServiceException(ExceptionMessages.NO_FILM_ORDERS);
			}
			
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		return set;
	}

	@Override
	public Set<Order> getAllOrders() throws ServiceException {
		Set<Order> set = new LinkedHashSet<Order>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IOrderDAO orderDAO = daoFactory.getOrderDAO();
			set = orderDAO.getAllOrders();
			
			if (set.isEmpty()) {
				throw new GetOrderServiceException(ExceptionMessages.NO_ORDERS_IN_DB);
			}
			
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		return set;
	}
}
