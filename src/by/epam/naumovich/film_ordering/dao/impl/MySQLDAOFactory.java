package by.epam.naumovich.film_ordering.dao.impl;

import by.epam.naumovich.film_ordering.dao.DAOFactory;
import by.epam.naumovich.film_ordering.dao.IFilmDAO;
import by.epam.naumovich.film_ordering.dao.IUserDAO;

public class MySQLDAOFactory extends DAOFactory {

	private static final MySQLDAOFactory instance = new MySQLDAOFactory();
	
	private MySQLDAOFactory() { }

	public static DAOFactory getInstance() {
		return instance;
	}
	
	@Override
	public IUserDAO getUserDAO() {
		return MySQLUserDAO.getInstance();
	}

	@Override
	public IFilmDAO getFilmDAO() {
		return MySQLFilmDAO.getInstance();
	}

	/*@Override
	public IOrderDAO getOrderDAO() {
		return MySQLOrderDAO.getInstance();
	}

	@Override
	public IReviewDAO getReviewDAO() {
		return MySQLReviewDAO.getInstance();
	}

	@Override
	public INewsDAO getNewsDAO() {
		return MySQLNewsDAO.getInstance();
	}*/

}
