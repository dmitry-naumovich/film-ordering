package by.epam.naumovich.film_ordering.dao;

import java.util.Set;

import by.epam.naumovich.film_ordering.bean.Review;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;

public interface IReviewDAO {

	void addReview(Review review) throws DAOException;
	void deleteReview(int userID, int filmID) throws DAOException;
	Set<Review> getAllReviews() throws DAOException;
	
	Set<Review> getReviewsByUserId(int id) throws DAOException;
	Set<Review> getReviewsByFilmId(int id) throws DAOException;
	
	Review getReviewByUserAndFilmId(int userID, int filmID) throws DAOException;
}
