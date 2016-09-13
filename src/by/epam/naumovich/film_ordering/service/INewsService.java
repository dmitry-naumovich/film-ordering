package by.epam.naumovich.film_ordering.service;

import java.util.List;

import by.epam.naumovich.film_ordering.bean.News;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public interface INewsService {

	int addNews(String title, String text) throws ServiceException;
	void deleteNews(int id) throws ServiceException;
	void editNews(int id, String title, String text) throws ServiceException;
	
	News getNewsById(int id) throws ServiceException;
	List<News> getAllNews() throws ServiceException;
	List<News> getFourLastNews() throws ServiceException;
	List<News> getNewsByYear(int year) throws ServiceException;
	List<News> getNewsByMonth(int month, int year) throws ServiceException;
}
