package com.bonus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonus.bean.User;
import com.bonus.dao.UserDao;
import com.bonus.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userdao;

	@Transactional
	public User loginValidate(String name, String pwd) {
		return userdao.loginValidate(name, pwd);
	}

}
