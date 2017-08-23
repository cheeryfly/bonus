package com.bonus.action;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.bonus.bean.Equity;
import com.bonus.bean.User;
import com.bonus.service.CreateService;

@Controller
public class CreateAction {
	@Autowired
	private CreateService createService;
	
	@RequestMapping("/runningcard/save")
	public void createRunningCard(HttpServletRequest request,HttpServletResponse response, String json){
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User us = (User)session.getAttribute("user");
		String userName = us.getNickname();
		
		
		Enumeration<String> headers=request.getHeaderNames();
		String repStr="";
		while(headers.hasMoreElements()){
			String head=headers.nextElement();
		}
	    try{
			if(json==null || json.equals("")) {
				repStr = ActionUtil.getResponse("500", "网络传输错误！");
			}
			
			Map map = (Map) JSON.parse(json);
			if(map==null) {
				map = new HashMap();
			}
			
			
			String department = map.get("department")==null?null:(String)map.get("department");
			String account_date_st = map.get("account_date")==null?null:(String)map.get("account_date");
			String account_item = map.get("account_item")==null?null:(String)map.get("account_item");
			String pro_id = map.get("pro_id")==null?null:(String)map.get("pro_id");
			String cardno = map.get("cardno")==null?null:(String)map.get("cardno");
			BigDecimal income = map.get("income")==null?null:new BigDecimal((String)map.get("income"));
			BigDecimal account_rate = map.get("account_rate")==null?null:new BigDecimal((String)map.get("account_rate"));
			BigDecimal prize_rate = map.get("prize_rate")==null?null:new BigDecimal((String)map.get("prize_rate"));
			Integer dir_count = map.get("dir_count")==null?null:new Integer((String)map.get("dir_count"));
			String remark = map.get("remark")==null?null:(String)map.get("remark");
			BigDecimal equity = map.get("equity")==null?null:new BigDecimal((String)map.get("equity"));
			BigDecimal pro_bonus_amount = map.get("pro_bonus_amount")==null?null:new BigDecimal((String)map.get("pro_bonus_amount"));
			BigDecimal pro_bonus_rate = map.get("pro_bonus_rate")==null?null:new BigDecimal((String)map.get("pro_bonus_rate"));
			BigDecimal expense_amount = map.get("expense_amount")==null?null:new BigDecimal((String)map.get("expense_amount"));
			BigDecimal expense_rate = map.get("expense_rate")==null?null:new BigDecimal((String)map.get("expense_rate"));
			BigDecimal dir_amount = map.get("dir_amount")==null?null:new BigDecimal((String)map.get("dir_amount"));
			BigDecimal dir_rate = map.get("dir_rate")==null?null:new BigDecimal((String)map.get("dir_rate"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  
			System.out.println(account_date_st);
			Date account_date=null;
			if(account_date_st != null){
				account_date = sdf.parse(account_date_st);
			}
			Date rec_date = new Date();
			Equity eq = new Equity();
			eq.setAccount_date(account_date);
			eq.setAccount_item(account_item);
			eq.setAccount_rate(account_rate);
			eq.setCardno(cardno);
			eq.setDepartment(department);
			eq.setDir_amount(dir_amount);
			eq.setDir_count(dir_count);
			eq.setDir_rate(dir_rate);
		//	eq.setCard_discount(card_discount);
			eq.setEquity(equity);
			eq.setExpense_amount(expense_amount);
			eq.setExpense_rate(expense_rate);
			eq.setIncome(income);
			eq.setPrize_rate(prize_rate);
			eq.setPrize_rate(prize_rate);
			eq.setPro_bonus_amount(pro_bonus_amount);
			eq.setPro_bonus_rate(pro_bonus_rate);
			eq.setRec_date(rec_date);
			eq.setRec_employee(userName);
			eq.setRemark(remark);
			eq.setPro_id(pro_id);
			eq.setStatus("0"); //草稿
			eq.setType("1"); //运行卡
			
			
	    
			int id = createService.createIncome(eq);
			System.out.println("新建运行卡入账明细，id ："+id);
			
			StringBuffer data = new StringBuffer();
			data.append("\"id\":" + id);
			repStr = ActionUtil.getResponse("200", "操作成功", data.toString());
	    }
	    catch (Exception e) {
			e.printStackTrace();
			repStr=ActionUtil.getResponse("500", "提交过程出错！");
	    }
	    ActionUtil.sendJSONToClient(repStr, response);
	}
	
	@RequestMapping("/settlement/save")
	public void createSettlement(HttpServletRequest request,HttpServletResponse response, String json){
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User us = (User)session.getAttribute("user");
		String userName = us.getNickname();
		
		
		Enumeration<String> headers=request.getHeaderNames();
		String repStr="";
		while(headers.hasMoreElements()){
			String head=headers.nextElement();
		}
	    try{
			if(json==null || json.equals("")) {
				repStr = ActionUtil.getResponse("500", "网络传输错误！");
			}
			
			Map map = (Map) JSON.parse(json);
			if(map==null) {
				map = new HashMap();
			}
			
			
			String department = map.get("department")==null?null:(String)map.get("department");
			String account_date_st = map.get("account_date")==null?null:(String)map.get("account_date");
			String account_item = map.get("account_item")==null?null:(String)map.get("account_item");
			String pro_id = map.get("pro_id")==null?null:(String)map.get("pro_id");
			String cardno = map.get("cardno")==null?null:(String)map.get("cardno");
			BigDecimal income = map.get("income")==null?null:new BigDecimal((String)map.get("income"));
			BigDecimal account_rate = map.get("account_rate")==null?null:new BigDecimal((String)map.get("account_rate"));
			BigDecimal prize_rate = map.get("prize_rate")==null?null:new BigDecimal((String)map.get("prize_rate"));
			Integer dir_count = map.get("dir_count")==null?null:new Integer((String)map.get("dir_count"));
			BigDecimal card_discount = map.get("card_discount")==null?null:new BigDecimal((String)map.get("card_discount"));
			String remark = map.get("remark")==null?null:(String)map.get("remark");
			BigDecimal equity = map.get("equity")==null?null:new BigDecimal((String)map.get("equity"));
			BigDecimal pro_bonus_amount = map.get("pro_bonus_amount")==null?null:new BigDecimal((String)map.get("pro_bonus_amount"));
			BigDecimal pro_bonus_rate = map.get("pro_bonus_rate")==null?null:new BigDecimal((String)map.get("pro_bonus_rate"));
			BigDecimal expense_amount = map.get("expense_amount")==null?null:new BigDecimal((String)map.get("expense_amount"));
			BigDecimal expense_rate = map.get("expense_rate")==null?null:new BigDecimal((String)map.get("expense_rate"));
			BigDecimal dir_amount = map.get("dir_amount")==null?null:new BigDecimal((String)map.get("dir_amount"));
			BigDecimal dir_rate = map.get("dir_rate")==null?null:new BigDecimal((String)map.get("dir_rate"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  
			Date account_date=null;
			if(account_date_st != null){
				account_date = sdf.parse(account_date_st);
			}
			Date rec_date = new Date();
			Equity eq = new Equity();
			eq.setAccount_date(account_date);
			eq.setAccount_item(account_item);
			eq.setAccount_rate(account_rate);
			eq.setCardno(cardno);
			eq.setDepartment(department);
			eq.setDir_amount(dir_amount);
			eq.setDir_count(dir_count);
			eq.setDir_rate(dir_rate);
			eq.setCard_discount(card_discount);
			eq.setEquity(equity);
			eq.setExpense_amount(expense_amount);
			eq.setExpense_rate(expense_rate);
			eq.setIncome(income);
			eq.setPrize_rate(prize_rate);
			eq.setPrize_rate(prize_rate);
			eq.setPro_bonus_amount(pro_bonus_amount);
			eq.setPro_bonus_rate(pro_bonus_rate);
			eq.setRec_date(rec_date);
			eq.setRec_employee(userName);
			eq.setRemark(remark);
			eq.setPro_id(pro_id);
			eq.setStatus("0"); //草稿
			eq.setType("2"); //结算卡
			
			
	    
			int id = createService.createIncome(eq);
			System.out.println("新建结算卡入账明细，id ："+id);
			StringBuffer data = new StringBuffer();
			data.append("\"id\":" + id);
			repStr = ActionUtil.getResponse("200", "操作成功", data.toString());
	    }
	    catch (Exception e) {
			e.printStackTrace();
			repStr=ActionUtil.getResponse("500", "提交过程出错！");
	    }
	    ActionUtil.sendJSONToClient(repStr, response);
	}
	
	@RequestMapping("/other/save")
	public void createOtherIncome(HttpServletRequest request,HttpServletResponse response, String json){
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User us = (User)session.getAttribute("user");
		String userName = us.getNickname();
		
		
		Enumeration<String> headers=request.getHeaderNames();
		String repStr="";
		while(headers.hasMoreElements()){
			String head=headers.nextElement();
		}
	    try{
			if(json==null || json.equals("")) {
				repStr = ActionUtil.getResponse("500", "网络传输错误！");
			}
			
			Map map = (Map) JSON.parse(json);
			if(map==null) {
				map = new HashMap();
			}
			
			
			String department = map.get("department")==null?null:(String)map.get("department");
			String type = map.get("type")==null?null:(String)map.get("type");
			String account_date_st = map.get("account_date")==null?null:(String)map.get("account_date");
			String account_item = map.get("account_item")==null?null:(String)map.get("account_item");
			String pro_id = map.get("pro_id")==null?null:(String)map.get("pro_id");
			Integer dir_count = map.get("dir_count")==null?null:new Integer((String)map.get("dir_count"));
			String remark = map.get("remark")==null?null:(String)map.get("remark");
			BigDecimal equity = map.get("equity")==null?null:new BigDecimal((String)map.get("equity"));
			BigDecimal pro_bonus_amount = map.get("pro_bonus_amount")==null?null:new BigDecimal((String)map.get("pro_bonus_amount"));
			BigDecimal expense_amount = map.get("expense_amount")==null?null:new BigDecimal((String)map.get("expense_amount"));
			BigDecimal dir_amount = map.get("dir_amount")==null?null:new BigDecimal((String)map.get("dir_amount"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  
			Date account_date=null;
			if(account_date_st != null){
				account_date = sdf.parse(account_date_st);
			}
			Date rec_date = new Date();
			Equity eq = new Equity();
			eq.setAccount_date(account_date);
			eq.setAccount_item(account_item);
			eq.setDepartment(department);
			eq.setDir_amount(dir_amount);
			eq.setEquity(equity);
			eq.setExpense_amount(expense_amount);
			eq.setPro_bonus_amount(pro_bonus_amount);
			eq.setRec_date(rec_date);
			eq.setRec_employee(userName);
			eq.setRemark(remark);
			eq.setType(type);
			eq.setDir_count(dir_count);
			eq.setStatus("0"); //草稿
			
			
	    
			int id = createService.createIncome(eq);
			System.out.println("新建其他入账明细，id ："+id);

			StringBuffer data = new StringBuffer();
			data.append("\"id\":" + id);
			repStr = ActionUtil.getResponse("200", "操作成功", data.toString());
	    }
	    catch (Exception e) {
			e.printStackTrace();
			repStr=ActionUtil.getResponse("500", "提交过程出错！");
	    }
	    ActionUtil.sendJSONToClient(repStr, response);
	}

}
