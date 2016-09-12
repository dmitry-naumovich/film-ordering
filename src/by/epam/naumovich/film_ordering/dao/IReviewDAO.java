package by.epam.naumovich.film_ordering.dao;

import java.util.List;

import by.epam.naumovich.film_ordering.bean.Review;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;

public interface IReviewDAO {

	void addReview(Review review) throws DAOException;
	void deleteReview(int userID, int filmID) throws DAOException;
	List<Review> getAllReviews() throws DAOException;
	
	List<Review> getReviewsByUserId(int id) throws DAOException;
	List<Review> getReviewsByFilmId(int id) throws DAOException;
	
	Review getReviewByUserAndFilmId(int userID, int filmID) throws DAOException;
}
