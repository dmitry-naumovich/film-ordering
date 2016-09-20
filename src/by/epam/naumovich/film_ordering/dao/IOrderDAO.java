package by.epam.naumovich.film_ordering.dao;

import java.util.Set;

import by.epam.naumovich.film_ordering.bean.Order;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;

/**
 * Defines methods for implementing in the DAO layer for the Order entity.
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 */
public interface IOrderDAO {

	/**
	 * Adds new order to the data source
	 * 
	 * @param order new order entity
	 * @return order number of the newly added order or 0 if it was not added
	 * @throws DAOException
	 */
	int addOrder(Order order) throws DAOException;
	
	/**
	 * Deletes order from the data source
	 * 
	 * @param orderNum order number of the order that will be deleted
	 * @throws DAOException
	 */
	void deleteOrder(int orderNum) throws DAOException;
	
	/**
	 * Searches for the order in the data source by its number
	 * 
	 * @param orderNum order number of the order
	 * @return found order or null if it was not found
	 * @throws DAOException
	 */
	Order getOrderByOrderNum(int orderNum) throws DAOException;

	/**
	 * Searches for the order in the data source by user and film IDs
	 * 
	 * @param userID user ID
	 * @param filmID film ID
	 * @return found order or null if it was not found
	 * @throws DAOException
	 */
	Order getOrderByUserAndFilmId(int userID, int filmID) throws DAOException;
	
	/**
	 * Searches for the orders in the data source by user ID
	 * 
	 * @param id user ID
	 * @return a set of found orders
	 * @throws DAOException
	 */
	Set<Order> getOrdersByUserId(int id) throws DAOException;
	
	/**
	 * Searches for the orders in the data source by film ID
	 * 
	 * @param id film ID
	 * @return a set of found orders
	 * @throws DAOException
	 */
	Set<Order> getOrdersByFilmId(int id) throws DAOException;
	
	/**
	 * Returns all orders that are present in the data source
	 * 
	 * @return a set of all orders
	 * @throws DAOException
	 */
	Set<Order> getAllOrders() throws DAOException;
	
}
