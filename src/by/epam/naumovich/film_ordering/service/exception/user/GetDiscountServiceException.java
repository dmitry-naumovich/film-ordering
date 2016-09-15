package by.epam.naumovich.film_ordering.service.exception.user;

import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class GetDiscountServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GetDiscountServiceException(String msg, Exception e) {
		super(msg, e);
		// TODO Auto-generated constructor stub
	}

	public GetDiscountServiceException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
