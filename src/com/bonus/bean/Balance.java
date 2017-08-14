package com.bonus.bean;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="equity_balance",schema="bonus")
public class Balance {
	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;
	@Column(name="department")
	private String department;
	@Column(name="year")
	private Integer year;
	@Column(name="month")
	private Integer month;
	@Column(name="equity")
	private BigDecimal equity;
	@Column(name="pro_bonus")
	private BigDecimal pro_bonus;
	@Column(name="expense")
	private BigDecimal expense;
	@Column(name="dir_bonus")
	private BigDecimal dir_bonus;
	@Column(name="count")
	private Integer count;
	@Column(name="type")
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public BigDecimal getEquity() {
		return equity;
	}
	public void setEquity(BigDecimal equity) {
		this.equity = equity;
	}
	public BigDecimal getPro_bonus() {
		return pro_bonus;
	}
	public void setPro_bonus(BigDecimal pro_bonus) {
		this.pro_bonus = pro_bonus;
	}
	public BigDecimal getExpense() {
		return expense;
	}
	public void setExpense(BigDecimal expense) {
		this.expense = expense;
	}
	public BigDecimal getDir_bonus() {
		return dir_bonus;
	}
	public void setDir_bonus(BigDecimal dir_bonus) {
		this.dir_bonus = dir_bonus;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
}
