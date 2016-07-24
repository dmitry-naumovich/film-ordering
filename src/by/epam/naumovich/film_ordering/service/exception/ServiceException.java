package by.epam.naumovich.film_ordering.service.exception;

public class ServiceException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ServiceException(String msg, Exception e) {
		super(msg, e);
	}

	public ServiceException(String msg) {
		super(msg);
	}
}
