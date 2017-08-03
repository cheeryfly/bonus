package com.bonus.action;
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
import com.bonus.bean.QueryResult;
import com.bonus.bean.User;
import com.bonus.service.ReportService;

@Controller
public class ReportAction {
	@Autowired
	private ReportService reportService;
	@RequestMapping("/report/detail")
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
			
			String page = map.get("page")==null?null:(String)map.get("page");
			String department = map.get("department")==null?null:(String)map.get("department");
			String type = map.get("type")==null?null:(String)map.get("type");
			String account_date_st = map.get("account_date_start")==null?null:(String)map.get("account_date_start");
			String account_date_en = map.get("account_date_end")==null?null:(String)map.get("account_date_end");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  
			Date account_date_start=null;
			if(account_date_st != null){
				account_date_start = sdf.parse(account_date_st);
			}
			Date account_date_end=null;
			if(account_date_en != null){
				account_date_end = sdf.parse(account_date_en);
			}
			Equity query = new Equity();
			int start = (Integer.parseInt(page)-1) * 10;
			query.setDepartment(department);
			query.setType(type);
			query.setAccount_date(account_date_start);
			query.setRec_date(account_date_end);
			
			
			QueryResult results = reportService.reportDetail(1, query);
			if (results.getTotalAmount() == 0 || results.getResult() == null) {
				repStr = ActionUtil.getResponse("500", "未查询到信息");
			} else {
				StringBuffer data = new StringBuffer();
				data.append("\"total\":" + results.getTotalAmount() + ","+"\"amount\":" + results.getResult().size() + ","
						+ "\"equitiesList\":" + JSON.toJSONString(results.getResult()));
				repStr = ActionUtil.getResponse("200", "操作成功", data.toString());
			}
	    }
	    catch (Exception e) {
			e.printStackTrace();
			repStr=ActionUtil.getResponse("500", "提交过程出错！");
	    }

	    ActionUtil.sendJSONToClient(repStr, response);
	}
	
	@RequestMapping("/report/test")
	public void queryTest(HttpServletRequest request,HttpServletResponse response, String json){
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
			
			String page = map.get("page")==null?null:(String)map.get("page");
			String department = map.get("department")==null?null:(String)map.get("department");
			String type = map.get("type")==null?null:(String)map.get("type");
			String account_date_st = map.get("account_date_start")==null?null:(String)map.get("account_date_start");
			String account_date_en = map.get("account_date_end")==null?null:(String)map.get("account_date_end");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  
			Date account_date_start=null;
			if(account_date_st != null){
				account_date_start = sdf.parse(account_date_st);
			}
			Date account_date_end=null;
			if(account_date_en != null){
				account_date_end = sdf.parse(account_date_en);
			}
			Equity query = new Equity();
			int start = (Integer.parseInt(page)-1) * 10;
			query.setDepartment(department);
			query.setType(type);
			query.setAccount_date(account_date_start);
			query.setRec_date(account_date_end);
			
			
			QueryResult results = reportService.reportDetail(1, query);
			if (results.getTotalAmount() == 0 || results.getResult() == null) {
				repStr = ActionUtil.getResponse("500", "未查询到信息");
			} else {
				StringBuffer data = new StringBuffer();
				data.append("\"total\":" + results.getTotalAmount() + ","+"\"amount\":" + results.getResult().size() + ","
						+ "\"equitiesList\":" + JSON.toJSONString(results.getResult()));
				/*{
				    "draw": 1,
				    "recordsTotal": 57,
				    "recordsFiltered": 57,
				    "data": [
				        {
				            "name":"Angelica",
				            "age":"Ramos",
				            "office":"System Architect",
				            "address":"London",
				            "date":"9th Oct 09",
				            "salary":"$2,875"
				        },
				        {
				            "name":"Ashton",
				            "age":"Cox",
				            "office":"Technical Author",
				            "address":"San Francisco",
				            "date":"12th Jan 09",
				            "salary":"$4,800"
				        },
				        {
				            "name":"Tom",
				            "age":"28",
				            "office":"Technical Author",
				            "address":"San Francisco",
				            "date":"12th Jan 09",
				            "salary":"$3,800"
				        },
				    ]
				}*/
				repStr = ActionUtil.getResponse("200", "操作成功", data.toString());
			}
	    }
	    catch (Exception e) {
			e.printStackTrace();
			repStr=ActionUtil.getResponse("500", "提交过程出错！");
	    }

	    ActionUtil.sendJSONToClient(repStr, response);
	}
}
