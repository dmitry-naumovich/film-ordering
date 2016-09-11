package by.epam.naumovich.film_ordering.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedHashSet;
import java.util.Set;

import by.epam.naumovich.film_ordering.bean.Film;
import by.epam.naumovich.film_ordering.dao.IFilmDAO;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;
import by.epam.naumovich.film_ordering.dao.util.ExceptionMessages;
import by.epam.naumovich.task82.dao.pool.MySQLConnectionPool;
import by.epam.naumovich.task82.dao.pool.exception.ConnectionPoolException;

public class MySQLFilmDAO implements IFilmDAO {

	private static final MySQLFilmDAO instance = new MySQLFilmDAO();
	
	public static final String INSERT_NEW_FILM = "INSERT INTO Films (f_name, f_year, f_direct, f_country, f_genre, f_actors, f_composer, f_description, f_length, f_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String DELETE_FILM = "DELETE FROM Films WHERE f_id = ?";
	
	public static final String SELECT_FILM_BY_ID = "SELECT * FROM Films WHERE f_id = ?";
	public static final String SELECT_FILM_NAME_BY_ID = "SELECT f_name FROM Films WHERE f_id = ?";
	public static final String SELECT_NEW_FILM_ID = "SELECT f_id FROM Films WHERE f_name = ? AND f_year = ? AND f_direct = ? AND f_length = ?";
	
	public static final String SELECT_TWELVE_LAST_ADDED_FILMS = "SELECT * FROM Films ORDER BY f_id DESC LIMIT 12";
	public static final String SELECT_ALL_FILMS = "SELECT * FROM Films ORDER BY f_rating DESC";
	
	public static final String SELECT_FILMS_BY_NAME = "SELECT * FROM Films WHERE f_name = ?";
	public static final String SELECT_FILMS_BY_YEAR = "SELECT * FROM Films WHERE f_year = ?";
	public static final String SELECT_FILMS_BY_GENRE = "SELECT * FROM Films WHERE FIND_IN_SET(?, f_genre) > 0";
	
	public static final String SELECT_FILMS_BY_NAME_YEAR = "SELECT * FROM Films WHERE f_name = ? AND f_year = ?";
	public static final String SELECT_FILMS_BY_NAME_GENRE = "SELECT * FROM Films WHERE f_name = ? AND FIND_IN_SET(?, f_genre) > 0";
	public static final String SELECT_FILMS_BY_YEAR_GENRE = "SELECT * FROM Films WHERE f_year = ? AND FIND_IN_SET(?, f_genre) > 0";
	
	public static final String SELECT_FILMS_BY_NAME_YEAR_GENRE = "SELECT * FROM Films WHERE f_name = ? AND f_year = ? AND FIND_IN_SET(?, f_genre) > 0";
	public static final String SELECT_FILMS_BETWEEN_YEARS = "SELECT * FROM Films WHERE f_year >= ? AND f_year <= ?";
	
	public static MySQLFilmDAO getInstance() {
		return instance;
	}
	
	@Override
	public int addFilm(Film film) throws DAOException {
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		PreparedStatement st2 = null;
		ResultSet rs = null;
		
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(INSERT_NEW_FILM);
			st.setString(1, film.getName());
			st.setInt(2, film.getYear());
			st.setString(3, film.getDirector());
			
			if (film.getCountry() == null){
				st.setNull(4, Types.VARCHAR);
			}
			else {
				st.setString(4, film.getCountry());
			}
			
			if (film.getGenre() == null){
				st.setNull(5, Types.VARCHAR);
			}
			else {
				st.setString(5, film.getGenre());
			}
			
			if (film.getActors() == null){
				st.setNull(6, Types.VARCHAR);
			}
			else {
				st.setString(6, film.getActors());
			}
			
			if (film.getComposer() == null){
				st.setNull(7, Types.VARCHAR);
			}
			else {
				st.setString(7, film.getComposer());
			}
			
			if (film.getDescription() == null){
				st.setNull(8, Types.VARCHAR);
			}
			else {
				st.setString(8, film.getDescription());
			}
			
			st.setInt(9, film.getLength());
			st.setFloat(10, film.getPrice());
			st.executeUpdate();
			
			st2 = con.prepareStatement(SELECT_NEW_FILM_ID);
			st2.setString(1, film.getName());
			st2.setInt(2, film.getYear());
			st2.setString(3, film.getDirector());
			st2.setInt(4, film.getLength());
			rs = st2.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_INSERT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.PREP_STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) { pool.closeConnection(con); }
			}
		}
		
		return 0;
		
	}

	@Override
	public void deleteFilm(int id) throws DAOException {
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(DELETE_FILM);
			st.setInt(1, id);
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_DELETE_FAILTURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.PREP_STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) { pool.closeConnection(con); }
			}
		}
	}
	

	@Override
	public Set<Film> getAllFilms() throws DAOException {
		Set<Film> filmSet = new LinkedHashSet<Film>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_ALL_FILMS);
			rs = st.executeQuery();
			
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt(1));
				film.setName(rs.getString(2));
				film.setYear(rs.getInt(3));
				film.setDirector(rs.getString(4));
				film.setCountry(rs.getString(5));
				film.setGenre(rs.getString(6));
				film.setActors(rs.getString(7));
				film.setComposer(rs.getString(8));
				film.setDescription(rs.getString(9));
				film.setLength(rs.getInt(10));
				film.setRating(rs.getFloat(11));
				film.setPrice(rs.getFloat(12));
				
				filmSet.add(film);
			}
			
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_SELECT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.RS_OR_STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) { pool.closeConnection(con); }
			}
		}
		return filmSet;
	}
	
	@Override
	public Film getFilmByID(int id) throws DAOException {
		Film film = new Film();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_FILM_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				film.setId(rs.getInt(1));
				film.setName(rs.getString(2));
				film.setYear(rs.getInt(3));
				film.setDirector(rs.getString(4));
				film.setCountry(rs.getString(5));
				film.setGenre(rs.getString(6));
				film.setActors(rs.getString(7));
				film.setComposer(rs.getString(8));
				film.setDescription(rs.getString(9));
				film.setLength(rs.getInt(10));
				film.setRating(rs.getFloat(11));
				film.setPrice(rs.getFloat(12));
			}
			
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_SELECT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.RS_OR_STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) { pool.closeConnection(con); }
			}
		}
		return film;
	}
	
	@Override
	public String getFilmNameByID(int id) throws DAOException {
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_FILM_NAME_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				return rs.getString(1);
			}
			
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_SELECT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.RS_OR_STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) { pool.closeConnection(con); }
			}
		}
		return null;
	}

	@Override
	public Set<Film> getFilmsByName(String name) throws DAOException {
		Set<Film> filmSet = new LinkedHashSet<Film>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_FILMS_BY_NAME);
			st.setString(1, name);
			rs = st.executeQuery();
			
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt(1));
				film.setName(rs.getString(2));
				film.setYear(rs.getInt(3));
				film.setDirector(rs.getString(4));
				film.setCountry(rs.getString(5));
				film.setGenre(rs.getString(6));
				film.setActors(rs.getString(7));
				film.setComposer(rs.getString(8));
				film.setDescription(rs.getString(9));
				film.setLength(rs.getInt(10));
				film.setRating(rs.getFloat(11));
				film.setPrice(rs.getFloat(12));
				
				filmSet.add(film);
			}
			
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_SELECT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.RS_OR_STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) { pool.closeConnection(con); }
			}
		}
		return filmSet;
	}


	@Override
	public Set<Film> getFilmsByYear(int year) throws DAOException {
		Set<Film> filmSet = new LinkedHashSet<Film>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_FILMS_BY_YEAR);
			st.setInt(1, year);
			rs = st.executeQuery();
			
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt(1));
				film.setName(rs.getString(2));
				film.setYear(rs.getInt(3));
				film.setDirector(rs.getString(4));
				film.setCountry(rs.getString(5));
				film.setGenre(rs.getString(6));
				film.setActors(rs.getString(7));
				film.setComposer(rs.getString(8));
				film.setDescription(rs.getString(9));
				film.setLength(rs.getInt(10));
				film.setRating(rs.getFloat(11));
				film.setPrice(rs.getFloat(12));
				
				filmSet.add(film);
			}
			
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_SELECT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.RS_OR_STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) { pool.closeConnection(con); }
			}
		}
		return filmSet;
	}

	@Override
	public Set<Film> getFilmsByGenre(String genre) throws DAOException {
		Set<Film> filmSet = new LinkedHashSet<Film>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_FILMS_BY_GENRE);
			st.setString(1, genre);
			rs = st.executeQuery();
			
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt(1));
				film.setName(rs.getString(2));
				film.setYear(rs.getInt(3));
				film.setDirector(rs.getString(4));
				film.setCountry(rs.getString(5));
				film.setGenre(rs.getString(6));
				film.setActors(rs.getString(7));
				film.setComposer(rs.getString(8));
				film.setDescription(rs.getString(9));
				film.setLength(rs.getInt(10));
				film.setRating(rs.getFloat(11));
				film.setPrice(rs.getFloat(12));
				
				filmSet.add(film);
			}
			
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_SELECT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.RS_OR_STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) { pool.closeConnection(con); }
			}
		}
		return filmSet;
	}

	@Override
	public Set<Film> getFilmsByYearGenre(int year, String genre) throws DAOException {
		Set<Film> filmSet = new LinkedHashSet<Film>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_FILMS_BY_YEAR_GENRE);
			st.setInt(1, year);
			st.setString(2, genre);
			rs = st.executeQuery();
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt(1));
				film.setName(rs.getString(2));
				film.setYear(rs.getInt(3));
				film.setDirector(rs.getString(4));
				film.setCountry(rs.getString(5));
				film.setGenre(rs.getString(6));
				film.setActors(rs.getString(7));
				film.setComposer(rs.getString(8));
				film.setDescription(rs.getString(9));
				film.setLength(rs.getInt(10));
				film.setRating(rs.getFloat(11));
				film.setPrice(rs.getFloat(12));
				
				filmSet.add(film);
			}
			
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_SELECT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.RS_OR_STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) { pool.closeConnection(con); }
			}
		}
		return filmSet;
	}

	@Override
	public Set<Film> getFilmsByNameYear(String name, int year) throws DAOException {
		Set<Film> filmSet = new LinkedHashSet<Film>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_FILMS_BY_NAME_YEAR);
			st.setString(1, name);
			st.setInt(2, year);
			rs = st.executeQuery();
			
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt(1));
				film.setName(rs.getString(2));
				film.setYear(rs.getInt(3));
				film.setDirector(rs.getString(4));
				film.setCountry(rs.getString(5));
				film.setGenre(rs.getString(6));
				film.setActors(rs.getString(7));
				film.setComposer(rs.getString(8));
				film.setDescription(rs.getString(9));
				film.setLength(rs.getInt(10));
				film.setRating(rs.getFloat(11));
				film.setPrice(rs.getFloat(12));
				
				filmSet.add(film);
			}
			
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_SELECT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.RS_OR_STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) { pool.closeConnection(con); }
			}
		}
		return filmSet;
	}

	@Override
	public Set<Film> getFilmsByNameGenre(String name, String genre) throws DAOException {
		Set<Film> filmSet = new LinkedHashSet<Film>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_FILMS_BY_NAME_GENRE);
			st.setString(1, name);
			st.setString(2, genre);
			rs = st.executeQuery();
			
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt(1));
				film.setName(rs.getString(2));
				film.setYear(rs.getInt(3));
				film.setDirector(rs.getString(4));
				film.setCountry(rs.getString(5));
				film.setGenre(rs.getString(6));
				film.setActors(rs.getString(7));
				film.setComposer(rs.getString(8));
				film.setDescription(rs.getString(9));
				film.setLength(rs.getInt(10));
				film.setRating(rs.getFloat(11));
				film.setPrice(rs.getFloat(12));
				
				filmSet.add(film);
			}
			
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_SELECT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.RS_OR_STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) { pool.closeConnection(con); }
			}
		}
		return filmSet;
	}

	@Override
	public Set<Film> getFilmsByNameYearGenre(String name, int year, String genre) throws DAOException {
		Set<Film> filmSet = new LinkedHashSet<Film>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_FILMS_BY_NAME_YEAR_GENRE);
			st.setString(1, name);
			st.setInt(2, year);
			st.setString(3, genre);
			rs = st.executeQuery();
			
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt(1));
				film.setName(rs.getString(2));
				film.setYear(rs.getInt(3));
				film.setDirector(rs.getString(4));
				film.setCountry(rs.getString(5));
				film.setGenre(rs.getString(6));
				film.setActors(rs.getString(7));
				film.setComposer(rs.getString(8));
				film.setDescription(rs.getString(9));
				film.setLength(rs.getInt(10));
				film.setRating(rs.getFloat(11));
				film.setPrice(rs.getFloat(12));
				
				filmSet.add(film);
			}
			
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_SELECT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.RS_OR_STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) { pool.closeConnection(con); }
			}
		}
		return filmSet;
	}

	@Override
	public Set<Film> getTwelveLastAddedFilms() throws DAOException {
		Set<Film> filmSet = new LinkedHashSet<Film>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_TWELVE_LAST_ADDED_FILMS);
			rs = st.executeQuery();
			
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt(1));
				film.setName(rs.getString(2));
				film.setYear(rs.getInt(3));
				film.setDirector(rs.getString(4));
				film.setCountry(rs.getString(5));
				film.setGenre(rs.getString(6));
				film.setActors(rs.getString(7));
				film.setComposer(rs.getString(8));
				film.setDescription(rs.getString(9));
				film.setLength(rs.getInt(10));
				film.setRating(rs.getFloat(11));
				film.setPrice(rs.getFloat(12));
				
				filmSet.add(film);
			}
			
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_SELECT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.RS_OR_STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) { pool.closeConnection(con); }
			}
		}
		return filmSet;
	}

	@Override
	public Set<Film> getFilmsBetweenYears(int yearFrom, int yearTo) throws DAOException {
		Set<Film> filmSet = new LinkedHashSet<Film>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_FILMS_BETWEEN_YEARS);
			st.setInt(1, yearFrom);
			st.setInt(2, yearTo);
			rs = st.executeQuery();
			
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt(1));
				film.setName(rs.getString(2));
				film.setYear(rs.getInt(3));
				film.setDirector(rs.getString(4));
				film.setCountry(rs.getString(5));
				film.setGenre(rs.getString(6));
				film.setActors(rs.getString(7));
				film.setComposer(rs.getString(8));
				film.setDescription(rs.getString(9));
				film.setLength(rs.getInt(10));
				film.setRating(rs.getFloat(11));
				film.setPrice(rs.getFloat(12));
				
				filmSet.add(film);
			}
			
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_SELECT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.RS_OR_STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) { pool.closeConnection(con); }
			}
		}
		return filmSet;
	}
}
