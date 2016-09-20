package by.epam.naumovich.film_ordering.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;


import by.epam.naumovich.film_ordering.bean.News;
import by.epam.naumovich.film_ordering.dao.DAOFactory;
import by.epam.naumovich.film_ordering.dao.INewsDAO;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;
import by.epam.naumovich.film_ordering.service.INewsService;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.news.AddNewsServiceException;
import by.epam.naumovich.film_ordering.service.exception.news.EditNewsServiceException;
import by.epam.naumovich.film_ordering.service.exception.news.GetNewsServiceException;
import by.epam.naumovich.film_ordering.service.util.ExceptionMessages;
import by.epam.naumovich.film_ordering.service.util.Validator;

/**
 * INewsService interface implementation that works with INewsDAO implementation
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 */
public class NewsServiceImpl implements INewsService {

	private static final String MYSQL = "mysql";
	
	@Override
	public int addNews(String title, String text) throws ServiceException {
		if (!Validator.validateStrings(title, text)) {
			throw new AddNewsServiceException(ExceptionMessages.INVALID_NEWS_TITLE_OR_TEXT);
		}
		News news = new News();
		news.setTitle(title);
		news.setText(text);
		news.setDate(Date.valueOf(LocalDate.now()));
		news.setTime(Time.valueOf(LocalTime.now()));
		
		int newsID = 0;
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			INewsDAO newsDAO = daoFactory.getNewsDAO();
			newsID = newsDAO.addNews(news);
			if (newsID == 0) {
				throw new AddNewsServiceException(ExceptionMessages.NEWS_NOT_ADDED);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		return newsID;
	}

	@Override
	public void deleteNews(int id) throws ServiceException {
		if (!Validator.validateInt(id)) {
			throw new ServiceException(ExceptionMessages.CORRUPTED_NEWS_ID);
		}
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			INewsDAO newsDAO = daoFactory.getNewsDAO();
			newsDAO.deleteNews(id);
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}


	@Override
	public void editNews(int id, String title, String text) throws ServiceException {
		if (!Validator.validateInt(id)) {
			throw new ServiceException(ExceptionMessages.CORRUPTED_NEWS_ID);
		}
		else if (!Validator.validateStrings(title, text)) {
			throw new EditNewsServiceException(ExceptionMessages.INVALID_NEWS_TITLE_OR_TEXT);
		}
		News news = new News();
		news.setTitle(title);
		news.setText(text);
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			INewsDAO newsDAO = daoFactory.getNewsDAO();
			newsDAO.editNews(id, news);
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}
	
	@Override
	public Set<News> getAllNews() throws ServiceException {
		Set<News> set = new LinkedHashSet<News>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			INewsDAO newsDAO = daoFactory.getNewsDAO();
			set = newsDAO.getAllNews();
			
			if (set.isEmpty()) {
				throw new GetNewsServiceException(ExceptionMessages.NO_NEWS_IN_DB);
			}
			
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		return set;
	}

	@Override
	public Set<News> getNewsByYear(int year) throws ServiceException {
		Set<News> set = new LinkedHashSet<News>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			INewsDAO newsDAO = daoFactory.getNewsDAO();
			set = newsDAO.getNewsByYear(year);
			
			if (set.isEmpty()) {
				throw new GetNewsServiceException(String.format(ExceptionMessages.NO_NEWS_WITHIN_YEAR, year));
			}
			
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		return set;
	}

	@Override
	public Set<News> getNewsByMonth(int month, int year) throws ServiceException {
		Set<News> set = new LinkedHashSet<News>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			INewsDAO newsDAO = daoFactory.getNewsDAO();
			set = newsDAO.getNewsByMonthAndYear(month, year);
			
			if (set.isEmpty()) {
				throw new GetNewsServiceException(String.format(ExceptionMessages.NO_NEWS_WITHIN_MONTH, month, year));
			}
			
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		return set;
	}

	@Override
	public Set<News> getFourLastNews() throws ServiceException {
		Set<News> set = new LinkedHashSet<News>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			INewsDAO newsDAO = daoFactory.getNewsDAO();
			set = newsDAO.getAllNews();
			
			if (set.isEmpty()) {
				throw new GetNewsServiceException(ExceptionMessages.NO_NEWS_IN_DB);
			}
			List<News> list = new ArrayList<News>(set);
			Collections.reverse(list);
			set = new LinkedHashSet<News>(list.subList(0, 4));
			
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		return set;
	}

	@Override
	public News getNewsById(int id) throws ServiceException {
		News news = null;
		try {
			INewsDAO newsDAO = DAOFactory.getDAOFactory(MYSQL).getNewsDAO();
			news = newsDAO.getNewsById(id);
			
			if (news == null) {
				throw new GetNewsServiceException(ExceptionMessages.NEWS_NOT_PRESENT);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		return news;
	}
}
