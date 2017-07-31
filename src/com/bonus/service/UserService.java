package com.bonus.service;

import com.bonus.bean.User;

public interface UserService {
	public User loginValidate(String name, String pwd);
}