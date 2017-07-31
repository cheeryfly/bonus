package com.bonus.dao;

import java.util.List;

import com.bonus.bean.Equity;

public interface EquityDao {
	public int createEquity(Equity e);
	public List<Equity> queryEquities(Equity e);
	public void updateEquity(Equity e);
}
