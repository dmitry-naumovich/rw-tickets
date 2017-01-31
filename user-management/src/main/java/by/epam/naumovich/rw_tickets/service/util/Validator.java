package by.epam.naumovich.rw_tickets.service.util;

import by.epam.naumovich.rw_tickets.entity.GroupRequest;
import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.entity.UserGroup;

/**
 * Contains static methods which are responsible for validating input values on service layer.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public final class Validator {

	private Validator() { };
	
	/**
	 * Validates integer values that all of them are positive.
	 * 
	 * @param ids - the undefined amount of integer values
	 * @return true if all values are correct and false otherwise
	 */
	public static boolean validateIds(int... ids) {
		for (int id : ids) {
			if (id <= 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Validates String objects that all of them are not empty or null.
	 * 
	 * @param strings - the undefined amount of String values
	 * @return true if all values are correct and false otherwise
	 */
	public static boolean validateStrings(String... strings) {
		for (String str : strings) {
			if (str == null || str.isEmpty()) {
				return false;
			}	
		}
		return true;
	}
	
	/**
	 * Validates new (i.e. it's not present in DB yet) UserGroup object that it is not null and all its fields comply with the application bounds. 
	 * 
	 * @param group - the UserGroup object to be validated
	 * @return true if the object comply with the bounds and false otherwise
	 */
	public static boolean validateNewUserGroup(UserGroup group) {
		if (group == null) {
			return false;
		}
		if (!validateIds(group.getOwner_id()) || !validateStrings(group.getGr_name())) {
			return false;
		}
		return true;
	}
	
	/**
	 * Validates existing (i.e. it's already present in DB) UserGroup object that it is not null and all its fields comply with the application bounds.
	 * 
	 * @param group - the UserGroup object to be validated
	 * @return true if the object comply with the bounds and false otherwise
	 */
	public static boolean validateExistingUserGroup(UserGroup group) {
		if (!validateNewUserGroup(group)) {
			return false;
		}
		if (!validateIds(group.getGr_id()) ||  group.getCreateDate() == null || group.getCreateTime() == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Validates new (i.e. it's not present in DB yet) GroupRequest object that it is not null and all its fields comply with the application bounds. 
	 * 
	 * @param req - the GroupRequest object to be validated
	 * @return true if the object comply with the bounds and false otherwise
	 */
	public static boolean validateNewRequest(GroupRequest req) {
		if (req == null) {
			return false;
		}
		
		if (!validateIds(req.getFrom_user(), req.getTo_user(), req.getGr_id())) {
			return false;
		}
		return true;
	}
	
	/**
	 * Validates existing (i.e. it's already present in DB) GroupRequest object that it is not null and all its fields comply with the application bounds.
	 * 
	 * @param req - the GroupRequest object to be validated
	 * @return true if the object comply with the bounds and false otherwise
	 */
	public static boolean validateExistingRequest(GroupRequest req) {
		if (!validateNewRequest(req)) {
			return false;
		}
		if (req.getCreateDate() == null || req.getCreateTime() == null || !validateRequestStatus(req.getStatus())) {
			return false;
		}
		return true;
	}
	
	/**
	 * Validates GroupRequest's status char value that it complies with the application bounds
	 * 
	 * @param status - the char value to be validated
	 * @return true if status comply with the bounds and false otherwise
	 */
	public static boolean validateRequestStatus(char status) {
		if (status != 'c' && status != 'o' && status != 'a') {
			return false;
		}
		return true;
	}
	
	/**
	 * Validates new (i.e. it's not present in DB yet) User object that it is not null and all its fields comply with the application bounds. 
	 * 
	 * @param user - the User object to be validated
	 * @return true if the object comply with the bounds and false otherwise
	 */
	public static boolean validateNewUser(User user) {
		if (user == null) {
			return false;
		}
		if (!validateStrings(user.getEmail(), user.getLogin(), user.getFname(), user.getSname(), user.getPassport(), user.getPwd())) {
			return false;
		}
		return true;
	}
	
	/**
	 * Validates existing (i.e. it's already present in DB) User object that it is not null and all its fields comply with the application bounds.
	 * 
	 * @param user - the User object to be validated
	 * @return true if the object comply with the bounds and false otherwise
	 */
	public static boolean validateExistingUser(User user) {
		if (!validateNewUser(user)) {
			return false;
		}
		if (!validateIds(user.getId())) {
			return false;
		}
		return true;
	}
}