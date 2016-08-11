package by.epam.naumovich.film_ordering.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import by.epam.naumovich.film_ordering.bean.News;
import by.epam.naumovich.film_ordering.dao.DAOFactory;
import by.epam.naumovich.film_ordering.dao.INewsDAO;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;
import by.epam.naumovich.film_ordering.service.INewsService;
import by.epam.naumovich.film_ordering.service.exception.GetNewsServiceException;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class NewsServiceImpl implements INewsService {

	private static final String MYSQL = "mysql";
	
	@Override
	public void addNews(News news) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteNews(News news) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<News> getAllNews() throws ServiceException {
		List<News> list = new ArrayList<News>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			INewsDAO newsDAO = daoFactory.getNewsDAO();
			list = newsDAO.getAllNews();
			
			if (list.isEmpty()) {
				throw new GetNewsServiceException("No news in the database");
			}
			
		} catch (DAOException e) {
			throw new ServiceException("Error in data source!");
		}
		
		return list;
	}

	@Override
	public List<News> getNewsByYear(int year) throws ServiceException {
		List<News> list = new ArrayList<News>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			INewsDAO newsDAO = daoFactory.getNewsDAO();
			list = newsDAO.getNewsByYear(year);
			
			if (list.isEmpty()) {
				throw new GetNewsServiceException("No news in the database");
			}
			
		} catch (DAOException e) {
			throw new ServiceException("Error in data source!");
		}
		
		return list;
	}

	@Override
	public List<News> getNewsByMonth(int month, int year) throws ServiceException {
		List<News> list = new ArrayList<News>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			INewsDAO newsDAO = daoFactory.getNewsDAO();
			list = newsDAO.getNewsByMonthAndYear(month, year);
			
			if (list.isEmpty()) {
				throw new GetNewsServiceException("No news in the database");
			}
			
		} catch (DAOException e) {
			throw new ServiceException("Error in data source!");
		}
		
		return list;
	}

	@Override
	public List<News> getFourLastNews() throws ServiceException {
		List<News> list = new ArrayList<News>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			INewsDAO newsDAO = daoFactory.getNewsDAO();
			list = newsDAO.getAllNews();
			
			if (list.isEmpty()) {
				throw new GetNewsServiceException("No news in the database");
			}
			
			Collections.reverse(list);
			
		} catch (DAOException e) {
			throw new ServiceException("Error in data source!");
		}
		
		return list.subList(0, 4);
	}

	@Override
	public News getNewsById(int id) throws ServiceException {
		News news = null;
		try {
			INewsDAO newsDAO = DAOFactory.getDAOFactory(MYSQL).getNewsDAO();
			news = newsDAO.getNewsById(id);
			
			if (news == null) {
				throw new GetNewsServiceException("News was not found. Sorry!");
			}
		} catch (DAOException e) {
			throw new ServiceException("Error in data source!");
		}
		
		return news;
	}

}
