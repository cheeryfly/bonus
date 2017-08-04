package com.bonus.action;
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
import com.alibaba.fastjson.serializer.ValueFilter;
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
		
		String draw = request.getParameter("draw");
		System.out.println("draw: "+draw);
		String st = request.getParameter("start");
		int start = Integer.parseInt(st);
		System.out.println("start: "+start);
		String le = request.getParameter("length");
		int length = Integer.parseInt(le);
		System.out.println("length: "+length);
		// 获取前台额外传递过来的查询条件
		String department = request.getParameter("department");
		System.out.println("department: "+department);
		if(department.equals("不限")) department=null;
		String type = request.getParameter("type");
		if(type.equals("")) type=null;
		System.out.println("type: "+type);
		String account_date_st = request.getParameter("account_date_start");
		System.out.println("account_date_start: "+account_date_st);
		String account_date_en = request.getParameter("account_date_end");
		System.out.println("account_date_end: "+account_date_en);
	        
		Enumeration<String> headers=request.getHeaderNames();
		String repStr="";
		while(headers.hasMoreElements()){
			String head=headers.nextElement();
		}
	    try{
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  
			Date account_date_start=null;
			if(account_date_st != null && account_date_st != ""){
				account_date_start = sdf.parse(account_date_st);
			}
			Date account_date_end=null;
			if(account_date_en != null && account_date_en != ""){
				account_date_end = sdf.parse(account_date_en);
			}
			Equity query = new Equity();
			query.setDepartment(department);
			query.setType(type);
			query.setAccount_date(account_date_start);
			query.setRec_date(account_date_end);
			
			
			QueryResult result = reportService.reportDetail(start, length, query);

				StringBuffer data = new StringBuffer();
				data.append("\"draw\":" + draw + ","+"\"recordsTotal\":" + result.getTotalAmount() + ","+"\"recordsFiltered\":" + result.getTotalAmount() + ","	
				  + "\"data\":" + JSON.toJSONString(result.getResult(), filter) );
				//repStr = ActionUtil.getResponse("200", "操作成功", data.toString());
				repStr = "{" + data +"}";
				System.out.println(repStr);

			}
	    catch (Exception e) {
			e.printStackTrace();
			repStr=ActionUtil.getResponse("500", "提交过程出错！");
	    }

	    ActionUtil.sendJSONToClient(repStr, response);
	}
	
	private static ValueFilter filter = new ValueFilter() {
	    @Override
	    public Object process(Object obj, String s, Object v) {
	    	
	    	
	    if(v==null)
	        return "";
	    
	    if(v instanceof String){
	    	String value= (String)v;
		    if(s.equals("type")){
		    	if(value.equals("1")){
		    		System.out.println("运行卡");
		    		return "运行卡";
		    	}
		    	if(value.equals("2"))return "结算卡";
		    	if(value.equals("3"))return "其他入账";
		    	if(value.equals("11"))return "提取项目奖金";
		    	if(value.equals("12"))return "提取所长奖金";
		    	if(value.equals("13"))return "成本报账";
		    	if(value.equals("14"))return "冲预发";
		    	if(value.equals("15"))return "其他出账";
		    }
	    	if(value.equals("undefined"))

	    	return "";

	    	}
	    if(v instanceof Date){
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        return sdf.format(v);  
	    }
	    return v;
	    }
	};
}
