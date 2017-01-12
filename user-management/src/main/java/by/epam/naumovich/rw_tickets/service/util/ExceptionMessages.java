package by.epam.naumovich.rw_tickets.service.util;

/**
 * Defines a set of String constants that describe occurred exceptions in the service layer classes
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 */
public final class ExceptionMessages {

	public static final String ALREADY_TAKEN_LOGIN = "This login is already taken! Please, choose another one.";
	public static final String BIRTHDATE_RIGHT_FORMAT = "BirthDate must follow \"YYYY-MM-DD\" format";
	public static final String CORRUPTED_INPUT_PARAMETERS = "Corrupted input parameters! Please, try again.";
	public static final String CORRUPTED_LOGIN = "The login is corrupted. Sorry!";
	public static final String CORRUPTED_LOGIN_OR_PWD = "Corrupted login or password. Sorry!";
	public static final String CORRUPTED_NAME_SURN_SEX = "At least one of the next fields is corrupted or empty: name, surname, sex.";
	public static final String CORRUPTED_PAGE_NUM = "Corrupted page number! Please, try again.";
	public static final String CORRUPTED_USER_ID = "Corrupted user ID! Please, try again.";
	public static final String COUNTRIES_NOT_AVAILABLE = "No countries available in the database! Please, try again.";
	public static final String EMAIL_NOT_REGISTRATED = "No such email registrated! Pleasre, try again.";
	public static final String INVALID_BAN_LENGTH = "Ban length must be positive integer number. Please, try again.";
	public static final String INVALID_BIRTHDATE = "The year must exceed %s and the date can not exceed today";
	public static final String INVALID_EMAIL = "The e-mail you entered is not valid. Please, try again.";
	public static final String INVALID_LOGIN = "Login must start with the letter and consist at least 3 symbols (latin letters and numbers)";
	public static final String INVALID_PASSWORD = "Password must be at least 4 symbols";
	public static final String INVALID_PHONE_NUM = "Phone number must contain %s numbers";
	public static final String LOGIN_NOT_REGISTRATED = "No such login registrated. Please, try again!";
	public static final String SOURCE_ERROR = "The error occured in the data source!";
	public static final String UNSUCCESSFULL_SIGN_UP = "User was not successfully registered. Sorry.";
	public static final String USER_NOT_FOUND = "No user found by this ID. Sorry!";
	public static final String WRONG_PASSWORD = "Wrong password! Please, try again.";
	public static final String NO_SUCH_USER = "No such user found in the data source";
	public static final String INVALID_INPUT_PARAMS = "Invalid input parameters passed into method.";
	public static final String USER_NOT_ADDED = "User was not sucessfully added. Please, try again.";
	public static final String USER_NOT_UPDATED = "User was not sucessfully updated. Please, try again.";
	public static final String GROUP_NOT_FOUND = "UserGroup was not found in the data source";
	
	private ExceptionMessages() {}
}
