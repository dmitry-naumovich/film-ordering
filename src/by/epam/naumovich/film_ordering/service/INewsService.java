package by.epam.naumovich.film_ordering.service;

import java.util.Set;

import by.epam.naumovich.film_ordering.bean.News;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public interface INewsService {

	int addNews(String title, String text) throws ServiceException;
	void deleteNews(int id) throws ServiceException;
	void editNews(int id, String title, String text) throws ServiceException;
	
	News getNewsById(int id) throws ServiceException;
	Set<News> getAllNews() throws ServiceException;
	Set<News> getFourLastNews() throws ServiceException;
	Set<News> getNewsByYear(int year) throws ServiceException;
	Set<News> getNewsByMonth(int month, int year) throws ServiceException;
}
