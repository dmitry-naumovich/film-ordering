package by.epam.naumovich.film_ordering.service.exception.news;

import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class AddNewsServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddNewsServiceException(String msg, Exception e) {
		super(msg, e);
		// TODO Auto-generated constructor stub
	}

	public AddNewsServiceException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
