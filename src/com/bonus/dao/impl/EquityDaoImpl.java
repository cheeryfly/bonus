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
import com.bonus.dao.EquityDao;

@Repository
public class EquityDaoImpl implements EquityDao {
	

	@Autowired
	private SessionFactory sessionFactory;
	
	public int createEquity(Equity e) {
		Integer r = (Integer)sessionFactory.getCurrentSession().save(e);
		return r.intValue();
	}
	
	public void updateEquity(Equity e){
		sessionFactory.getCurrentSession().update(e);
	}
	
	public List<Equity> queryEquities(Equity e){
		List<Equity> result = null;
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		sb.append("from Equity e where 1=1");
		if(e.getId() != null){
			sb.append(" and e.id="+e.getId());
		}
		if(e.getAccount_date() != null){
			sb.append(" and e.account_date='"+sdf.format(e.getAccount_date())+"'");
		}
		if(e.getAccount_item() != null){
			sb.append(" and e.account_item='"+e.getAccount_item()+"'");
		}
		if(e.getCardno() != null){
			sb.append(" and e.cardno='"+e.getCardno()+"'");
		}
		if(e.getCheck_date() != null){
			sb.append(" and e.check_date='"+sdf.format(e.getCheck_date())+"'");
		}
		if(e.getCheck_employee() != null){
			sb.append(" and e.check_employee='"+e.getCheck_employee() +"'");
		}
		if(e.getDepartment() != null){
			sb.append(" and e.department='"+e.getDepartment()+"'");
		}
		if(e.getDir_count() != null){
			sb.append(" and e.dir_count="+e.getDir_count());
		}
		if(e.getPro_id() != null){
			sb.append(" and e.pro_id='"+e.getPro_id()+"'");
		}
		if(e.getRec_date() != null){
			sb.append(" and e.rec_date='"+sdf.format(e.getRec_date())+"'");
		}
		if(e.getRec_employee() != null){
			sb.append(" and e.rec_employee='"+e.getRec_employee() +"'");
		}
		if(e.getStatus() != null){
			sb.append(" and e.status='"+e.getStatus()+"'");
		}
		if(e.getType() != null){
			sb.append(" and e.type='"+e.getType()+"'");
		}
		
		Query query = sessionFactory.getCurrentSession().createQuery(sb.toString());
		query.setMaxResults(20);
		query.setFirstResult(0);
		result = query.list();
		return result;
	}
	
	
	public QueryResult reportDetail(int start, int length, Equity e){
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String head2 = "from Equity e ";
		String head = "select count(*) from equity_detail e ";
		sb.append("where 1=1");
		if(e.getDepartment() != null && !e.getDepartment().equals("不限") && !e.getDepartment().equals("")){
			sb.append(" and e.department='"+e.getDepartment()+"'");
		}
		if(e.getType() != null && !e.getType().equals("不限") && !e.getType().equals("")){
			sb.append(" and e.type='"+e.getType()+"'");
		}
		if(e.getAccount_date() != null){
			sb.append(" and e.account_date>='"+sdf.format(e.getAccount_date())+"'");
		}
		if(e.getRec_date() != null){
			sb.append(" and e.account_date<='"+sdf.format(e.getRec_date())+"'");
		}
		sb.append(" and e.status='1'");
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

	public QueryResult reportBonus(int start, int length, Equity e) {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String head = "select count(*), sum(e.dir_amount) from equity_detail e ";
		String head2 = "from Equity e ";
		
		sb.append("where 1=1");
		if(e.getDepartment() != null && !e.getDepartment().equals("不限") && !e.getDepartment().equals("")){
			sb.append(" and e.department='"+e.getDepartment()+"'");
		}
		if(e.getAccount_date() != null){
			sb.append(" and e.account_date>='"+sdf.format(e.getAccount_date())+"'");
		}
		if(e.getRec_date() != null){
			sb.append(" and e.account_date<='"+sdf.format(e.getRec_date())+"'");
		}
		sb.append(" and e.status='1' and e.dir_amount>0");
		String sql1 = head + sb.toString();
		String sql2 = head2 + sb.toString();
		SQLQuery q= sessionFactory.getCurrentSession().createSQLQuery(sql1);
		Object[] w = (Object[])q.uniqueResult();
		BigInteger t = (BigInteger)w[0];
		int total = t.intValueExact();
		BigDecimal bonus_amount = (BigDecimal)w[1];
		Query query = sessionFactory.getCurrentSession().createQuery(sql2);
		
		query.setMaxResults(length);
		query.setFirstResult(start);
		QueryResult re= new QueryResult();
		re.setTotalAmount(total);
		re.setBonus_amount(bonus_amount);
		re.setResult(query.list());
		return re;
	}

}
