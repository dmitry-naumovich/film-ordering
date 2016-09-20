package by.epam.naumovich.film_ordering.dao.util;

/**
 * Defines a set of String constants that describe occurred exceptions in the DAO layer classes
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 */
public final class ExceptionMessages {

	private ExceptionMessages() {}
	
	public static final String CONNECTION_NOT_TAKEN = "Failure during taking connection from the Connection Pool";
	public static final String PREP_STATEMENT_NOT_CLOSED = "Prepared Statement was not closed properly";
	public static final String RS_OR_STATEMENT_NOT_CLOSED = "Result Set or Statement was not closed properly";
	public static final String SQL_DELETE_FAILURE = "Failure during the SQL delete request execution";
	public static final String SQL_INSERT_FAILURE = "Failure during the SQL insert request execution";
	public static final String SQL_SELECT_FAILURE = "Failure during the SQL select request execution";
	public static final String SQL_SHOW_FAILURE = "Failure during the SQL show request execution";
	public static final String SQL_UPDATE_FAILURE = "Failure during the SQL update request execution";
	public static final String UNKNOWN_DATA_SOURCE = "Unknown data source type. Please, try again.";
	
}
