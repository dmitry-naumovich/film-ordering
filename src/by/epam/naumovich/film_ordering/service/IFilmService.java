package by.epam.naumovich.film_ordering.service;

import java.util.List;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public interface IFilmService {

	void addNewFilm(Film film);
	
	List<Film> getTwelveLastAddedFilms() throws ServiceException;
	List<Film> getAllFilms() throws ServiceException;
	
	
}
