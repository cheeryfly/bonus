package com.bonus.service;
import com.bonus.bean.Balance;
import com.bonus.bean.Equity;
import com.bonus.bean.QueryResult;
public interface ReportService {

	public QueryResult reportDetail(int start, int length, Equity e);
	public QueryResult reportBonus(int start, int length, Equity e);
	public QueryResult reportBalance(int start, int length, Balance b);
}
