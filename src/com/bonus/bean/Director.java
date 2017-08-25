package com.bonus.bean;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="director",schema="bonus")
public class Director {
	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="department")
	private String department;
	@Column(name="title")
	private String title;
	
	@Column(name="bonus_total")
	private BigDecimal bonus_total;
	@Column(name="bonus_draw")
	private BigDecimal bonus_draw;
	@Column(name="bonus_surplus")
	private BigDecimal bonus_surplus;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BigDecimal getBonus_total() {
		return bonus_total;
	}
	public void setBonus_total(BigDecimal bonus_total) {
		this.bonus_total = bonus_total;
	}
	public BigDecimal getBonus_draw() {
		return bonus_draw;
	}
	public void setBonus_draw(BigDecimal bonus_draw) {
		this.bonus_draw = bonus_draw;
	}
	public BigDecimal getBonus_surplus() {
		return bonus_surplus;
	}
	public void setBonus_surplus(BigDecimal bonus_surplus) {
		this.bonus_surplus = bonus_surplus;
	}
}
