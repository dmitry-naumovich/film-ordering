package by.epam.naumovich.film_ordering.dao;

import java.util.Set;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;

/**
 * Defines methods for implementing in the DAO layer for the Film entity.
 *
 * @author Dmitry Naumovich
 * @version 1.0
 */
public interface IFilmDAO {

	/**
	 * Adds a new film to the data source
	 * 
	 * @param film new film entity
	 * @return ID of a newly added film or 0 if it was not added
	 * @throws DAOException
	 */
	int addFilm(Film film) throws DAOException;
	
	/**
	 * Deletes a film from the data source
	 * 
	 * @param id ID of a film which will be deleted
	 * @throws DAOException
	 */
	void deleteFilm(int id) throws DAOException;
	
	/**
	 * Edits a film in the data source
	 * 
	 * @param id ID of a film which will be edited
	 * @param editedFilm film entity with edited fields
	 * @throws DAOException
	 */
	void editFilm(int id, Film editedFilm) throws DAOException;
	
	/**
	 * Searches for a film in the data source by its ID
	 * 
	 * @param id ID of a film
	 * @return found film or null if it was not found
	 * @throws DAOException
	 */
	Film getFilmByID(int id) throws DAOException;
	
	/**
	 * Returns film name by its ID
	 * 
	 * @param id ID of a film
	 * @return the name of the film or null if it was not found
	 * @throws DAOException
	 */
	String getFilmNameByID(int id) throws DAOException;
	
	/**
	 * Returns twelve last added to the data source films
	 * 
	 * @return a set of films
	 * @throws DAOException
	 */
	Set<Film> getTwelveLastAddedFilms() throws DAOException;
	
	/**
	 * Returns all films that a present in the data source
	 * 
	 * @return a set of all films
	 * @throws DAOException
	 */
	Set<Film> getAllFilms() throws DAOException;
	
	/**
	 * Searches for films in the data source by name 
	 * 
	 * @param name film name
	 * @return a set of found films
	 * @throws DAOException
	 */
	Set<Film> getFilmsByName(String name) throws DAOException;
	
	/**
	 * Searches for films in the data source by year
	 * 
	 * @param year film year
	 * @return a set of found films
	 * @throws DAOException
	 */
	Set<Film> getFilmsByYear(int year) throws DAOException;
	
	/**
	 * Searches for films in the data source by genre
	 * 
	 * @param genre film genre
	 * @return a set of found films
	 * @throws DAOException
	 */
	Set<Film> getFilmsByGenre(String genre) throws DAOException;
	
	/**
	 * Searches for films in the data source by country
	 * 
	 * @param country film country
	 * @return a set of found films
	 * @throws DAOException
	 */
	Set<Film> getFilmsByCountry(String country) throws DAOException;
	
	/**
	 * Searches for films in the data source by year range
	 * 
	 * @param yearFrom left border of the range (including)
	 * @param yearTo right border of the range (including)
	 * @return a set of found films
	 * @throws DAOException
	 */
	Set<Film> getFilmsBetweenYears(int yearFrom, int yearTo) throws DAOException;
	
	/**
	 * Searches for films in the data source by name and year
	 * 
	 * @param name film name
	 * @param year film year
	 * @return a set of found films
	 * @throws DAOException
	 */
	Set<Film> getFilmsByNameYear(String name, int year) throws DAOException;
	
	/**
	 * Searches for films in the data source by name and genre
	 * 
	 * @param name film name
	 * @param genre film genre
	 * @return a set of found films
	 * @throws DAOException
	 */
	Set<Film> getFilmsByNameGenre(String name, String genre) throws DAOException;
	
	/**
	 * Searches for films in the data source by year and genre
	 * 
	 * @param year film year
	 * @param genre film genre
	 * @return a set of found films
	 * @throws DAOException
	 */
	Set<Film> getFilmsByYearGenre(int year, String genre) throws DAOException;
	
	/**
	 * Searches for films in the data source by name, year and genre
	 * 
	 * @param name film name
	 * @param year film year
	 * @param genre film genre
	 * @return a set of found films
	 * @throws DAOException
	 */
	Set<Film> getFilmsByNameYearGenre(String name, int year, String genre) throws DAOException;
	
	/**
	 * Returns all film genres that are present in the data source
	 * 
	 * @return an array of available genres
	 * @throws DAOException
	 */
	String[] getAvailableGenres() throws DAOException;
	
	/**
	 * Returns all film countries that are present in the data source
	 * 
	 * @return an array of available countries
	 * @throws DAOException
	 */
	String[] getAvailableCountries() throws DAOException;
}
