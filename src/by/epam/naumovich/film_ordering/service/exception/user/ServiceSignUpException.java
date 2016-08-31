package by.epam.naumovich.film_ordering.service.exception;

public class ServiceSignUpException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceSignUpException(String msg, Exception e) {
		super(msg, e);
	}

	public ServiceSignUpException(String msg) {
		super(msg);
	}

}
