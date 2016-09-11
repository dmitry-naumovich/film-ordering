package by.epam.naumovich.film_ordering.service;

import java.util.List;

import by.epam.naumovich.film_ordering.bean.Order;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public interface IOrderService {

	int addOrder(int filmID, int userID, String price, String discount, String payment) throws ServiceException;
	void deleteOrder(int orderID) throws ServiceException;
	
	Order getOrderByOrderNum(int orderNum) throws ServiceException;
	List<Order> getOrdersByUserId(int id) throws ServiceException;
	List<Order> getOrdersByFilmId(int id) throws ServiceException;
	List<Order> getAllOrders() throws ServiceException;
}
