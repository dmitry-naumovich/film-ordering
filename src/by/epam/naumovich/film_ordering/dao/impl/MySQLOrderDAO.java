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
	
	public static final String INSERT_NEW_ORDER = "INSERT INTO Orders (o_user, o_film, o_date, o_time, o_fprice, o_discount, o_paym) VALUES (?, ?, ?, ?, ?, ?, ?)";
	public static final String DELETE_ORDER = "DELETE FROM Orders WHERE o_num = ?";
	public static final String SELECT_ALL_ORDERS = "SELECT * FROM Orders";
	public static final String SELECT_ORDER_BY_ORDER_NUM = "SELECT * FROM Orders WHERE o_num = ?";
	public static final String SELECT_ORDER_BY_USER_AND_FILM_ID = "SELECT * FROM Orders WHERE o_user = ? AND o_film = ?";
	public static final String SELECT_ORDER_BY_USER_ID = "SELECT * FROM Orders WHERE o_user = ?";
	public static final String SELECT_ORDER_BY_FILM_ID = "SELECT * FROM Orders WHERE o_film = ?";
	public static final String SELECT_ORDER_NUM_BY_USER_AND_FILM_ID = "SELECT o_num FROM Orders WHERE o_user = ? AND o_film = ?";
	
	public static MySQLOrderDAO getInstance() {
		return instance;
	}

	@Override
	public int addOrder(Order order) throws DAOException {
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		PreparedStatement st2 = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(INSERT_NEW_ORDER);
			st.setInt(1, order.getUserId());
			st.setInt(2, order.getFilmId());
			st.setDate(3, order.getDate());
			st.setTime(4, order.getTime());
			st.setFloat(5, order.getPrice());
			st.setInt(6, order.getDiscount());
			st.setFloat(7, order.getPayment());
			st.executeUpdate();
			
			st2 = con.prepareStatement(SELECT_ORDER_NUM_BY_USER_AND_FILM_ID);
			st2.setInt(1, order.getUserId());
			st2.setInt(2, order.getFilmId());
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
	public void deleteOrder(int orderNum) throws DAOException {
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(DELETE_ORDER);
			st.setInt(1, orderNum);
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
	public Order getOrderByOrderNum(int orderNum) throws DAOException {
		Order order = new Order();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_ORDER_BY_ORDER_NUM);
			st.setInt(1, orderNum);
			rs = st.executeQuery();
			
			if (rs.next()) {
				order.setOrdNum(rs.getInt(1));
				order.setUserId(rs.getInt(2));
				order.setFilmId(rs.getInt(3));
				order.setDate(rs.getDate(4));
				order.setTime(rs.getTime(5));
				order.setPrice(rs.getFloat(6));
				order.setDiscount(rs.getInt(7));
				order.setPayment(rs.getFloat(8));
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
		return order;
	}
	
	@Override
	public Order getOrderByUserAndFilmId(int userID, int filmID) throws DAOException {
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_ORDER_BY_USER_AND_FILM_ID);
			st.setInt(1, userID);
			st.setInt(2, filmID);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Order order = new Order();
				order.setOrdNum(rs.getInt(1));
				order.setUserId(rs.getInt(2));
				order.setFilmId(rs.getInt(3));
				order.setDate(rs.getDate(4));
				order.setTime(rs.getTime(5));
				order.setPrice(rs.getFloat(6));
				order.setDiscount(rs.getInt(7));
				order.setPayment(rs.getFloat(8));
				return order;
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
		return null;
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
				order.setPrice(rs.getFloat(6));
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
				order.setPrice(rs.getFloat(6));
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
				order.setPrice(rs.getFloat(6));
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
}