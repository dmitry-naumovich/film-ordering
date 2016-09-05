package by.epam.naumovich.film_ordering.service;

import java.util.Set;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public interface IFilmService {

	void addNewFilm(Film film) throws ServiceException;
	void deleteFilm(int id) throws ServiceException;
	
	Film getFilmByID(int id) throws ServiceException;
	
	Set<Film> getTwelveLastAddedFilms() throws ServiceException;
	Set<Film> getAllFilms() throws ServiceException;
	
	Set<Film> searchByName(String text) throws ServiceException;
}
