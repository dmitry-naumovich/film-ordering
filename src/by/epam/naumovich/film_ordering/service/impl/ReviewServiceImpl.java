package by.epam.naumovich.film_ordering.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.film_ordering.bean.Review;
import by.epam.naumovich.film_ordering.dao.DAOFactory;
import by.epam.naumovich.film_ordering.dao.IReviewDAO;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;
import by.epam.naumovich.film_ordering.service.IReviewService;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.review.GetReviewsServiceException;
import by.epam.naumovich.film_ordering.service.exception.review.SendReviewServiceException;
import by.epam.naumovich.film_ordering.service.util.ExceptionMessages;
import by.epam.naumovich.film_ordering.service.util.Validator;

public class ReviewServiceImpl implements IReviewService {

	private static final String MYSQL = "mysql";
	public static final String POSITIVE_REVIEW = "ps";
	public static final String NEGATIVE_REVIEW = "ng";
	public static final String NEUTRAL_REVIEW = "nt";
	public static final int REVIEW_MIN_LENGTH = 50;
	
	@Override
	public void addReview(int userID, int filmID, String mark, String type, String text) throws ServiceException {
		if (!Validator.validateInt(userID) || !Validator.validateInt(filmID) || !Validator.validateStrings(mark, type, text)) {
			throw new SendReviewServiceException(ExceptionMessages.CORRUPTED_INPUT_PARAMETERS);
		}
		
		int rMark = Integer.parseInt(mark);
		if (rMark < 0 || rMark > 5) {
			throw new SendReviewServiceException(ExceptionMessages.REVIEW_MARK_RANGE);
		}
		if (!type.equals(POSITIVE_REVIEW) && !type.equals(NEGATIVE_REVIEW) && !type.equals(NEUTRAL_REVIEW)) {
			throw new SendReviewServiceException(ExceptionMessages.INVALID_REVIEW_TYPE);
		}
		if (text.length() < REVIEW_MIN_LENGTH) {
			throw new SendReviewServiceException(ExceptionMessages.REVIEW_TEXT_LENGTH);
		}
		
		Review review = new Review();
		review.setAuthor(userID);
		review.setFilmId(filmID);
		review.setMark(rMark);
		review.setType(type);
		review.setDate(Date.valueOf(LocalDate.now()));
		review.setTime(Time.valueOf(LocalTime.now()));
		review.setText(text);
		
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IReviewDAO reviewDAO = daoFactory.getReviewDAO();
			reviewDAO.addReview(review);
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
	}
	
	@Override
	public void deleteReview(Review review) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Review> getAllReviews() throws ServiceException {
		List<Review> list = new ArrayList<Review>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IReviewDAO reviewDAO = daoFactory.getReviewDAO();
			list = reviewDAO.getAllReviews();
			
			if (list.isEmpty()) {
				throw new GetReviewsServiceException(ExceptionMessages.NO_REVIEWS_IN_DB);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		return list;
	}

	@Override
	public List<Review> getReviewsByUserId(int id) throws ServiceException {
		List<Review> list = new ArrayList<Review>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IReviewDAO reviewDAO = daoFactory.getReviewDAO();
			list = reviewDAO.getReviewsByUserId(id);
			
			if (list.isEmpty()) {
				throw new GetReviewsServiceException(ExceptionMessages.NO_USER_REVIEWS_YET);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		return list;
	}

	@Override
	public List<Review> getReviewsByFilmId(int id) throws ServiceException {
		List<Review> list = new ArrayList<Review>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IReviewDAO reviewDAO = daoFactory.getReviewDAO();
			list = reviewDAO.getReviewsByFilmId(id);
			
			if (list.isEmpty()) {
				throw new GetReviewsServiceException(ExceptionMessages.NO_FILM_REVIEWS);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		return list;
	}

	@Override
	public Review getReviewByUserAndFilmId(int userID, int filmID) throws ServiceException {
		Review review = null;
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IReviewDAO reviewDAO = daoFactory.getReviewDAO();
			review = reviewDAO.getReviewByUserAndFilmId(userID, filmID);
			
			if (review == null) {
				throw new GetReviewsServiceException(ExceptionMessages.NO_FILM_USER_REVIEW);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		return review;
	}

}
