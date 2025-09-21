package vn.buoi2.daos;

import vn.buoi2.models.User;
import vn.buoi2.models.UserModel;

public interface UserDao {
	UserModel findByUserName(String username);
	void insert(User user);
	 boolean checkExistEmail(String email);
	 boolean checkExistUsername(String username);
	 boolean checkExistPhone(String phone);
}
