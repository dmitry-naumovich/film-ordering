package by.epam.naumovich.film_ordering.dao;

import java.util.List;

import by.epam.naumovich.film_ordering.bean.Order;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;

public interface IOrderDAO {

	void addOrder(Order order) throws DAOException;
	void deleteOrder(Order order) throws DAOException;
	
	List<Order> getOrdersByUserId(int id) throws DAOException;
	List<Order> getOrdersByFilmId(int id) throws DAOException;
	List<Order> getAllOrders() throws DAOException;
	
}
