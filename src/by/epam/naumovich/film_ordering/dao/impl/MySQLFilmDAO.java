package by.epam.naumovich.film_ordering.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public static final String INSERT_NEW_FILM = "INSERT INTO Films (f_name, f_year, f_direct, f_country, f_genre, f_actors, f_composer, f_description, f_length, f_rating, f_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String DELETE_FILM = "DELETE FROM Films WHERE f_id = ?";
	
	public static final String SELECT_FILM_BY_ID = "SELECT * FROM Films WHERE f_id = ?";
	
	public static final String SELECT_TWELVE_LAST_ADDED_FILMS = "SELECT * FROM Films ORDER BY f_id DESC LIMIT 12";
	public static final String SELECT_ALL_FILMS = "SELECT * FROM Films ORDER BY f_rating DESC";
	
	public static final String SELECT_FILMS_BY_NAME = "SELECT * FROM Films WHERE f_name = ?";
	public static final String SELECT_FILMS_BY_YEAR = "SELECT * FROM Films WHERE f_year = ?";
	public static final String SELECT_FILMS_BY_GENRE = "SELECT * FROM Films WHERE FIND_IN_SET(?, f_genre) > 0";
	
	public static final String SELECT_FILMS_BY_NAME_YEAR = "SELECT * FROM Films WHERE f_name = ? AND f_year = ?";
	public static final String SELECT_FILMS_BY_NAME_GENRE = "SELECT * FROM Films WHERE f_name = ? AND FIND_IN_SET(?, f_genre) > 0";
	public static final String SELECT_FILMS_BY_YEAR_GENRE = "SELECT * FROM Films WHERE f_year = ? AND FIND_IN_SET(?, f_genre) > 0";
	
	public static final String SELECT_FILMS_BY_NAME_YEAR_GENRE = "SELECT * FROM Films WHERE f_name = ? AND f_year = ? AND FIND_IN_SET(?, f_genre) > 0";
	
	public static MySQLFilmDAO getInstance() {
		return instance;
	}
	
	@Override
	public void addNewFilm(Film film) throws DAOException {
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(INSERT_NEW_FILM);
			st.setString(1, film.getName());
			st.setInt(2, film.getYear());
			st.setString(3, film.getDirector());
			st.setString(4, film.getCountry());
			st.setString(5, film.getGenre());
			st.setString(6, film.getActors());
			st.setString(7, film.getComposer());
			st.setString(8, film.getDescription());
			st.setInt(9, film.getLength());
			st.setFloat(10, film.getRating());
			st.setFloat(11, film.getPrice());
			st.executeUpdate();
			
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
		
	}

	@Override
	public void deleteFilm(Film film) throws DAOException {
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(DELETE_FILM);
			st.setString(1, String.valueOf(film.getId()));
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
}
