package com.bonus.action;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
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
import com.bonus.service.QueryService;

@Controller
public class CheckAction {
	@Autowired
	private QueryService queryService;
	@RequestMapping("/check/query")
	public void queryDetail(HttpServletRequest request,HttpServletResponse response, String json){
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User us = (User)session.getAttribute("user");
		int role = us.getRole();
		
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
			
			String id = map.get("id")==null?null:(String)map.get("id");
			String department = map.get("department")==null?null:(String)map.get("department");
			String type = map.get("type")==null?null:(String)map.get("type");
			String status = map.get("status")==null?null:(String)map.get("status");
			String account_date_st = map.get("account_date")==null?null:(String)map.get("account_date");
			String account_item = map.get("account_item")==null?null:(String)map.get("account_item");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  
			Date account_date=null;
			if(account_date_st != null){
				account_date = sdf.parse(account_date_st);
			}
			Equity eq = new Equity();
			if(id!= null && id!=""){
				eq.setId(Integer.parseInt(id));
			}
			eq.setAccount_date(account_date);
			eq.setAccount_item(account_item);
			eq.setDepartment(department);
			eq.setType(type);
			eq.setStatus(status); 
			
			
	    
			List<Equity> results = queryService.queryEquities(eq);
			if (results == null || results.isEmpty()) {
				repStr = ActionUtil.getResponse("500", "未查询到待审批信息");
			} else {
				StringBuffer data = new StringBuffer();
				data.append("\"amount\":" + results.size() + ","
						+ "\"equitiesList\":" + JSON.toJSONString(results));
				repStr = ActionUtil.getResponse("200", "操作成功", data.toString());
			}
	    }
	    catch (Exception e) {
			e.printStackTrace();
			repStr=ActionUtil.getResponse("500", "提交过程出错！");
	    }
	    if(role == 0){
	    	repStr=ActionUtil.getResponse("500", "请用管理员身份审批！");
	    }
	    ActionUtil.sendJSONToClient(repStr, response);
	}
	
	@RequestMapping("/check/submit")
	public void checkSubmit(HttpServletRequest request,HttpServletResponse response, String json){
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User us = (User)session.getAttribute("user");
		int role = us.getRole();
		
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
			
			String id = map.get("id")==null?null:(String)map.get("id");
			String action = map.get("action")==null?null:(String)map.get("action");
			String check_remark = map.get("check_remark")==null?null:(String)map.get("check_remark");
			Date check_date = new Date();
			String check_emp = us.getNickname();
			Equity eq = new Equity();
			eq.setId(Integer.parseInt(id));
			eq = queryService.queryEquities(eq).get(0);
			eq.setCheck_date(check_date);
			eq.setCheck_employee(check_emp);
			eq.setStatus(action); 
			eq.setCheck_remark(check_remark);
			queryService.updateEquity(eq);
			StringBuffer data = new StringBuffer();
			data.append("\"result\":" + "1" );
			repStr = ActionUtil.getResponse("200", "操作成功", data.toString());
			System.out.println("审核完成！");
	    }
	    catch (Exception e) {
			e.printStackTrace();
			repStr=ActionUtil.getResponse("500", "审核过程出错！");
	    }
	    if(role == 0){
	    	repStr=ActionUtil.getResponse("500", "请用管理员身份审批！");
	    }
	    ActionUtil.sendJSONToClient(repStr, response);
	}
	
	@RequestMapping("/runningcard/edit")
	public void editSubmit(HttpServletRequest request,HttpServletResponse response, String json){
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User us = (User)session.getAttribute("user");
		int role = us.getRole();
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
			
			String id = map.get("id")==null?null:(String)map.get("id");
			Equity eq = new Equity();
			eq.setId(Integer.parseInt(id));
			eq = queryService.queryEquities(eq).get(0);
			
			String department = map.get("department")==null?null:(String)map.get("department");
			String account_date_st = map.get("account_date")==null?null:(String)map.get("account_date");
			String account_item = map.get("account_item")==null?null:(String)map.get("account_item");
			String project_id = map.get("project_id")==null?null:(String)map.get("project_id");
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
			Date account_date=null;
			if(account_date_st != null){
				account_date = sdf.parse(account_date_st);
			}
			Date rec_date = new Date();
			eq.setAccount_date(account_date);
			eq.setAccount_item(account_item);
			eq.setAccount_rate(account_rate);
			eq.setCardno(cardno);
			eq.setDepartment(department);
			eq.setDir_amount(dir_amount);
			eq.setDir_count(dir_count);
			eq.setDir_rate(dir_rate);
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
			eq.setPro_id(project_id);
			eq.setStatus("0"); //草稿
			eq.setType("1"); //运行卡
			queryService.updateEquity(eq);
			StringBuffer data = new StringBuffer();
			data.append("\"result\":" + "1" );
			repStr = ActionUtil.getResponse("200", "操作成功", data.toString());
			System.out.println("修改完成！");
	    }
	    catch (Exception e) {
			e.printStackTrace();
			repStr=ActionUtil.getResponse("500", "修改过程出错！");
	    }
	    ActionUtil.sendJSONToClient(repStr, response);
	}
}
