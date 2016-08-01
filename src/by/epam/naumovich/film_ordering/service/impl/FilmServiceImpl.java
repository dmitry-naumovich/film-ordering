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

	@Override
	public void addNewFilm(Film film) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Film> getTwelveLastAddedFilms() throws ServiceException {
		List<Film> list = new ArrayList<Film>();
		try {
			DAOFactory daoFactory = DAOFactory.getDAOFactory("mysql");
			IFilmDAO filmDAO = daoFactory.getFilmDAO();
			list = filmDAO.getTwelveLastAddedFilms();
			
			if (list == null) {
				throw new GetFilmsServiceException("No films in database");
			}
		} catch (DAOException e) {
			throw new ServiceException("Error in data source!");
		}
		
		return list;
	}

	@Override
	public List<Film> getAllFilms() {
		// TODO Auto-generated method stub
		return null;
	}

}
