package by.epam.naumovich.film_ordering.service.exception.order;

import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class AddOrderServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddOrderServiceException(String msg, Exception e) {
		super(msg, e);
		// TODO Auto-generated constructor stub
	}

	public AddOrderServiceException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
