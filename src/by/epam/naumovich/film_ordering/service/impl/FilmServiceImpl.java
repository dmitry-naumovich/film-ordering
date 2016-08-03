package by.epam.naumovich.film_ordering.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.dao.DAOFactory;
import by.epam.naumovich.film_ordering.dao.IFilmDAO;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.exception.GetFilmsServiceException;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;

public class FilmServiceImpl implements IFilmService {

	private static final String MYSQL = "mysql";
	//private static final String DASH = "\u2014";
	
	@Override
	public void addNewFilm(Film film) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Film> getTwelveLastAddedFilms() throws ServiceException {
		List<Film> list = new ArrayList<Film>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IFilmDAO filmDAO = daoFactory.getFilmDAO();
			list = filmDAO.getTwelveLastAddedFilms();
			
			if (list.isEmpty()) {
				throw new GetFilmsServiceException("No films in database");
			}
			
			
		} catch (DAOException e) {
			throw new ServiceException("Error in data source!");
		}
		
		return list;
	}

	@Override
	public List<Film> getAllFilms() throws ServiceException {
		List<Film> list = new ArrayList<Film>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IFilmDAO filmDAO = daoFactory.getFilmDAO();
			list = filmDAO.getAllFilms();
			
			if (list.isEmpty()) {
				throw new GetFilmsServiceException("No films in database");
			}
			
			
		} catch (DAOException e) {
			throw new ServiceException("Error in data source!");
		}
		
		return list;
	}

	@Override
	public Film getFilmByID(int id) throws ServiceException {
		Film film = null;
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IFilmDAO filmDAO = daoFactory.getFilmDAO();
			film = filmDAO.getFilmByID(id);
			if (film == null) {
				throw new GetFilmsServiceException("Film is missing in database");
			}
			
			
		} catch (DAOException e) {
			throw new ServiceException("Error in data source!");
		}
		
		return film;
	}
	
	/*private void checkFilmForNullFields(Film film) {
		if (film.getCountry() == null) {
			film.setCountry(DASH);
		}
		if (film.getGenre() == null) {
			film.setGenre(DASH);
		}
		if (film.getActors() == null) {
			film.setActors(DASH);
		}
		if (film.getComposer() == null) {
			film.setComposer(DASH);
		}
		if (film.getDescription() == null) {
			film.setDescription(DASH);
		}
	}
	
	private void checkFilmListForNullFields(List<Film> list) {
		for (Film f : list) {
			checkFilmForNullFields(f);
		}
	}*/

}
