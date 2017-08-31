package com.bonus.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bonus.bean.Director;
import com.bonus.dao.DirectorDao;
@Repository
public class DirectorDaoImpl implements DirectorDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Director> queryDirectors(Director d) {
		List<Director> result = null;
		StringBuilder sb = new StringBuilder();
		sb.append("from Director d where 1=1");
		if(d.getId() != null){
			sb.append(" and d.id="+d.getId());
		}
		if(d.getDepartment() != null){
			sb.append(" and d.department='"+d.getDepartment()+"'");
		}
		if(d.getName() != null){
			sb.append(" and d.name='"+d.getName()+"'");
		}
		if(d.getTitle() != null){
			sb.append(" and d.title='"+d.getTitle()+"'");
		}
		sb.append(" order by d.title");
		
		Query query = sessionFactory.getCurrentSession().createQuery(sb.toString());
		query.setMaxResults(50);
		query.setFirstResult(0);
		result = query.list();
		return result;
	}

	public void updateDirector(Director d) {
		sessionFactory.getCurrentSession().update(d);
	}
	
	public void calculateBonus(int id, BigDecimal amount){
		Director d = new Director();
		d.setId(id);
		d = queryDirectors(d).get(0);
		BigDecimal surplus = d.getBonus_surplus();
		BigDecimal total = d.getBonus_total();
		BigDecimal draw = d.getBonus_draw();
		if(amount.compareTo(new BigDecimal(0)) == -1){
			draw = draw.add(amount);
		}
		if(amount.compareTo(new BigDecimal(0)) == 1){
			total = total.add(amount);
		}
		surplus = surplus.add(amount);
		d.setBonus_draw(draw);
		d.setBonus_surplus(surplus);
		d.setBonus_total(total);
		updateDirector(d);
	}

}
