package by.epam.naumovich.film_ordering.service.exception.order;

import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class GetOrdersServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GetOrdersServiceException(String msg, Exception e) {
		super(msg, e);
	}

	public GetOrdersServiceException(String msg) {
		super(msg);
	}

}
