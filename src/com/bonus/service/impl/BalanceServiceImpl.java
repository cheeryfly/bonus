package com.bonus.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonus.bean.Balance;
import com.bonus.bean.Director;
import com.bonus.bean.Equity;
import com.bonus.bean.QueryResult;
import com.bonus.dao.BalanceDao;
import com.bonus.dao.DirectorDao;
import com.bonus.dao.EquityDao;
import com.bonus.service.BalanceService;

@Service
public class BalanceServiceImpl implements BalanceService {

	@Autowired
	private EquityDao equitydao;
	@Autowired
	private BalanceDao balancedao;
	@Autowired
	private DirectorDao directordao;
	
	@Transactional
	public void initiateBalance(Equity e){
		/**
		 * 从2017年1月开始第一期
		 */
		int year = 2017;
		int month = 1;
		while(checkLastDate(year, month)){
			createOrUpdateBalace(year, month);
			createOrUpdateBalaceEnd(year, month);
			//进入下个月
			year = nextYear(year, month);
			month = nextMonth(month);
		}
		
		//计算所长奖金
		int dir_count = e.getDir_count();
		int dir1_id = e.getDir1_id();
		BigDecimal dir1_amount = e.getDir1_amount();
		int dir2_id = e.getDir2_id();
		BigDecimal dir2_amount = e.getDir2_amount();
		directordao.calculateBonus(dir1_id, dir1_amount);
		directordao.calculateBonus(dir2_id, dir2_amount);
		if(dir_count == 3){
			int dir3_id = e.getDir3_id();
			BigDecimal dir3_amount = e.getDir3_amount();
			directordao.calculateBonus(dir3_id, dir3_amount);
		}
	}
	
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
		List<Object> eos = results.getResult();
		BigDecimal equity = new BigDecimal(0);
		BigDecimal pro_bonus = new BigDecimal(0);
		BigDecimal expense = new BigDecimal(0);
		BigDecimal dir_bonus = new BigDecimal(0);
		int count = 0;
		for(Object o : eos){
			Equity detail = (Equity)o;
			equity = equity.add(detail.getEquity()).setScale(2, BigDecimal.ROUND_HALF_UP);
			pro_bonus = pro_bonus.add(detail.getPro_bonus_amount()).setScale(2, BigDecimal.ROUND_HALF_UP);
			expense = expense.add(detail.getExpense_amount()).setScale(2, BigDecimal.ROUND_HALF_UP);
			dir_bonus = dir_bonus.add(detail.getDir_amount()).setScale(2, BigDecimal.ROUND_HALF_UP);
			count++;
		}
		//查询balance
		Balance b = balancedao.queryBalance(department, year, month, "0");
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
			ba.setType("0");
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

	public Date getLastDay(Date date) {
		// 创建日历
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1); // 加一个月
		calendar.set(Calendar.DATE, 1); // 设置为该月第一天
		calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
		return calendar.getTime();
	}

	public Date getFirstDay(Date date) {
		// 创建日历
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	public boolean checkLastDate(int year, int month){
		Date today = new Date();
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(today);
		int last_year = cal.get(Calendar.YEAR);
		int last_month = cal.get(Calendar.MONTH)+1;
		if(year < last_year){
			return true;
		}
		else{
			if(year == last_year && month <= last_month) return true;
		}
		return false;
	}
	
	public int nextYear(int year, int month){
		if(month == 12){
			year++;
			return year;
		}
		return year;
	}
	
	public int nextMonth(int month){
		if(month == 12) return 1;
		month++;
		return month;
	}
	
	public int lastYear(int year, int month){
		if(month == 1) {
			year--;
			return year;
		}
		return year;
	}
	
	public int lastMonth(int month){
		if(month == 1) return 12;
		month--;
		return month;
	}
	public void createOrUpdateBalace(int year, int month){
		SimpleDateFormat sdf = sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sdate = year +"-"+month+"-01";
		Date ad = null;
		try{
			ad = sdf.parse(sdate);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		Equity e = new Equity();
		e.setAccount_date(ad);
		e.setDepartment("一所");
		createBalance(e);
		e.setDepartment("二所");
		createBalance(e);
		e.setDepartment("三所");
		createBalance(e);
		e.setDepartment("四所");
		createBalance(e);
		e.setDepartment("五所");
		createBalance(e);
	}
	
	public void createOrUpdateBalaceEnd(int year, int month){
		createBalanceEnd(year, month, "一所");
		createBalanceEnd(year, month, "二所");
		createBalanceEnd(year, month, "三所");
		createBalanceEnd(year, month, "四所");
		createBalanceEnd(year, month, "五所");
		
		
	}
	
	private void createBalanceEnd(int year, int month, String department){
		int lastYear = lastYear(year, month);
		int lastMonth = lastMonth(month);
		Balance lastEnd = balancedao.queryBalance(department, lastYear, lastMonth, "1");
		Balance b = balancedao.queryBalance(department, year, month, "0");
		Balance end = balancedao.queryBalance(department, year, month, "1");
		if(end == null){
			Balance ba = new Balance(); 
			ba.setDepartment(department);
			ba.setEquity(lastEnd.getEquity().add(b.getEquity()));
			ba.setExpense(lastEnd.getExpense().add(b.getExpense()));
			ba.setDir_bonus(lastEnd.getDir_bonus().add(b.getDir_bonus()));
			ba.setPro_bonus(lastEnd.getPro_bonus().add(b.getPro_bonus()));
			ba.setYear(year);
			ba.setMonth(month);
			ba.setCount(0);
			ba.setType("1");
			balancedao.createBalance(ba);
		}else {
			end.setEquity(lastEnd.getEquity().add(b.getEquity()));
			end.setExpense(lastEnd.getExpense().add(b.getExpense()));
			end.setDir_bonus(lastEnd.getDir_bonus().add(b.getDir_bonus()));
			end.setPro_bonus(lastEnd.getPro_bonus().add(b.getPro_bonus()));
			balancedao.updateBalance(b);
		}
	}
}
