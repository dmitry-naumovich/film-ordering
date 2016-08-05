package by.epam.naumovich.film_ordering.service;

import java.util.List;

import by.epam.naumovich.film_ordering.bean.News;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public interface INewsService {

	void addNews(News news) throws ServiceException;
	void deleteNews(News news) throws ServiceException;
	List<News> getAllNews() throws ServiceException;
	List<News> getNewsByYear(int year) throws ServiceException;
	List<News> getNewsByMonth(int month, int year) throws ServiceException;
}
