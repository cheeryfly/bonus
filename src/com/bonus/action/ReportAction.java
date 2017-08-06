package com.bonus.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

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
	public void queryDetail(HttpServletRequest request, HttpServletResponse response, String json) {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User us = (User) session.getAttribute("user");
		int role = us.getRole();

		String draw = request.getParameter("draw");
		System.out.println("draw: " + draw);
		String st = request.getParameter("start");
		int start = Integer.parseInt(st);
		System.out.println("start: " + start);
		String le = request.getParameter("length");
		int length = Integer.parseInt(le);
		System.out.println("length: " + length);
		// 获取前台额外传递过来的查询条件
		String department = request.getParameter("department");
		System.out.println("department: " + department);
		if (department.equals("不限"))
			department = null;
		String type = request.getParameter("type");
		if (type.equals(""))
			type = null;
		System.out.println("type: " + type);
		String account_date_st = request.getParameter("account_date_start");
		System.out.println("account_date_start: " + account_date_st);
		String account_date_en = request.getParameter("account_date_end");
		System.out.println("account_date_end: " + account_date_en);

		Enumeration<String> headers = request.getHeaderNames();
		String repStr = "";
		while (headers.hasMoreElements()) {
			String head = headers.nextElement();
		}
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Date account_date_start = null;
			if (account_date_st != null && account_date_st != "") {
				account_date_start = sdf.parse(account_date_st);
			}
			Date account_date_end = null;
			if (account_date_en != null && account_date_en != "") {
				account_date_end = sdf.parse(account_date_en);
			}
			Equity query = new Equity();
			query.setDepartment(department);
			query.setType(type);
			query.setAccount_date(account_date_start);
			query.setRec_date(account_date_end);

			QueryResult result = reportService.reportDetail(start, length, query);

			StringBuffer data = new StringBuffer();
			data.append("\"draw\":" + draw + "," + "\"recordsTotal\":" + result.getTotalAmount() + ","
					+ "\"recordsFiltered\":" + result.getTotalAmount() + "," + "\"data\":"
					+ JSON.toJSONString(result.getResult(), filter));
			// repStr = ActionUtil.getResponse("200", "操作成功", data.toString());
			repStr = "{" + data + "}";
			System.out.println(repStr);

		} catch (Exception e) {
			e.printStackTrace();
			repStr = ActionUtil.getResponse("500", "提交过程出错！");
		}

		ActionUtil.sendJSONToClient(repStr, response);
	}

	@RequestMapping("/report/bonus")
	public void queryBonus(HttpServletRequest request, HttpServletResponse response, String json) {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		String draw = request.getParameter("draw");
		String st = request.getParameter("start");
		int start = Integer.parseInt(st);
		String le = request.getParameter("length");
		int length = Integer.parseInt(le);
		// 获取前台额外传递过来的查询条件
		String department_qu = request.getParameter("department");
		if (department_qu.equals("不限"))
			department_qu = null;
		String account_date_st = request.getParameter("account_date_start");
		String account_date_en = request.getParameter("account_date_end");

		Enumeration<String> headers = request.getHeaderNames();
		String repStr = "";
		while (headers.hasMoreElements()) {
			String head = headers.nextElement();
		}
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Date account_date_start = null;
			if (account_date_st != null && account_date_st != "") {
				account_date_start = sdf.parse(account_date_st);
			}
			Date account_date_end = null;
			if (account_date_en != null && account_date_en != "") {
				account_date_end = sdf.parse(account_date_en);
			}
			Equity query = new Equity();
			query.setDepartment(department_qu);
			query.setAccount_date(account_date_start);
			query.setRec_date(account_date_end);

			QueryResult result = reportService.reportDetail(start, length, query);

			StringBuffer data = new StringBuffer();
			data.append("\"draw\":" + draw + "," + "\"recordsTotal\":" + result.getTotalAmount() + ","
					+ "\"recordsFiltered\":" + result.getTotalAmount() + "," + "\"data\":[");
				//+ JSON.toJSONString(result.getResult(), filter));
			// repStr = ActionUtil.getResponse("200", "操作成功", data.toString());
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			for(Equity e : result.getResult()) {
				String department = e.getDepartment()==null?"":e.getDepartment();
				String account_date = e.getAccount_date()==null?"":sdf.format(e.getAccount_date());
				String cardno = e.getCardno()==null?"":e.getCardno();
				String account_item = e.getAccount_item()==null?"":e.getAccount_item();
				String income = e.getIncome()==null?"":e.getIncome().toString();
				String account_rate = e.getAccount_rate()==null?"":e.getAccount_rate().toString();
				String prize_rate = e.getPrize_rate()==null?"":e.getPrize_rate().toString();
				int dir_count = e.getDir_count()==null?3:e.getDir_count().intValue();
				BigDecimal dir_amount = e.getDir_amount();
				BigDecimal dir_rate = e.getDir_rate();
				BigDecimal retained_amount = dir_amount.multiply(new BigDecimal(0.1)).setScale(4);
				BigDecimal prebonus_amount = dir_amount.multiply(new BigDecimal(0.9)).setScale(4);
				BigDecimal dir_amount1, dir_amount2, dir_amount3;
				if(dir_rate == null) {
					
				}else {
					
				}
				data.append("{");
				data.append("\"department\":\"" + e.getDepartment()+"\",");
				data.append("\"account_date\":\"" + sdf.format(e.getAccount_date())+"\",");
				data.append("\"cardno\":\"" + e.getCardno()+"\",");
				data.append("\"account_item\":\"" + e.getAccount_item()+"\",");
				data.append("\"income\":\"" + e.getIncome()+"\",");
				data.append("\"account_rate\":\"" + e.getAccount_rate()+"\",");
				data.append("\"prize_rate\":\"" + e.getPrize_rate()+"\",");
				if(dir_count == null) {
					data.append("\"dir_count\":\" \",");
				}else {
					data.append("\"dir_count\":\"" +dir_count +"\",");
				}
				if(dir_rate.compareTo(new BigDecimal(0))==0) {
					
				}else {
					
				}
				
				BigDecimal retained_rate = e.getDir_rate().multiply(new BigDecimal(0.1)).setScale(4);
				data.append("\"retained_rate\":\"" +retained_rate +"\",");
				BigDecimal retained_amount = e.getDir_amount().multiply(e.getDir_rate().multiply(new BigDecimal(0.1))).setScale(2);
				data.append("\"retained_amount\":\"" +retained_amount +"\",");
				BigDecimal prebonus_rate = e.getDir_rate().multiply(new BigDecimal(0.9)).setScale(4);
				data.append("\"prebonus_rate\":\"" +prebonus_rate +"\",");
				BigDecimal prebonus_amount = e.getDir_amount().multiply(e.getDir_rate().multiply(new BigDecimal(0.9)).setScale(2));
				data.append("\"prebonus_amount\":\"" +prebonus_amount +"\",");
				
				BigDecimal dir_rate1 = new BigDecimal(0);
				BigDecimal dir_amount1= new BigDecimal(0);
				BigDecimal dir_rate2= new BigDecimal(0);
				BigDecimal dir_amount2= new BigDecimal(0);
				BigDecimal dir_rate3= new BigDecimal(0);
				BigDecimal dir_amount3= new BigDecimal(0);
				if(dir_count == 3) {
					dir_amount1 = dir_amount.multiply(new BigDecimal(0.5)).setScale(2);
					dir_amount2 = dir_amount.multiply(new BigDecimal(0.25)).setScale(2);
					dir_amount3 = dir_amount.subtract(dir_amount2).setScale(2);
					if(dir_rate.compareTo(new BigDecimal(0)) != 0) {
						dir_rate1 = prebonus_rate.multiply(new BigDecimal(0.5)).setScale(4);
						dir_rate2 = prebonus_rate.multiply(new BigDecimal(0.25)).setScale(4);
						dir_rate3 = prebonus_rate.multiply(new BigDecimal(0.25)).setScale(4);
					}
				}else if(dir_count == 2) {
					dir_amount1 = dir_amount.multiply(new BigDecimal(0.667)).setScale(2);
					dir_amount2 = dir_amount.subtract(dir_amount1).setScale(2);
					if(dir_rate.compareTo(new BigDecimal(0)) != 0) {
						dir_rate1 = prebonus_rate.multiply(new BigDecimal(0.5)).setScale(4);
						dir_rate2 = prebonus_rate.multiply(new BigDecimal(0.25)).setScale(4);
					}
				}else if(dir_count ==1) {
					
					dir_amount1 = dir_amount;
					dir_rate1 = prebonus_rate;
				}
				data.append("\"dir_rate1\":\"" +dir_rate1+"\",");
				
				data.append("\"dir_amount1\":\"" + dir_amount1+"\",");
				data.append("\"dir_rate2\":\"" + dir_rate2+"\",");
				data.append("\"dir_amount2\":\"" + dir_amount2+"\",");
				data.append("\"dir_rate3\":\"" + dir_rate3+"\",");
				data.append("\"dir_amount3\":\"" + dir_amount3+"\",");
				data.append("},");
			}
			data.deleteCharAt(data.lastIndexOf(","));
			data.append("]");
			repStr = "{" + data + "}";
			System.out.println(repStr);
			/*
			 	"data":[
			 			{"account_date":"2017-07-17","account_item":"龙泉总体规划","account_rate":0.3200,"card_discount":"","cardno":"67222","check_date":"2017-07-28","check_employee":"陈苹","check_remark":"审核通过","department":"一所","dir_amount":122000.00,"dir_count":3,"dir_rate":0.0500,"equity":754400.00,"expense_amount":120000.00,"expense_rate":0.0600,"id":2,"income":2000000.00,"prize_rate":1.2200,"pro_bonus_amount":512400.00,"pro_bonus_rate":0.2100,"pro_id":"ZG8901","rec_date":"2017-07-19","rec_employee":"陈苹","remark":"运行卡测试","status":"1","type":"运行卡"}
			 			,
			 			{"account_date":"2017-07-01","account_item":"慢行交通规划","account_rate":0.3200,"card_discount":2.2000,"cardno":"782221","check_date":"","check_employee":"","check_remark":"","department":"四所","dir_amount":21780.00,"dir_count":3,"dir_rate":0.0500,"equity":113256.00,"expense_amount":0.00,"expense_rate":0.0600,"id":3,"income":990000.00,"prize_rate":2.6400,"pro_bonus_amount":91476.00,"pro_bonus_rate":0.2100,"pro_id":"","rec_date":"2017-07-19","rec_employee":"陈苹","remark":"结算卡测试入账","status":"1","type":"结算卡"}
			 			,
			 			{"account_date":"2017-07-05","account_item":"灯光专项规划","account_rate":"","card_discount":"","cardno":"","check_date":"2017-07-30","check_employee":"陈苹","check_remark":"未填写要素","department":"五所","dir_amount":87000.00,"dir_count":"","dir_rate":"","equity":87000.00,"expense_amount":0.00,"expense_rate":"","id":4,"income":"","prize_rate":"","pro_bonus_amount":0.00,"pro_bonus_rate":"","pro_id":"","rec_date":"2017-07-20","rec_employee":"陈苹","remark":"所长奖金补发","status":"1","type":"成本报账"}
			 		   ]

			 */

		} catch (Exception e) {
			e.printStackTrace();
			repStr = ActionUtil.getResponse("500", "提交过程出错！");
		}

		ActionUtil.sendJSONToClient(repStr, response);
	}

	private static ValueFilter filter = new ValueFilter() {
		@Override
		public Object process(Object obj, String s, Object v) {

			if (v == null)
				return "";

			if (v instanceof String) {
				String value = (String) v;
				if (s.equals("type")) {
					if (value.equals("1")) {
						System.out.println("运行卡");
						return "运行卡";
					}
					if (value.equals("2"))
						return "结算卡";
					if (value.equals("3"))
						return "其他入账";
					if (value.equals("11"))
						return "提取项目奖金";
					if (value.equals("12"))
						return "提取所长奖金";
					if (value.equals("13"))
						return "成本报账";
					if (value.equals("14"))
						return "冲预发";
					if (value.equals("15"))
						return "其他出账";
				}
				if (value.equals("undefined"))

					return "";

			}
			if (v instanceof Date) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				return sdf.format(v);
			}
			return v;
		}
	};
}
