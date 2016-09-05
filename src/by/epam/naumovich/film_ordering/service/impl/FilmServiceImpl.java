package by.epam.naumovich.film_ordering.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.dao.DAOFactory;
import by.epam.naumovich.film_ordering.dao.IFilmDAO;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.film.GetFilmsServiceException;
import by.epam.naumovich.film_ordering.service.util.ExceptionMessages;
import by.epam.naumovich.film_ordering.service.util.Validator;

public class FilmServiceImpl implements IFilmService {

	private static final String MYSQL = "mysql";
	private static final String SPACE = " ";
	
	@Override
	public void addNewFilm(Film film) throws ServiceException {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void deleteFilm(int id) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Film> getTwelveLastAddedFilms() throws ServiceException {
		Set<Film> filmSet = new LinkedHashSet<Film>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IFilmDAO filmDAO = daoFactory.getFilmDAO();
			filmSet = filmDAO.getTwelveLastAddedFilms();
			
			if (filmSet.isEmpty()) {
				throw new GetFilmsServiceException(ExceptionMessages.NO_FILMS_IN_DB);
			}
			
			
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		return filmSet;
	}

	@Override
	public Set<Film> getAllFilms() throws ServiceException {
		Set<Film> filmSet = new LinkedHashSet<Film>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IFilmDAO filmDAO = daoFactory.getFilmDAO();
			filmSet = filmDAO.getAllFilms();
			
			if (filmSet.isEmpty()) {
				throw new GetFilmsServiceException(ExceptionMessages.NO_FILMS_IN_DB);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		return filmSet;
	}

	@Override
	public Film getFilmByID(int id) throws ServiceException {
		Film film = null;
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IFilmDAO filmDAO = daoFactory.getFilmDAO();
			film = filmDAO.getFilmByID(id);
			if (film == null) {
				throw new GetFilmsServiceException(ExceptionMessages.FILM_NOT_PRESENT);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		return film;
	}

	

	@Override
	public Set<Film> searchByName(String text) throws ServiceException {
		if (!Validator.validateStrings(text)) {
			throw new GetFilmsServiceException(ExceptionMessages.NO_FILMS_FOUND);
		}
		
		Set<Film> foundFilms = new LinkedHashSet<Film>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IFilmDAO filmDAO = daoFactory.getFilmDAO();
			foundFilms.addAll(filmDAO.getFilmsByName(text));
			Set<Film> allFilms = filmDAO.getAllFilms();
			
			String searchText = text.toLowerCase();
			boolean moreThanOneWord = false;
			String[] words = null;
			
			if (searchText.split(SPACE).length > 1) {
				moreThanOneWord = true;
				words = searchText.split(SPACE);
			}
			for (Film f : allFilms) {
				String filmName = f.getName().toLowerCase();
				if (filmName.contains(searchText) || searchText.contains(filmName)) {
					foundFilms.add(f);
				}
				if (moreThanOneWord) {
					for (String s : words) {
						if (filmName.contains(s) || s.contains(filmName)) {
							foundFilms.add(f);
						}
					}
				}
				
			}
			
			if (foundFilms.isEmpty()) {
				throw new GetFilmsServiceException(ExceptionMessages.NO_FILMS_FOUND);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		return foundFilms;
	}

	@Override
	public Set<Film> searchWidened(String name, String year, String genre) throws ServiceException {
		
		if (Validator.validateStrings(year)) {
			try {
				int fYear = Integer.parseInt(year);
				if (fYear < 0) {
					throw new GetFilmsServiceException(ExceptionMessages.INVALID_FILM_YEAR);
				}
				
			} catch (NumberFormatException e) {
				throw new GetFilmsServiceException(ExceptionMessages.INVALID_FILM_YEAR, e);
			}
		}
		
		Set<Film> foundFilms = new LinkedHashSet<Film>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
			IFilmDAO filmDAO = daoFactory.getFilmDAO();
			
			if (Validator.validateStrings(year)) {
				foundFilms.addAll(filmDAO.getFilmsByYear(Integer.parseInt(year)));
			}
			
			if (Validator.validateStrings(name)) {
				try {
					foundFilms.addAll(searchByName(name));
				} catch (GetFilmsServiceException e) {
				}
			}
			
			
			
			if (Validator.validateStrings(genre)) {
				foundFilms.addAll(filmDAO.getFilmsByGenre(genre));
			}
			
			if (foundFilms.isEmpty()) {
				throw new GetFilmsServiceException(ExceptionMessages.NO_FILMS_FOUND);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		
		return foundFilms;
	}
}
