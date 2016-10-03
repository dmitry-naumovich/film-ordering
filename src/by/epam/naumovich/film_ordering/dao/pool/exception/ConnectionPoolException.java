package by.epam.naumovich.film_ordering.dao.pool.exception;

public class ConnectionPoolException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConnectionPoolException(String msg, Exception e) {
		super(msg, e);
	}
	
	public ConnectionPoolException(Exception e) {
		super(e);
	}
	
	public ConnectionPoolException(String msg) {
		super(msg);
	}
}
