package by.epam.naumovich.film_ordering.service.exception;

public class GetFilmsServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GetFilmsServiceException(String msg) {
		super(msg);
	}
	
	public GetFilmsServiceException(String msg, Exception e) {
		super(msg, e);
	}

}
