package com.bonus.dao;

import com.bonus.bean.Balance;

public interface BalanceDao {
	Balance queryBalance(String department, int year, int month);
	int createBalance(Balance b);
	void updateBalance(Balance b);
}
