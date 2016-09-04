package by.epam.naumovich.film_ordering.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.film_ordering.bean.News;
import by.epam.naumovich.film_ordering.dao.INewsDAO;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;
import by.epam.naumovich.film_ordering.dao.util.ExceptionMessages;
import by.epam.naumovich.task82.dao.pool.MySQLConnectionPool;
import by.epam.naumovich.task82.dao.pool.exception.ConnectionPoolException;

public class MySQLNewsDAO implements INewsDAO {

	public static final String INSERT_NEW_NEWS = "INSERT INTO News (n_date, n_time, n_title, n_text) VALUES (?, ?, ?)";
	public static final String DELETE_NEWS = "DELETE FROM News WHERE n_id = ?";
	public static final String SELECT_ALL_NEWS = "SELECT * FROM News";
	public static final String SELECT_NEWS_BY_ID = "SELECT * FROM News WHERE n_id = ?";
	public static final String SELECT_NEWS_BY_YEAR = "SELECT * FROM News WHERE YEAR(n_date) = ?";
	public static final String SELECT_NEWS_BY_MONTH_AND_YEAR = "SELECT * FROM News WHERE MONTH(n_date) = ? AND YEAR(n_date) = ?";
	
	private static final MySQLNewsDAO instance = new MySQLNewsDAO();
	
	public static MySQLNewsDAO getInstance() {
		return instance;
	}
	
	@Override
	public void addNews(News news) throws DAOException {
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(INSERT_NEW_NEWS);
			st.setDate(1, news.getDate());
			st.setTime(2, news.getTime());
			st.setString(3, news.getTitle());
			st.setString(4, news.getText());
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
				pool.closeConnection(con);
			}
		}
	}

	@Override
	public void deleteNews(News news) throws DAOException {
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(DELETE_NEWS);
			st.setString(1, String.valueOf(news.getId()));
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
				pool.closeConnection(con);
			}
		}
	}

	@Override
	public List<News> getAllNews() throws DAOException {
		List<News> newsList = new ArrayList<News>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_ALL_NEWS);
			rs = st.executeQuery();
			
			while (rs.next()) {
				News news = new News();
				news.setId(rs.getInt(1));
				news.setDate(rs.getDate(2));
				news.setTime(rs.getTime(3));
				news.setTitle(rs.getString(4));
				news.setText(rs.getString(5));
				newsList.add(news);
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
				throw new DAOException(ExceptionMessages.RS_OR_STATEMENT_NOT_CLOSED);
			} finally {
				pool.closeConnection(con);
			}
			
		}
		return newsList;
	}

	@Override
	public List<News> getNewsByYear(int year) throws DAOException {
		List<News> newsList = new ArrayList<News>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_NEWS_BY_YEAR);
			st.setInt(1, year);
			rs = st.executeQuery();
			
			while (rs.next()) {
				News news = new News();
				news.setId(rs.getInt(1));
				news.setDate(rs.getDate(2));
				news.setTime(rs.getTime(3));
				news.setTitle(rs.getString(4));
				news.setText(rs.getString(5));
				
				newsList.add(news);
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
				throw new DAOException(ExceptionMessages.RS_OR_STATEMENT_NOT_CLOSED);
			} finally {
				pool.closeConnection(con);
			}
			
		}
		return newsList;
	}

	@Override
	public List<News> getNewsByMonthAndYear(int month, int year) throws DAOException {
		List<News> newsList = new ArrayList<News>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_NEWS_BY_MONTH_AND_YEAR);
			st.setInt(1, month);
			st.setInt(2, year);
			rs = st.executeQuery();
			
			while (rs.next()) {
				News news = new News();
				news.setId(rs.getInt(1));
				news.setDate(rs.getDate(2));
				news.setTime(rs.getTime(3));
				news.setTitle(rs.getString(4));
				news.setText(rs.getString(5));
				
				newsList.add(news);
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
				throw new DAOException(ExceptionMessages.RS_OR_STATEMENT_NOT_CLOSED);
			} finally {
				pool.closeConnection(con);
			}
			
		}
		return newsList;
	}

	@Override
	public News getNewsById(int id) throws DAOException {
		News news = null;
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_NEWS_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			
			while (rs.next()) {
				news = new News();
				news.setId(rs.getInt(1));
				news.setDate(rs.getDate(2));
				news.setTime(rs.getTime(3));
				news.setTitle(rs.getString(4));
				news.setText(rs.getString(5));
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
				throw new DAOException(ExceptionMessages.RS_OR_STATEMENT_NOT_CLOSED);
			} finally {
				pool.closeConnection(con);
			}
			
		}
		return news;
	}

}
