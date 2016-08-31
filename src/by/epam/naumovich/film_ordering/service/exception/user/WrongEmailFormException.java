package by.epam.naumovich.film_ordering.service.exception;

public class WrongEmailFormException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongEmailFormException(String msg, Exception e) {
		super(msg, e);
		// TODO Auto-generated constructor stub
	}

	public WrongEmailFormException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
