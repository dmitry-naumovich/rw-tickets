package by.epam.naumovich.rw_tickets.service.util;

import by.epam.naumovich.rw_tickets.entity.GroupRequest;
import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.entity.UserGroup;

public final class Validator {

	public static boolean validateIds(int... ids) {
		for (int id : ids) {
			if (id <= 0) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean validateStrings(String... strings) {
		for (String str : strings) {
			if (str == null || str.isEmpty()) {
				return false;
			}	
		}
		return true;
	}
	
	public static boolean validateNewUserGroup(UserGroup group) {
		if (group == null) {
			return false;
		}
		if (!validateIds(group.getOwner_id()) || !validateStrings(group.getGr_name())) {
			return false;
		}
		return true;
	}
	
	public static boolean validateExistingUserGroup(UserGroup group) {
		if (!validateNewUserGroup(group)) {
			return false;
		}
		if (!validateIds(group.getGr_id()) ||  group.getCreateDate() == null || group.getCreateTime() == null) {
			return false;
		}
		return true;
	}
	
	public static boolean validateNewRequest(GroupRequest req) {
		if (req == null) {
			return false;
		}
		
		if (!validateIds(req.getFrom_user(), req.getTo_user(), req.getGr_id())) {
			return false;
		}
		return true;
	}
	
	public static boolean validateExistingRequest(GroupRequest req) {
		if (!validateNewRequest(req)) {
			return false;
		}
		if (req.getCreateDate() == null || req.getCreateTime() == null || !validateRequestStatus(req.getStatus())) {
			return false;
		}
		return true;
	}
	
	public static boolean validateRequestStatus(char status) {
		if (status != 'c' && status != 'o' && status != 'a') {
			return false;
		}
		return true;
	}
	
	public static boolean validateNewUser(User user) {
		if (user == null) {
			return false;
		}
		if (!validateStrings(user.getEmail(), user.getLogin(), user.getFname(), user.getSname(), user.getPassport(), user.getPwd())) {
			return false;
		}
		return true;
	}
	
	public static boolean validateExistingUser(User user) {
		if (!validateNewUser(user)) {
			return false;
		}
		if (!validateIds(user.getId())) {
			return false;
		}
		return true;
	}
	
	private Validator() { };
}
