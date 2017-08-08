package com.bonus.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bonus.bean.Equity;
import com.bonus.bean.Balance;
import com.bonus.dao.BalanceDao;

@Repository
public class BalanceDaoImpl implements BalanceDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Balance queryBalance(String department, int year, int month) {
		StringBuilder sb = new StringBuilder();
		sb.append("from Balance b where 1=1");
		if(department != null){
			sb.append(" and b.department='" + department + "'");
		}
		sb.append(" and b.year='"+year);
		sb.append(" and b.month='"+month);
		Query query = sessionFactory.getCurrentSession().createQuery(sb.toString());
		query.setMaxResults(20);
		query.setFirstResult(0);
		List<Balance> result = query.list();
		if(result == null || result.size()==0) {
			return null;
		}
		return result.get(0);
	}

	public int createBalance(Balance b) {
		Integer r = (Integer)sessionFactory.getCurrentSession().save(b);
		return r.intValue();
	}

	public void updateBalance(Balance b) {
		sessionFactory.getCurrentSession().update(b);
	}

}
