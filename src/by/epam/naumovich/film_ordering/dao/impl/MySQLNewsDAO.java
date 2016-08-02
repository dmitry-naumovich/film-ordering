package by.epam.naumovich.film_ordering.dao.impl;

import java.util.List;

import by.epam.naumovich.film_ordering.bean.News;
import by.epam.naumovich.film_ordering.dao.INewsDAO;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;

public class MySQLNewsDAO implements INewsDAO {

	public static final String INSERT_NEW_NEWS = "INSERT INTO News (n_date, n_title, n_text) VALUES (?, ?, ?)";
	public static final String DELETE_NEWS = "DELETE FROM News WHERE n_id = ?";
	public static final String SELECT_ALL_NEWS = "SELECT * FROM News";
	public static final String SELECT_NEWS_BY_YEAR = "SELECT * FROM News WHERE YEAR(n_date) = ?";
	public static final String SELECT_NEWS_BY_MONTH = "SELECT * FROM News WHERE MONTH(n_date) = ?";
	
	private static final MySQLNewsDAO instance = new MySQLNewsDAO();
	
	public static MySQLNewsDAO getInstance() {
		return instance;
	}
	
	@Override
	public void addNews(News news) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteNews(News news) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<News> getAllNews() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<News> getNewsByYear(int year) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<News> getNewsByMonth(int month) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
