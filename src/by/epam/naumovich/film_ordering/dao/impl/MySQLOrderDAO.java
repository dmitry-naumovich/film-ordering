package by.epam.naumovich.film_ordering.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.film_ordering.bean.Order;
import by.epam.naumovich.film_ordering.dao.IOrderDAO;
import by.epam.naumovich.film_ordering.dao.exception.DAOException;
import by.epam.naumovich.film_ordering.dao.util.ExceptionMessages;
import by.epam.naumovich.task82.dao.pool.MySQLConnectionPool;
import by.epam.naumovich.task82.dao.pool.exception.ConnectionPoolException;

public class MySQLOrderDAO implements IOrderDAO {

	private static final MySQLOrderDAO instance = new MySQLOrderDAO();
	
	public static final String INSERT_NEW_ORDER = "INSERT INTO Orders (o_user, o_film, o_date, o_time, o_price, o_discount,_paym) VALUES (?, ?, ?, ?, ?, ?, ?)";
	public static final String DELETE_ORDER = "DELETE FROM Orders WHERE o_num = ?";
	public static final String SELECT_ALL_ORDERS = "SELECT * FROM Orders";
	public static final String SELECT_ORDER_BY_USER_ID = "SELECT * FROM Orders WHERE o_user = ?";
	public static final String SELECT_ORDER_BY_FILM_ID = "SELECT * FROM Orders WHERE o_film = ?";
	
	public static MySQLOrderDAO getInstance() {
		return instance;
	}

	@Override
	public List<Order> getOrdersByUserId(int id) throws DAOException {
		List<Order> orderList = new ArrayList<Order>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_ORDER_BY_USER_ID);
			st.setString(1, String.valueOf(id));
			rs = st.executeQuery();
			
			while (rs.next()) {
				Order order = new Order();
				order.setOrdNum(rs.getInt(1));
				order.setUserId(rs.getInt(2));
				order.setFilmId(rs.getInt(3));
				order.setDate(rs.getDate(4));
				order.setTime(rs.getTime(5));
				order.setPrice(rs.getInt(6));
				order.setDiscount(rs.getInt(7));
				order.setPayment(rs.getFloat(8));
	
				orderList.add(order);
			}
			
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_INSERT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.RS_OR_STATEMENT_NOT_CLOSED);
			} finally {
				if (con != null) { pool.closeConnection(con); }
			}
		}
		return orderList;
	}

	@Override
	public List<Order> getOrdersByFilmId(int id) throws DAOException {
		List<Order> orderList = new ArrayList<Order>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_ORDER_BY_FILM_ID);
			st.setString(1, String.valueOf(id));
			rs = st.executeQuery();
			
			while (rs.next()) {
				Order order = new Order();
				order.setOrdNum(rs.getInt(1));
				order.setUserId(rs.getInt(2));
				order.setFilmId(rs.getInt(3));
				order.setDate(rs.getDate(4));
				order.setTime(rs.getTime(5));
				order.setPrice(rs.getInt(6));
				order.setDiscount(rs.getInt(7));
				order.setPayment(rs.getFloat(8));
	
				orderList.add(order);
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
				if (con != null) { pool.closeConnection(con); }
			}
		}
		return orderList;
	}

	@Override
	public List<Order> getAllOrders() throws DAOException {
		List<Order> orderList = new ArrayList<Order>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_ALL_ORDERS);
			rs = st.executeQuery();
			
			while (rs.next()) {
				Order order = new Order();
				order.setOrdNum(rs.getInt(1));
				order.setUserId(rs.getInt(2));
				order.setFilmId(rs.getInt(3));
				order.setDate(rs.getDate(4));
				order.setTime(rs.getTime(5));
				order.setPrice(rs.getInt(6));
				order.setDiscount(rs.getInt(7));
				order.setPayment(rs.getFloat(8));
				
				orderList.add(order);
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
				if (con != null) { pool.closeConnection(con); }
			}
		}
		return orderList;
	}

	@Override
	public void addOrder(Order order) throws DAOException {
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(INSERT_NEW_ORDER);
			st.setInt(1, order.getUserId());
			st.setInt(2, order.getFilmId());
			st.setDate(3, order.getDate());
			st.setTime(4, order.getTime());
			st.setInt(5, order.getPrice());
			st.setInt(6, order.getDiscount());
			st.setFloat(7, order.getPayment());
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
	public void deleteOrder(Order order) throws DAOException {
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(DELETE_ORDER);
			st.setString(1, String.valueOf(order.getOrdNum()));
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
	
	
}
