package com.bonus.bean;

import java.math.BigDecimal;
import java.util.List;

public class QueryResult {
	public BigDecimal getBonus_amount() {
		return bonus_amount;
	}
	public void setBonus_amount(BigDecimal bonus_amount) {
		this.bonus_amount = bonus_amount;
	}
	private int totalAmount;
	private List<Object> result;
	private BigDecimal bonus_amount;
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<Object> getResult() {
		return result;
	}
	public void setResult(List<Object> result) {
		this.result = result;
	}
	

}
