package com.bonus.service;

import java.util.List;

import com.bonus.bean.Equity;

public interface QueryService {
	public List<Equity> queryEquities(Equity e);
	public void updateEquity(Equity e);
}
