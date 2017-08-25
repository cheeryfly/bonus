package com.bonus.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="equity_detail",schema="bonus")
public class Equity {
	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;
	@Column(name="department")
	private String department;
	@Column(name="rec_date")
	private Date rec_date;
	@Column(name="rec_employee")
	private String rec_employee;
	@Column(name="check_date")
	private Date check_date;
	@Column(name="check_employee")
	private String check_employee;
	@Column(name="status")
	private String status;
	@Column(name="type")
	private String type;
	@Column(name="account_date")
	private Date account_date;
	@Column(name="account_item")
	private String account_item;
	@Column(name="cardno")
	private String cardno;
	@Column(name="income")
	private BigDecimal income;
	@Column(name="account_rate")
	private BigDecimal account_rate;
	@Column(name="prize_rate")
	private BigDecimal prize_rate;
	@Column(name="dir_count")
	private Integer dir_count;
	@Column(name="card_discount")
	private BigDecimal card_discount;
	@Column(name="pro_bonus_rate")
	private BigDecimal pro_bonus_rate;
	@Column(name="pro_bonus_amount")
	private BigDecimal pro_bonus_amount;
	@Column(name="dir_rate")
	private BigDecimal dir_rate;
	@Column(name="dir_amount")
	private BigDecimal dir_amount;
	@Column(name="expense_rate")
	private BigDecimal expense_rate;
	@Column(name="expense_amount")
	private BigDecimal expense_amount;
	@Column(name="equity")
	private BigDecimal equity;
	@Column(name="remark")
	private String remark;
	@Column(name="pro_id")
	private String pro_id;
	@Column(name="dir1_id")
	private Integer dir1_id;
	@Column(name="dir1_name")
	private String dir1_name;
	@Column(name="dir1_rate")
	private BigDecimal dir1_rate;
	@Column(name="dir1_amount")
	private BigDecimal dir1_amount;
	@Column(name="dir2_id")
	private Integer dir2_id;
	@Column(name="dir2_name")
	private String dir2_name;
	@Column(name="dir2_rate")
	private BigDecimal dir2_rate;
	@Column(name="dir2_amount")
	private BigDecimal dir2_amount;
	@Column(name="dir3_id")
	private Integer dir3_id;
	@Column(name="dir3_name")
	private String dir3_name;
	@Column(name="dir3_rate")
	private BigDecimal dir3_rate;
	@Column(name="dir3_amount")
	private BigDecimal dir3_amount;
	
	
	
	
	
	
	public String getCheck_remark() {
		return check_remark;
	}
	public void setCheck_remark(String check_remark) {
		this.check_remark = check_remark;
	}
	@Column(name="check_remark")
	private String check_remark;
	public String getPro_id() {
		return pro_id;
	}
	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
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
	public Date getRec_date() {
		return rec_date;
	}
	public void setRec_date(Date rec_date) {
		this.rec_date = rec_date;
	}
	public String getRec_employee() {
		return rec_employee;
	}
	public void setRec_employee(String rec_employee) {
		this.rec_employee = rec_employee;
	}
	public Date getCheck_date() {
		return check_date;
	}
	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}
	public String getCheck_employee() {
		return check_employee;
	}
	public void setCheck_employee(String check_employee) {
		this.check_employee = check_employee;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getAccount_date() {
		return account_date;
	}
	public void setAccount_date(Date account_date) {
		this.account_date = account_date;
	}
	public String getAccount_item() {
		return account_item;
	}
	public void setAccount_item(String account_item) {
		this.account_item = account_item;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public BigDecimal getIncome() {
		return income;
	}
	public void setIncome(BigDecimal income) {
		this.income = income;
	}
	public BigDecimal getAccount_rate() {
		return account_rate;
	}
	public void setAccount_rate(BigDecimal account_rate) {
		this.account_rate = account_rate;
	}
	public BigDecimal getPrize_rate() {
		return prize_rate;
	}
	public void setPrize_rate(BigDecimal prize_rate) {
		this.prize_rate = prize_rate;
	}
	public Integer getDir_count() {
		return dir_count;
	}
	public void setDir_count(Integer dir_count) {
		this.dir_count = dir_count;
	}
	public BigDecimal getCard_discount() {
		return card_discount;
	}
	public void setCard_discount(BigDecimal card_discount) {
		this.card_discount = card_discount;
	}
	public BigDecimal getPro_bonus_rate() {
		return pro_bonus_rate;
	}
	public void setPro_bonus_rate(BigDecimal pro_bonus_rate) {
		this.pro_bonus_rate = pro_bonus_rate;
	}
	public BigDecimal getPro_bonus_amount() {
		return pro_bonus_amount;
	}
	public void setPro_bonus_amount(BigDecimal pro_bonus_amount) {
		this.pro_bonus_amount = pro_bonus_amount;
	}
	public BigDecimal getDir_rate() {
		return dir_rate;
	}
	public void setDir_rate(BigDecimal dir_rate) {
		this.dir_rate = dir_rate;
	}
	public BigDecimal getDir_amount() {
		return dir_amount;
	}
	public void setDir_amount(BigDecimal dir_amount) {
		this.dir_amount = dir_amount;
	}
	public BigDecimal getExpense_rate() {
		return expense_rate;
	}
	public void setExpense_rate(BigDecimal expense_rate) {
		this.expense_rate = expense_rate;
	}
	public BigDecimal getExpense_amount() {
		return expense_amount;
	}
	public void setExpense_amount(BigDecimal expense_amount) {
		this.expense_amount = expense_amount;
	}
	public BigDecimal getEquity() {
		return equity;
	}
	public void setEquity(BigDecimal equity) {
		this.equity = equity;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getDir1_id() {
		return dir1_id;
	}
	public void setDir1_id(Integer dir1_id) {
		this.dir1_id = dir1_id;
	}
	public String getDir1_name() {
		return dir1_name;
	}
	public void setDir1_name(String dir1_name) {
		this.dir1_name = dir1_name;
	}
	public BigDecimal getDir1_rate() {
		return dir1_rate;
	}
	public void setDir1_rate(BigDecimal dir1_rate) {
		this.dir1_rate = dir1_rate;
	}
	public BigDecimal getDir1_amount() {
		return dir1_amount;
	}
	public void setDir1_amount(BigDecimal dir1_amount) {
		this.dir1_amount = dir1_amount;
	}
	public Integer getDir2_id() {
		return dir2_id;
	}
	public void setDir2_id(Integer dir2_id) {
		this.dir2_id = dir2_id;
	}
	public String getDir2_name() {
		return dir2_name;
	}
	public void setDir2_name(String dir2_name) {
		this.dir2_name = dir2_name;
	}
	public BigDecimal getDir2_rate() {
		return dir2_rate;
	}
	public void setDir2_rate(BigDecimal dir2_rate) {
		this.dir2_rate = dir2_rate;
	}
	public BigDecimal getDir2_amount() {
		return dir2_amount;
	}
	public void setDir2_amount(BigDecimal dir2_amount) {
		this.dir2_amount = dir2_amount;
	}
	public Integer getDir3_id() {
		return dir3_id;
	}
	public void setDir3_id(Integer dir3_id) {
		this.dir3_id = dir3_id;
	}
	public String getDir3_name() {
		return dir3_name;
	}
	public void setDir3_name(String dir3_name) {
		this.dir3_name = dir3_name;
	}
	public BigDecimal getDir3_rate() {
		return dir3_rate;
	}
	public void setDir3_rate(BigDecimal dir3_rate) {
		this.dir3_rate = dir3_rate;
	}
	public BigDecimal getDir3_amount() {
		return dir3_amount;
	}
	public void setDir3_amount(BigDecimal dir3_amount) {
		this.dir3_amount = dir3_amount;
	}



}
