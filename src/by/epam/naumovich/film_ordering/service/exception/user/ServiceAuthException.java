package by.epam.naumovich.film_ordering.service.exception;

public class ServiceAuthException extends ServiceException {
	
	private static final long serialVersionUID = 1L;

	public ServiceAuthException(String message) {
		super(message);
	}

	public ServiceAuthException(String message, Exception e) {
		super(message, e);
	}
}
