package com.bonus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonus.bean.Equity;
import com.bonus.dao.EquityDao;
import com.bonus.service.QueryService;

@Service
public class QueryServiceImpl implements QueryService {
	@Autowired
	private EquityDao equitydao;

	@Transactional
	public List<Equity> queryEquities(Equity e) {
		return equitydao.queryEquities(e);
	}
	
	@Transactional
	public void updateEquity(Equity e){
		equitydao.updateEquity(e);
	}

}
