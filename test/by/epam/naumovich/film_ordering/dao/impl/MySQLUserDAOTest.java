package by.epam.naumovich.film_ordering.dao.impl;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import by.epam.naumovich.film_ordering.bean.User;
import by.epam.naumovich.film_ordering.dao.DAOFactory;
import by.epam.naumovich.film_ordering.dao.IUserDAO;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;


/**
 * Tests DAO layer methods overridden in MySQLUserDAO class in a way of comparing expected and actual results with the help of JUnit 4 framework.
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 *
 */
public class MySQLUserDAOTest {

	/**
	 * Database type used in this test suite.
	 * 
	 */
	private static final String MYSQL = "mysql";
	/**
	 * This object will be compared to the actual object taken from the DAO layer.
	 * 
	 */
	private User expectedUser;
	
	/**
	 * Executes every time before a single method call and initializes expected User object.
	 * 
	 */
	@Before
	public void initTestReview() {
		expectedUser = new User();
		expectedUser.setLogin("testlogin");
		expectedUser.setName("testname");
		expectedUser.setSurname("testsurname");
		expectedUser.setSex('m');
		expectedUser.setType('c');
		expectedUser.setRegDate(Date.valueOf(LocalDate.now()));
		expectedUser.setRegTime(Time.valueOf(LocalTime.now()));
		expectedUser.setEmail("testemail@text.com");
		expectedUser.setPassword("testpass");
		expectedUser.setAbout("aboutuser test text");
	}
	
	/**
	 * Adds expectedUser to the data source via DAO layer, gets it back and compares two results.
	 * Tests if the user was really added.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void addUser() throws DAOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
		IUserDAO dao = daoFactory.getUserDAO();
		
		int id = dao.addUser(expectedUser);
		User actualUser = dao.getUserByID(id);
		dao.deleteUser(id);
		
		Assert.assertEquals(expectedUser.getLogin(), actualUser.getLogin());
		Assert.assertEquals(expectedUser.getName(), actualUser.getName());
		Assert.assertEquals(expectedUser.getSurname(), actualUser.getSurname());
		Assert.assertEquals(expectedUser.getSex(), actualUser.getSex());
		Assert.assertEquals(expectedUser.getType(), actualUser.getType());
		Assert.assertEquals(expectedUser.getRegDate(), actualUser.getRegDate());
		Assert.assertEquals(expectedUser.getRegTime(), actualUser.getRegTime());
		Assert.assertEquals(expectedUser.getEmail(), actualUser.getEmail());
		Assert.assertEquals(expectedUser.getPassword(), actualUser.getPassword());
		Assert.assertEquals(expectedUser.getAbout(), actualUser.getAbout());
	}
	
	/**
	 * Adds expectedUser to the data source via DAO layer, updates it, gets it back and compares two results.
	 * Tests if the user was really edited.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void updateUser() throws DAOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
		IUserDAO dao = daoFactory.getUserDAO();
		
		int id = dao.addUser(expectedUser);
		expectedUser.setName("upd text name");
		expectedUser.setPassword("new-password");
		expectedUser.setSex('f');
		dao.updateUser(id, expectedUser);
		User actualUser = dao.getUserByID(id);
		dao.deleteUser(id);
		
		Assert.assertEquals(expectedUser.getLogin(), actualUser.getLogin());
		Assert.assertEquals(expectedUser.getName(), actualUser.getName());
		Assert.assertEquals(expectedUser.getSurname(), actualUser.getSurname());
		Assert.assertEquals(expectedUser.getSex(), actualUser.getSex());
		Assert.assertEquals(expectedUser.getType(), actualUser.getType());
		Assert.assertEquals(expectedUser.getRegDate(), actualUser.getRegDate());
		Assert.assertEquals(expectedUser.getRegTime(), actualUser.getRegTime());
		Assert.assertEquals(expectedUser.getEmail(), actualUser.getEmail());
		Assert.assertEquals(expectedUser.getPassword(), actualUser.getPassword());
		Assert.assertEquals(expectedUser.getAbout(), actualUser.getAbout());
	}
	
	/**
	 * Adds expectedUser to the data source via DAO layer, deletes it and then tries to get it back expecting the null result.
	 * Tests if the user was really deleted.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void deleteUser() throws DAOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
		IUserDAO dao = daoFactory.getUserDAO();
		
		int id = dao.addUser(expectedUser);
		dao.deleteUser(id);
		User actualUser = dao.getUserByID(id);
		
		Assert.assertNull(actualUser);
	}
	
	/**
	 * Gets users in ban and iterates over all users checking if  of same year, 
	 * removing them from the first collection if found. The expected result is empty collection.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void getUsersInBan() throws DAOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(MYSQL);
		IUserDAO dao = daoFactory.getUserDAO();
		
		Set<User> usersInBan = dao.getUsersInBan();
		
		for (User u : dao.getAllUsers()) {
			if (dao.userIsInBan(u.getId())) {
				usersInBan.remove(u);
			}
		}
		
		Assert.assertTrue(usersInBan.isEmpty());
		
	}
}
