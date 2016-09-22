package by.epam.naumovich.film_ordering.service.impl;

import org.junit.Test;

import by.epam.naumovich.film_ordering.service.IReviewService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.review.AddReviewServiceException;

/**
 * Tests service layer methods overridden in ReviewServiceImpl class in a way of passing invalid parameters into service methods
 * and expecting exceptions on the output with the help of JUnit 4 framework.
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 */
public class ReviewServiceImplTest {
	
	/**
	 * Tries to add new review with invalid filmID and expects for the exception.
	 * 
	 * @throws ServiceException
	 */
	@Test(expected=AddReviewServiceException.class)
	public void addReviewWithInvalidUserID() throws ServiceException {
		IReviewService service = ServiceFactory.getInstance().getReviewService();
		service.addReview(0, 1, "1", "ng", "test review text");
	}
	
	/**
	 * Tries to add new review with invalid mark and expects for the exception.
	 * 
	 * @throws ServiceException
	 */
	@Test(expected=AddReviewServiceException.class)
	public void addReviewWithInvalidMark() throws ServiceException {
		IReviewService service = ServiceFactory.getInstance().getReviewService();
		service.addReview(0, 1, "-1", "ng", "test review text");
	}
	
	/**
	 * Tries to add new review with invalid filmID and expects for the exception.
	 * 
	 * @throws ServiceException
	 */
	@Test(expected=AddReviewServiceException.class)
	public void addReviewWithInvalidReviewText() throws ServiceException {
		IReviewService service = ServiceFactory.getInstance().getReviewService();
		service.addReview(0, 1, "1", "ng", null);
	}
	
	/**
	 * Tries to delete the review by invalid user and film ID values and expects for the exception.
	 * 
	 * @throws ServiceException
	 */
	@Test(expected=ServiceException.class)
	public void deleteReview() throws ServiceException {
		IReviewService service = ServiceFactory.getInstance().getReviewService();
		service.deleteReview(-1, -1);
	}

}
