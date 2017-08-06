package com.bonus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonus.bean.Equity;
import com.bonus.bean.QueryResult;
import com.bonus.dao.EquityDao;
import com.bonus.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private EquityDao equitydao;
	
	@Transactional
	public QueryResult reportDetail(int start, int length, Equity e) {
		return equitydao.reportDetail(start, length, e);
	}

	@Transactional
	public QueryResult reportBonus(int start, int length, Equity e) {
		return equitydao.reportBonus(start, length, e);
	}
	


	

}
