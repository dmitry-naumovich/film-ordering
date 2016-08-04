package by.epam.naumovich.film_ordering.service.exception;

public class GetNewsServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GetNewsServiceException(String msg, Exception e) {
		super(msg, e);
	}

	public GetNewsServiceException(String msg) {
		super(msg);
	}

}
