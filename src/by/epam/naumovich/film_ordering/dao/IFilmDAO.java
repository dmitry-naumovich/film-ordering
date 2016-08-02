package by.epam.naumovich.film_ordering.dao;

import java.util.List;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;

public interface IFilmDAO {

	void addNewFilm(Film film) throws DAOException;
	void deleteFilm(Film film) throws DAOException;
	
	Film getFilmByID(int id) throws DAOException;
	
	List<Film> getTwelveLastAddedFilms() throws DAOException;
	List<Film> getAllFilms() throws DAOException;
	
	List<Film> getFilmsByName(String name) throws DAOException;
	List<Film> getFilmsByYear(int year) throws DAOException;
	List<Film> getFilmsByGenre(String genre) throws DAOException;
	
	List<Film> getFilmsByNameYear(String name, int year) throws DAOException;
	List<Film> getFilmsByNameGenre(String name, String genre) throws DAOException;
	List<Film> getFilmsByYearGenre(int year, String genre) throws DAOException;
	
	List<Film> getFilmsByNameYearGenre(String name, int year, String genre) throws DAOException;
	
}
