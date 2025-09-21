package vn.buoi2.services.impl;

import vn.buoi2.daos.UserDao;
import vn.buoi2.daos.impl.UserDaoImpl;
import vn.buoi2.models.User;
import vn.buoi2.models.UserModel;
import vn.buoi2.services.UserService;

public class UserServiceImpl implements UserService{

	UserDao userDao = new UserDaoImpl();
	
	@Override
	public UserModel findByUserName(String username) {		
		return userDao.findByUserName(username);
	}

	@Override
	public UserModel login(String username, String password) {
		
		UserModel user = this.findByUserName(username);
		
		if(user != null && password.equals(user.getPassWord())) {
			return user;
		}
		
		return null;
	}

	@Override
	public void insert(User user) {
		userDao.insert(user);	
	}

	@Override
	public boolean register(String email, String password, String username, String fullname, String phone) {
		 if (userDao.checkExistUsername(username)) {
			 return false;
			 }
			 long millis=System.currentTimeMillis();   
			java.sql.Date date=new java.sql.Date(millis);
			 userDao.insert(new User(email, username, fullname,password, null,5,phone,date));
			 return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		 return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		 return userDao.checkExistUsername(username);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}
	
}
