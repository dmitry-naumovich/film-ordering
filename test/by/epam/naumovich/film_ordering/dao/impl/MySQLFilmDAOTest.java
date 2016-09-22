package by.epam.naumovich.film_ordering.dao.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.dao.DAOFactory;
import by.epam.naumovich.film_ordering.dao.IFilmDAO;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;

/**
 * Tests DAO layer methods overridden in MySQLFilmDAO class in a way of comparing expected and actual results with the help of JUnit 4 framework.
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 *
 */
public class MySQLFilmDAOTest {

	/**
	 * Database type used in this test suite.
	 * 
	 */
	private static final String MYSQL = "mysql";
	/**
	 * This object will be compared to the actual object taken from the DAO layer.
	 * 
	 */
	private Film expectedFilm;
	
	/**
	 * Executes every time before a single method call and initializes expected Film object.
	 * 
	 */
	@Before
	public void initTestFilm() {
		expectedFilm = new Film();
		expectedFilm.setId(1000);
		expectedFilm.setName("Test film name");
		expectedFilm.setYear(2016);
		expectedFilm.setDirector("Test film director");
		expectedFilm.setCountry("France");
		expectedFilm.setGenre("Thriller");
		expectedFilm.setLength(200);
		expectedFilm.setPrice(12f);
		expectedFilm.setRating(4f);
		expectedFilm.setActors("Test actors");
		expectedFilm.setComposer("Test composer");
		expectedFilm.setDescription("Test film description");
	}
	
	/**
	 * Adds expectedFilm to the data source via DAO layer, gets it back and compares two results.
	 * Tests if the film was correctly added.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void addFilm() throws DAOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
		IFilmDAO filmDAO = daoFactory.getFilmDAO();
		
		int filmID = filmDAO.addFilm(expectedFilm);
        Film actualFilm = filmDAO.getFilmByID(filmID);
        filmDAO.deleteFilm(filmID);
        
        Assert.assertEquals(expectedFilm.getName(), actualFilm.getName());
        Assert.assertEquals(expectedFilm.getYear(), actualFilm.getYear());
        Assert.assertEquals(expectedFilm.getDirector(), actualFilm.getDirector());
        Assert.assertEquals(expectedFilm.getCountry(), actualFilm.getCountry());
        Assert.assertEquals(expectedFilm.getGenre(), actualFilm.getGenre());
        Assert.assertEquals(expectedFilm.getLength(), actualFilm.getLength());
        Assert.assertEquals(expectedFilm.getPrice(), actualFilm.getPrice(), 0.01f);
        Assert.assertEquals(expectedFilm.getActors(), actualFilm.getActors());
        Assert.assertEquals(expectedFilm.getComposer(), actualFilm.getComposer());
        Assert.assertEquals(expectedFilm.getDescription(), actualFilm.getDescription());
	}

	/**
	 * Adds expectedFilm to the data source via DAO layer, deletes it and then tries to get it back expecting the null result.
	 * Tests if the film was correctly deleted.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void deleteFilm() throws DAOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
		IFilmDAO filmDAO = daoFactory.getFilmDAO();

		int filmID = filmDAO.addFilm(expectedFilm);
		filmDAO.deleteFilm(filmID); 
        Film deletedFilm = filmDAO.getFilmByID(filmID);

        Assert.assertNull(deletedFilm);
	}

	/**
	 * Adds expectedFilm to the data source via DAO layer, edits it, gets it back and compares two results.
	 * Tests if the film was correctly edited.
	 * 
	 * @throws DAOException
	 * @throws InterruptedException 
	 */
	@Test
	public void editFilm() throws DAOException, InterruptedException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
		IFilmDAO filmDAO = daoFactory.getFilmDAO();
		
		int filmID = filmDAO.addFilm(expectedFilm);
		expectedFilm.setName("test name 1");
		expectedFilm.setActors("test actors 1");
		expectedFilm.setPrice(12);
		filmDAO.editFilm(filmID, expectedFilm);
		Film actualFilm = filmDAO.getFilmByID(filmID);
        filmDAO.deleteFilm(filmID);
	    
        Assert.assertEquals(expectedFilm.getName(), actualFilm.getName());
        Assert.assertEquals(expectedFilm.getPrice(), actualFilm.getPrice(), 0.01f);
        Assert.assertEquals(expectedFilm.getActors(), actualFilm.getActors());
	}
	
	/**
	 * Adds expectedFilm to the data source via DAO layer, gets it back by ID and compares two results.
	 * Tests if the valid film entity is returned by id.
	 * 
	 * @throws DAOException
	 */
	public void getFilmByID() throws DAOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
		IFilmDAO filmDAO = daoFactory.getFilmDAO();
		
		int filmID = filmDAO.addFilm(expectedFilm);
		Film actualFilm = filmDAO.getFilmByID(filmID);
		filmDAO.deleteFilm(filmID);
		
		Assert.assertEquals(expectedFilm.getName(), actualFilm.getName());
        Assert.assertEquals(expectedFilm.getYear(), actualFilm.getYear());
        Assert.assertEquals(expectedFilm.getDirector(), actualFilm.getDirector());
        Assert.assertEquals(expectedFilm.getCountry(), actualFilm.getCountry());
        Assert.assertEquals(expectedFilm.getGenre(), actualFilm.getGenre());
        Assert.assertEquals(expectedFilm.getLength(), actualFilm.getLength());
        Assert.assertEquals(expectedFilm.getPrice(), actualFilm.getPrice(), 0.01f);
        Assert.assertEquals(expectedFilm.getActors(), actualFilm.getActors());
        Assert.assertEquals(expectedFilm.getComposer(), actualFilm.getComposer());
        Assert.assertEquals(expectedFilm.getDescription(), actualFilm.getDescription());
		
	}
	
	/**
	 * Adds expectedFilm to the data source via DAO layer, gets its name back by ID and compares two results.
	 * Tests if valid film name is returned by id.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void getFilmNameByID() throws DAOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
		IFilmDAO filmDAO = daoFactory.getFilmDAO();
		
		int filmID = filmDAO.addFilm(expectedFilm);
		String actualFilmName = filmDAO.getFilmNameByID(filmID);
		filmDAO.deleteFilm(filmID);
		
		Assert.assertEquals(expectedFilm.getName(), actualFilmName);
	}
	
	/**
	 * Gets all films in two different ways and compares results which must be equal.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void getAllFilms() throws DAOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
		IFilmDAO filmDAO = daoFactory.getFilmDAO();
		
		Set<Film> allFilms1 = filmDAO.getAllFilms();
		Set<Film> allFilms2 = filmDAO.getFilmsBetweenYears(1, 3000);
		
		Assert.assertEquals(allFilms1, allFilms2);
	}

	/**
	 * Gets films by name in two different ways and compares results which must be equal.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void getFilmsByName() throws DAOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
		IFilmDAO filmDAO = daoFactory.getFilmDAO();
		
		int filmID = filmDAO.addFilm(expectedFilm);
		Set<Film> filmsByName1 = filmDAO.getFilmsByName(expectedFilm.getName());
		
		Set<Film> filmsByName2 = new LinkedHashSet<Film>();
		for (Film f : filmDAO.getAllFilms()) {
			if (f.getName().toLowerCase().equals(expectedFilm.getName().toLowerCase())) {
				filmsByName2.add(f);
			}
		}
		
		filmDAO.deleteFilm(filmID);
		Assert.assertEquals(filmsByName1, filmsByName2);
	}
	
	/**
	 * Gets films by year in two different ways and compares results which must be equal.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void getFilmsByYear() throws DAOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
		IFilmDAO filmDAO = daoFactory.getFilmDAO();
		
		Set<Film> filmsByYear1 = filmDAO.getFilmsByYear(2012);
		
		Set<Film> filmsByYear2 = new LinkedHashSet<Film>();
		for (Film f : filmDAO.getAllFilms()) {
			if (f.getYear() == 2012) {
				filmsByYear2.add(f);
			}
		}
		
		Assert.assertEquals(filmsByYear1, filmsByYear2);
	}
	
	/**
	 * Gets films by genre in two different ways and compares results which must be equal.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void getFilmsByGenre() throws DAOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
		IFilmDAO filmDAO = daoFactory.getFilmDAO();
		
		Set<Film> filmsByGenre1 = filmDAO.getFilmsByGenre(expectedFilm.getGenre());
		
		Set<Film> filmsByGenre2 = new LinkedHashSet<Film>();
		for (Film f : filmDAO.getAllFilms()) {
			if (f.getGenre().toLowerCase().contains(expectedFilm.getGenre().toLowerCase())) {
				filmsByGenre2.add(f);
			}
		}
		Assert.assertEquals(filmsByGenre1, filmsByGenre2);
	}
	
	/**
	 * Gets films by country in two different ways and compares results which must be equal.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void getFilmsByCountry() throws DAOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
		IFilmDAO filmDAO = daoFactory.getFilmDAO();
		
		Set<Film> filmsByCountry1 = filmDAO.getFilmsByCountry(expectedFilm.getCountry());
		
		Set<Film> filmsByCountry2 = new LinkedHashSet<Film>();
		for (Film f : filmDAO.getAllFilms()) {
			if (f.getCountry().toLowerCase().contains(expectedFilm.getCountry().toLowerCase())) {
				filmsByCountry2.add(f);
			}
		}
		Assert.assertEquals(filmsByCountry1, filmsByCountry2);
	}
	
	/**
	 * Gets films between years in two different ways and compares results which must be equal.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void getFilmsBetweenYears() throws DAOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
		IFilmDAO filmDAO = daoFactory.getFilmDAO();
		
		Set<Film> filmsByYears1 = filmDAO.getFilmsBetweenYears(2000, 2005);
		
		Set<Film> filmsByYears2 = new LinkedHashSet<Film>();
		for (Film f : filmDAO.getAllFilms()) {
			if (f.getYear() >= 2000 && f.getYear() <= 2005) {
				filmsByYears2.add(f);
			}
		}
		Assert.assertEquals(filmsByYears1, filmsByYears2);
	}
}
