package by.epam.naumovich.film_ordering.service;

import java.util.Set;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public interface IFilmService {

	int addNewFilm(String name, String year, String director, String cast, String country, String composer, 
			String genre, String length, String price, String description) throws ServiceException;
	
	void deleteFilm(int id) throws ServiceException; 
	
	Film getFilmByID(int id) throws ServiceException;
	String getFilmNameByID(int id) throws ServiceException;
	
	Set<Film> getTwelveLastAddedFilms() throws ServiceException;
	Set<Film> getAllFilms() throws ServiceException;
	
	Set<Film> searchByName(String text) throws ServiceException;
	Set<Film> searchWidened(String name, String yearFrom, String yearTo, String genre) throws ServiceException;
}
