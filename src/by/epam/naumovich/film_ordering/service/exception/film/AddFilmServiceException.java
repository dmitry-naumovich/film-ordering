package by.epam.naumovich.film_ordering.service.exception.film;

import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class AddFilmServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddFilmServiceException(String msg, Exception e) {
		super(msg, e);
		// TODO Auto-generated constructor stub
	}

	public AddFilmServiceException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
