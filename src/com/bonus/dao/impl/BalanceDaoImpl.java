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
import com.bonus.bean.QueryResult;
import com.bonus.bean.Balance;
import com.bonus.dao.BalanceDao;

@Repository
public class BalanceDaoImpl implements BalanceDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Balance queryBalance(String department, int year, int month, String type) {
		StringBuilder sb = new StringBuilder();
		sb.append("from Balance b where 1=1");
		if(department != null){
			sb.append(" and b.department='" + department + "'");
		}
		if(year>0)
		sb.append(" and b.year="+year);
		if(month>0)
		sb.append(" and b.month="+month);
		sb.append(" and b.type='"+type+ "'");
		sb.append(" order by b.department, b.year, b.month");
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
	
	public QueryResult reportBalance(int start, int length, Balance b){
		StringBuilder sb = new StringBuilder();
		String head = "select count(*)  from equity_balance b ";
		String head2 = "from Balance b ";
		
		sb.append("where 1=1");
		if(b.getDepartment() != null && !b.getDepartment().equals("不限") && !b.getDepartment().equals("")){
			sb.append(" and b.department='"+b.getDepartment()+"'");
		}
		if(b.getYear() > 0){
			sb.append(" and b.year ="+ b.getYear());
		}
		if(b.getMonth() > 0){
			sb.append(" and b.month="+b.getMonth());
		}
		sb.append(" order by b.department, b.year, b.month, b.type");
		String sql1 = head + sb.toString();
		String sql2 = head2 + sb.toString();
		SQLQuery q= sessionFactory.getCurrentSession().createSQLQuery(sql1);
		BigInteger t = (BigInteger)q.uniqueResult();
		int total = t.intValueExact();
		Query query = sessionFactory.getCurrentSession().createQuery(sql2);
		
		query.setMaxResults(length);
		query.setFirstResult(start);
		QueryResult re= new QueryResult();
		re.setTotalAmount(total);
		re.setResult(query.list());
		return re;
	}


}
