package by.epam.naumovich.film_ordering.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.film_ordering.bean.Review;
import by.epam.naumovich.film_ordering.dao.DAOFactory;
import by.epam.naumovich.film_ordering.dao.IReviewDAO;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;
import by.epam.naumovich.film_ordering.service.IReviewService;
import by.epam.naumovich.film_ordering.service.exception.GetReviewsServiceException;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class ReviewServiceImpl implements IReviewService {

	private static final String MYSQL = "mysql";
	
	@Override
	public void addReview(Review review) throws ServiceException {
		// TODO Auto-generated method stub

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
				throw new GetReviewsServiceException("No reviews in database");
			}
		} catch (DAOException e) {
			throw new ServiceException("Error in data source!");
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
				throw new GetReviewsServiceException("This user has not written any reviews yet");
			}
		} catch (DAOException e) {
			throw new ServiceException("Error in data source!");
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
				throw new GetReviewsServiceException("No reviews for this film");
			}
		} catch (DAOException e) {
			throw new ServiceException("Error in data source!");
		}
		
		return list;
	}

}
