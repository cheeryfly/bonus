package com.bonus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonus.bean.Equity;
import com.bonus.dao.EquityDao;
import com.bonus.service.CreateService;

@Service
public class CreateServiceImpl implements CreateService {
	@Autowired
	private EquityDao equitydao;

	@Transactional
	public int createIncome(Equity e){
		return equitydao.createEquity(e);
	}
	

}
