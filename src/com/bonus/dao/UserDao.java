package com.bonus.dao;

import com.bonus.bean.User;

public interface UserDao {
	public User loginValidate(String name, String pwd);
	public void updateUser(User u);

}
