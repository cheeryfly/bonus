package com.bonus.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonus.bean.Balance;
import com.bonus.bean.Equity;
import com.bonus.bean.QueryResult;
import com.bonus.dao.BalanceDao;
import com.bonus.dao.EquityDao;
import com.bonus.service.BalanceService;

@Service
public class BalanceServiceImpl implements BalanceService {

	@Autowired
	private EquityDao equitydao;
	@Autowired
	private BalanceDao balancedao;
	
	@Transactional
	public void createBalance(Equity e) {
		// 汇总
		String department = e.getDepartment();
		int year = e.getAccount_date().getYear()+1900;
		int month = e.getAccount_date().getMonth()+1;
		//SimpleDateFormat sdf = sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = getFirstDay(e.getAccount_date());
		Date end = getLastDay(e.getAccount_date());
		Equity query = new Equity();
		query.setDepartment(department);
		query.setAccount_date(start);
		query.setRec_date(end);
		QueryResult results = equitydao.reportDetail(0, 9999999, query);
		List<Equity> eos = results.getResult();
		BigDecimal equity = new BigDecimal(0);
		BigDecimal pro_bonus = new BigDecimal(0);
		BigDecimal expense = new BigDecimal(0);
		BigDecimal dir_bonus = new BigDecimal(0);
		int count = 0;
		for(Equity detail : eos){
			equity.add(detail.getEquity()).setScale(2, BigDecimal.ROUND_HALF_UP);
			pro_bonus.add(detail.getPro_bonus_amount()).setScale(2, BigDecimal.ROUND_HALF_UP);
			expense.add(detail.getExpense_amount()).setScale(2, BigDecimal.ROUND_HALF_UP);
			dir_bonus.add(detail.getDir_amount()).setScale(2, BigDecimal.ROUND_HALF_UP);
			count++;
		}
		//查询balance
		Balance b = balancedao.queryBalance(department, year, month);
		if(b == null) {
			Balance ba = new Balance(); 
			ba.setDepartment(department);
			ba.setEquity(equity);
			ba.setExpense(expense);
			ba.setDir_bonus(dir_bonus);
			ba.setPro_bonus(pro_bonus);
			ba.setYear(year);
			ba.setMonth(month);
			ba.setCount(count);
			balancedao.createBalance(ba);
		}else {
			b.setCount(count);
			b.setEquity(equity);
			b.setExpense(expense);
			b.setDir_bonus(dir_bonus);
			b.setPro_bonus(pro_bonus);
			balancedao.updateBalance(b);
		}
	}

	 public Date getLastDay(Date date){
	        //创建日历
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.add(Calendar.MONTH, 1);    //加一个月
	        calendar.set(Calendar.DATE, 1);     //设置为该月第一天
	        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
	        return calendar.getTime();
	    }
	    
	    public Date getFirstDay(Date date){
	        //创建日历
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.set(Calendar.DAY_OF_MONTH, 1);
	        return calendar.getTime();
	    }
}
