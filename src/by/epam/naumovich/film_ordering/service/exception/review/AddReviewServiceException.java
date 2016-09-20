package by.epam.naumovich.film_ordering.service.exception.review;

import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class SendReviewServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SendReviewServiceException(String msg, Exception e) {
		super(msg, e);
		// TODO Auto-generated constructor stub
	}

	public SendReviewServiceException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
