package by.epam.naumovich.film_ordering.dao;

import java.util.Set;

import by.epam.naumovich.film_ordering.bean.Review;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;

/**
 * Defines methods for implementing in the DAO layer for the Review entity.
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 */
public interface IReviewDAO {

	/**
	 * Adds new review to the data source
	 * 
	 * @param review new review entity
	 * @throws DAOException
	 */
	void addReview(Review review) throws DAOException;
	
	/**
	 * Deletes review from the data source by user and film IDs
	 * 
	 * @param userID user ID
	 * @param filmID film ID
	 * @throws DAOException
	 */
	void deleteReview(int userID, int filmID) throws DAOException;
	
	/**
	 * Returns all reviews that are present in the data source
	 * 
	 * @return a set of reviews
	 * @throws DAOException
	 */
	Set<Review> getAllReviews() throws DAOException;

	/**
	 * Returns a necessary part of all reviews from the data source
	 * 
	 * @param start start index of necessary reviews part
	 * @param amount amount of reviews to be returned
	 * @return a part of the set of all reviews
	 * @throws DAOException
	 */
	Set<Review> getAllReviewsPart(int start, int amount) throws DAOException;
	
	/**
	 * Searches for reviews in the data source by user ID
	 * 
	 * @param id user ID
	 * @return a set of found reviews
	 * @throws DAOException
	 */
	Set<Review> getReviewsByUserId(int id) throws DAOException;
	
	/**
	 * Searches for reviews in the data source by film ID
	 * 
	 * @param id film ID
	 * @return a set of found reviews
	 * @throws DAOException
	 */
	Set<Review> getReviewsByFilmId(int id) throws DAOException;
	
	/**
	 * Searches for review in the data source by user and film IDs
	 * 
	 * @param userID user ID
	 * @param filmID film ID
	 * @return found review or null if it was not found
	 * @throws DAOException
	 */
	Review getReviewByUserAndFilmId(int userID, int filmID) throws DAOException;
	
	/**
	 * Counts the number of all reviews in the data source
	 * 
	 * @return total review amount
	 * @throws DAOException
	 */
	int getNumberOfReviews() throws DAOException;
}
