package by.epam.naumovich.film_ordering.service.util;

public final class ExceptionMessages {

	private ExceptionMessages() {}
	
	public static final String ALREADY_TAKEN_LOGIN = "This login is already taken! Please, choose another one.";
	public static final String BIRTHDATE_RIGHT_FORMAT = "BirthDate must follow \"YYYY-MM-DD\" format";
	public static final String CORRUPTED_NAME_SURN_SEX = "At least one of the next fields is corrupted or empty: name, surname, sex.";
	public static final String INVALID_EMAIL = "The e-mail you entered is not valid. Please, try again.";
	public static final String INVALID_LOGIN = "Login must start with the letter and consist at least 3 symbols (latin letters and numbers)";
	public static final String INVALID_PASSWORD = "Password must be at least 4 symbols";
	public static final String SOURCE_ERROR = "The error occured in the data source!";
	public static final String UNSUCCESSFULL_SIGN_UP = "User was not successfully registered. Sorry.";
	

}
