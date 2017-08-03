package com.bonus.dao;

import java.util.List;

import com.bonus.bean.Equity;
import com.bonus.bean.QueryResult;

public interface EquityDao {
	public int createEquity(Equity e);
	public List<Equity> queryEquities(Equity e);
	public void updateEquity(Equity e);
	
	public QueryResult reportDetail(int page, Equity e);
}
