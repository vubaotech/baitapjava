package vn.buoi2.services;

import vn.buoi2.models.User;
import vn.buoi2.models.UserModel;

public interface UserService {
	
	void insert(User user);
	
	UserModel login(String username, String password);
	
	boolean register(String email, String password, String username, 
			String fullname, String phone);
	
	 boolean checkExistEmail(String email);
	 boolean checkExistUsername(String username);
	 boolean checkExistPhone(String phone);
	
	UserModel findByUserName(String username);
}
