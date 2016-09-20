package by.epam.naumovich.film_ordering.service;

import java.util.Set;

import by.epam.naumovich.film_ordering.bean.Order;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public interface IOrderService {

	int addOrder(int filmID, int userID, String price, String discount, String payment) throws ServiceException;
	void deleteOrder(int orderNum) throws ServiceException;
	
	Order getOrderByOrderNum(int orderNum) throws ServiceException;
	Order getOrderByUserAndFilmId(int userID, int filmID) throws ServiceException;
	Set<Order> getOrdersByUserId(int id) throws ServiceException;
	Set<Order> getOrdersByFilmId(int id) throws ServiceException;
	Set<Order> getAllOrders() throws ServiceException;
}
