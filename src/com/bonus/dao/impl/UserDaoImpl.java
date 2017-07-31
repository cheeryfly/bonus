package com.bonus.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bonus.bean.User;
import com.bonus.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	

	public User getUserByName(String name) {
		List<User> result = sessionFactory.getCurrentSession().createQuery("from User u where u.name='"+name+"'").list();
		if(result!=null && result.size()>0) return result.get(0);
		return null;
	}

	public void deleteUser(Integer id) {
		User user = (User) sessionFactory.getCurrentSession().load(User.class, id);
		if(null != user){
			 sessionFactory.getCurrentSession().delete(user);
		}
	}

	public User loginValidate(String name, String pwd) {
		List<User> result = sessionFactory.getCurrentSession().createQuery("from User u where u.username='"+name+"' and u.password='"+pwd+"'").list();
		if(result!=null && result.size()>0) return result.get(0);
		return null;
	}

}
