package by.epam.naumovich.film_ordering.dao;

import java.util.Set;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;

public interface IFilmDAO {

	int addFilm(Film film) throws DAOException;
	void deleteFilm(int id) throws DAOException;
	
	Film getFilmByID(int id) throws DAOException;
	String getFilmNameByID(int id) throws DAOException;
	
	Set<Film> getTwelveLastAddedFilms() throws DAOException;
	Set<Film> getAllFilms() throws DAOException;
	
	Set<Film> getFilmsByName(String name) throws DAOException;
	Set<Film> getFilmsByYear(int year) throws DAOException;
	Set<Film> getFilmsByGenre(String genre) throws DAOException;
	
	Set<Film> getFilmsByNameYear(String name, int year) throws DAOException;
	Set<Film> getFilmsByNameGenre(String name, String genre) throws DAOException;
	Set<Film> getFilmsByYearGenre(int year, String genre) throws DAOException;
	
	Set<Film> getFilmsByNameYearGenre(String name, int year, String genre) throws DAOException;
	Set<Film> getFilmsBetweenYears(int yearFrom, int yearTo) throws DAOException;
}
