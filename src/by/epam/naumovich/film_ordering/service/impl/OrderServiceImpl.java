package by.epam.naumovich.film_ordering.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.film_ordering.bean.Order;
import by.epam.naumovich.film_ordering.dao.DAOFactory;
import by.epam.naumovich.film_ordering.dao.IOrderDAO;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;
import by.epam.naumovich.film_ordering.service.IOrderService;
import by.epam.naumovich.film_ordering.service.exception.GetOrdersServiceException;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class OrderServiceImpl implements IOrderService {

	private static final String MYSQL = "mysql";
	
	@Override
	public void addOrder(Order order) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteOrder(Order order) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Order> getOrdersByUserId(int id) throws ServiceException {
		List<Order> list = new ArrayList<Order>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IOrderDAO orderDAO = daoFactory.getOrderDAO();
			list = orderDAO.getOrdersByUserId(id);
			
			if (list.isEmpty()) {
				throw new GetOrdersServiceException("This user has not ordered anything yet");
			}
			
		} catch (DAOException e) {
			throw new ServiceException("Error in data source!");
		}
		
		return list;
	}

	@Override
	public List<Order> getOrdersByFilmId(int id) throws ServiceException {
		List<Order> list = new ArrayList<Order>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IOrderDAO orderDAO = daoFactory.getOrderDAO();
			list = orderDAO.getOrdersByFilmId(id);
			
			if (list.isEmpty()) {
				throw new GetOrdersServiceException("This movie has not been ordered yet");
			}
			
		} catch (DAOException e) {
			throw new ServiceException("Error in data source!");
		}
		
		return list;
	}

	@Override
	public List<Order> getAllOrders() throws ServiceException {
		List<Order> list = new ArrayList<Order>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IOrderDAO orderDAO = daoFactory.getOrderDAO();
			list = orderDAO.getAllOrders();
			
			if (list.isEmpty()) {
				throw new GetOrdersServiceException("No orders in the database");
			}
			
		} catch (DAOException e) {
			throw new ServiceException("Error in data source!");
		}
		
		return list;
	}

}
