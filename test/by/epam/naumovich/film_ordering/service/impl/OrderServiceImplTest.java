package by.epam.naumovich.film_ordering.service.impl;

import org.junit.Test;

import by.epam.naumovich.film_ordering.service.IOrderService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.order.AddOrderServiceException;
import by.epam.naumovich.film_ordering.service.exception.order.GetOrderServiceException;

/**
 * Tests service layer methods overridden in OrderServiceImpl class in a way of passing invalid parameters into service methods
 * and expecting exceptions on the output with the help of JUnit 4 framework.
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 */
public class OrderServiceImplTest {
	
	/**
	 * Tries to add new order with invalid film ID value and expects for the exception. 
	 * 
	 * @throws ServiceException
	 */
	@Test(expected=AddOrderServiceException.class)
	public void addOrderWithInvalidFilmID() throws ServiceException {
		IOrderService service = ServiceFactory.getInstance().getOrderService();
		service.addOrder(0, 1, "10", "0", "10");
	}
	
	/**
	 * Tries to add new order with invalid film ID value and expects for the exception. 
	 * 
	 * @throws ServiceException
	 */
	@Test(expected=AddOrderServiceException.class)
	public void addOrderWithInvalidPayment() throws ServiceException {
		IOrderService service = ServiceFactory.getInstance().getOrderService();
		service.addOrder(1, 1, "10", "0", "sdf10");
	}
	
	/**
	 * Tries to delete the order by invalid orderNum value and expects for the exception. 
	 * 
	 * @throws ServiceException
	 */
	@Test(expected=ServiceException.class)
	public void deleteOrder() throws ServiceException {
		IOrderService service = ServiceFactory.getInstance().getOrderService();
		service.deleteOrder(0);
	}
	
	/**
	 * Tries to get the order by invalid orderNum value and expects for the exception. 
	 * 
	 * @throws ServiceException
	 */
	@Test(expected=GetOrderServiceException.class)
	public void getOrderByOrderNum() throws ServiceException {
		IOrderService service = ServiceFactory.getInstance().getOrderService();
		service.getOrderByOrderNum(-1);
	}
	
	/**
	 * Tries to get the order by invalid user and film ID values and expects for the exception. 
	 * 
	 * @throws ServiceException
	 */
	@Test(expected=ServiceException.class)
	public void getOrderByUserAndFilmId() throws ServiceException {
		IOrderService service = ServiceFactory.getInstance().getOrderService();
		service.getOrderByUserAndFilmId(-1, 0);
	}
	
	/**
	 * Tries to get orders by invalid user ID values and expects for the exception. 
	 * 
	 * @throws ServiceException
	 */
	@Test(expected=ServiceException.class)
	public void getOrdersByUserId() throws ServiceException {
		IOrderService service = ServiceFactory.getInstance().getOrderService();
		service.getOrdersByUserId(0);
	}
	
	/**
	 * Tries to get orders by invalid film ID values and expects for the exception. 
	 * 
	 * @throws ServiceException
	 */
	@Test(expected=ServiceException.class)
	public void getOrdersByFilmId() throws ServiceException {
		IOrderService service = ServiceFactory.getInstance().getOrderService();
		service.getOrdersByFilmId(0);
	}
	
	/**
	 * Tries to get orders from the DAO layer by the invalid page number.
	 * 
	 * @throws ServiceException
	 */
	@Test(expected=GetOrderServiceException.class)
	public void getAllOrdersPart() throws ServiceException {
		IOrderService service = ServiceFactory.getInstance().getOrderService();
		service.getAllOrdersPart(0);
	}

}
