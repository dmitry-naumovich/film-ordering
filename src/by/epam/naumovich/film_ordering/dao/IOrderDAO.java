package by.epam.naumovich.film_ordering.dao;

import java.util.Set;

import by.epam.naumovich.film_ordering.bean.Order;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;

public interface IOrderDAO {

	int addOrder(Order order) throws DAOException;
	void deleteOrder(int orderNum) throws DAOException;
	
	Order getOrderByOrderNum(int orderNum) throws DAOException;

	Order getOrderByUserAndFilmId(int userID, int filmID) throws DAOException;
	Set<Order> getOrdersByUserId(int id) throws DAOException;
	Set<Order> getOrdersByFilmId(int id) throws DAOException;
	Set<Order> getAllOrders() throws DAOException;
	
}
