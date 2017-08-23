package com.bonus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonus.bean.Equity;
import com.bonus.bean.User;
import com.bonus.dao.EquityDao;
import com.bonus.dao.UserDao;
import com.bonus.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userdao;
	@Autowired
	private EquityDao equitydao;

	@Transactional
	public User loginValidate(String name, String pwd) {
		return userdao.loginValidate(name, pwd);
	}

	@Transactional
	public void changePwd(User u) {
		userdao.updateUser(u);
		
	}

	@Transactional
	public int getEvent(User u) {
		Integer role = u.getRole();
		String r = role.toString();
		if(r.equals("3"))return 0;
		int event = equitydao.getEventByRole(r);
		return event;
	}

}
