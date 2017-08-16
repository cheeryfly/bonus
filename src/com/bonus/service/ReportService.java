package com.bonus.service;
import java.io.OutputStream;
import java.util.Date;

import com.bonus.bean.Balance;
import com.bonus.bean.Equity;
import com.bonus.bean.QueryResult;

import jxl.write.WritableWorkbook;
public interface ReportService {

	public QueryResult reportDetail(int start, int length, Equity e);
	public QueryResult reportBonus(int start, int length, Equity e);
	public QueryResult reportBalance(int start, int length, Balance b);
	
	public WritableWorkbook downladBonus(String department, Date start_date, Date end_date, OutputStream os)throws Exception;
	public WritableWorkbook downladBalance(String department, int year, int month, OutputStream os)throws Exception;
}
