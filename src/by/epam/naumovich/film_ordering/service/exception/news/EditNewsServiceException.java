package by.epam.naumovich.film_ordering.service.exception.news;

import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class EditNewsServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EditNewsServiceException(String msg, Exception e) {
		super(msg, e);
		// TODO Auto-generated constructor stub
	}

	public EditNewsServiceException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
