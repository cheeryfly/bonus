package com.bonus.bean;

import java.util.List;

public class QueryResult {
	private int totalAmount;
	private int page;
	private List<Equity> result;
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public List<Equity> getResult() {
		return result;
	}
	public void setResult(List<Equity> result) {
		this.result = result;
	}
	

}
