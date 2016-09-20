package by.epam.naumovich.film_ordering.service;

import java.util.Set;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

/**
 * Defines methods that receive parameters from Command implementations, verify them, construct necessary entities if needed 
 * and then pass them to the DAO layer, possibly getting some objects or primitive values back and passing them further back to the commands.
 * These methods operate with the Film entity.
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 */
public interface IFilmService {

	/**
	 * Constructs a new film entity based on input parameters received from the Controller layer, verifies them and either 
	 * passes to the DAO layer or throws an exception
	 * 
	 * @param name film name
	 * @param year film year
	 * @param director film director
	 * @param cast film cast
	 * @param countries film countries
	 * @param composer film composer
	 * @param genres film genres
	 * @param length film length
	 * @param price film price
	 * @param description film description
	 * @return ID of newly added film
	 * @throws ServiceException
	 */
	int addNewFilm(String name, String year, String director, String cast, String[] countries, String composer, 
			String[] genres, String length, String price, String description) throws ServiceException;
	
	/**
	 * Verifies the input parameter and either passes it to the DAO layer or throws an exception
	 * 
	 * @param id ID of the film to be deleted
	 * @throws ServiceException
	 */
	void deleteFilm(int id) throws ServiceException; 
	
	/**
	 * Constructs an edited film entity based on input parameters received from the Controller layer, verifies them
	 * and either passes to the DAO layer or throws an exception
	 * 
	 * @param id ID of the film to be edited
	 * @param name film name
	 * @param year film year
	 * @param director film director
	 * @param cast film cast
	 * @param countries film countries
	 * @param composer film composer
	 * @param genres film genres
	 * @param length film length
	 * @param price film price
	 * @param description film description
	 * @throws ServiceException
	 */
	void editFilm(int id, String name, String year, String director, String cast, String[] countries,
			String composer, String[] genres, String length, String price, String description) throws ServiceException;
	
	/**
	 * Verifies the input parameter and passes it to the DAO layer, receives the film entity, returns it back to the Controller layer
	 * or throws an exception if it equals null
	 * 
	 * @param id film ID
	 * @return found film entity
	 * @throws ServiceException
	 */
	Film getFilmByID(int id) throws ServiceException;
	
	/**
	 * Verifies the input parameter and passes it to the DAO layer, receives the String name of the film back and passes it to the Controller
	 * or throws an exception if it equals null
	 * 
	 * @param id film ID
	 * @return String object : film name
	 * @throws ServiceException
	 */
	String getFilmNameByID(int id) throws ServiceException;
	
	/**
	 * Receives a set of twelve last added films from the DAO layer and passes it back to the Controller layer or throws an exception if it is empty
	 * 
	 * @return a set of films
	 * @throws ServiceException
	 */
	Set<Film> getTwelveLastAddedFilms() throws ServiceException;
	
	/**
	 * Receives a set of all present films from the DAO layer and passes it back to the Controller layer or throws an exception if it is empty
	 * 
	 * @return a set of films
	 * @throws ServiceException
	 */
	Set<Film> getAllFilms() throws ServiceException;
	
	/**
	 * Verifies input parameter and passes it to the DAO layer, received a set of found films back and returns it to the Controller layer
	 * or throws an exception if it is empty
	 * 
	 * @param text the name of the film that user is searching for
	 * @return a set of found films
	 * @throws ServiceException
	 */
	Set<Film> searchByName(String text) throws ServiceException;
	
	/**
	 * Performs the logic of searching films by several criteria, verifies input parameters and requests necessary sets of films from the DAO layer,
	 * mixing them in the way that all acceptable by the criterion films would be returned back to the Controller layer
	 * 
	 * @param name film name
	 * @param yearFrom the left border of the year searching range
	 * @param yearTo the right border of the year searching range
	 * @param genres film genres array
	 * @param countries film countries array
	 * @return a set of found films or throws an exception if it is empty
	 * @throws ServiceException
	 */
	Set<Film> searchWidened(String name, String yearFrom, String yearTo, String[] genres, String[] countries) throws ServiceException;
	
	/**
	 * Receives the String array of all available genres from the DAO layer and passes them to the Controller layer
	 * 
	 * 
	 * @return String array of available film genres
	 * @throws ServiceException if no genres found
	 */
	String[] getAvailableGenres() throws ServiceException;

	/**
	 * Receives the String array of all available countries from the DAO layer and passes them to the Controller layer
	 * 
	 * 
	 * @return String array of available film countries
	 * @throws ServiceException if no countries found
	 */
	String[] getAvailableCountries() throws ServiceException;
}
