package by.epam.naumovich.film_ordering.service;

import java.util.List;

import by.epam.naumovich.film_ordering.bean.Review;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public interface IReviewService {
	
	void addReview(int userID, int filmID, String mark, String type, String text) throws ServiceException;
	void deleteReview(int userID, int filmID) throws ServiceException;
	List<Review> getAllReviews() throws ServiceException;
	
	List<Review> getReviewsByUserId(int id) throws ServiceException;
	List<Review> getReviewsByFilmId(int id) throws ServiceException;
	
	Review getReviewByUserAndFilmId(int userID, int filmID)  throws ServiceException;
}
