package com.bonus.dao;

import com.bonus.bean.Balance;
import com.bonus.bean.QueryResult;

public interface BalanceDao {
	public Balance queryBalance(String department, int year, int month, String type);
	public int createBalance(Balance b);
	public void updateBalance(Balance b);
	public QueryResult reportBalance(int start, int length, Balance b);
}
