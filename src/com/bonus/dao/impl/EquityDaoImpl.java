package com.bonus.dao.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bonus.bean.Equity;
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

}
