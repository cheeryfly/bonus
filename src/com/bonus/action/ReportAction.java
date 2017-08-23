package com.bonus.action;

import java.io.OutputStream;
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
import com.bonus.bean.Balance;
import com.bonus.bean.Equity;
import com.bonus.bean.QueryResult;
import com.bonus.bean.User;
import com.bonus.service.BalanceService;
import com.bonus.service.ReportService;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Controller
public class ReportAction {
	@Autowired
	private ReportService reportService;
	@Autowired
	private BalanceService balanceService;

	@RequestMapping("/report/detail")
	public void queryDetail(HttpServletRequest request, HttpServletResponse response, String json) {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User us = (User) session.getAttribute("user");
		int role = us.getRole();

		String draw = request.getParameter("draw");
		String st = request.getParameter("start");
		int start = Integer.parseInt(st);
		String le = request.getParameter("length");
		int length = Integer.parseInt(le);
		// 获取前台额外传递过来的查询条件
		String department = request.getParameter("department");
		if (department.equals("不限"))
			department = null;
		if(role == 3){
			department = us.getNickname();
		}
		String type = request.getParameter("type");
		if (type.equals(""))
			type = null;
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
			query.setDepartment(department);
			query.setType(type);
			query.setAccount_date(account_date_start);
			query.setRec_date(account_date_end);

			QueryResult result = reportService.reportDetail(start, length, query);

			StringBuffer data = new StringBuffer();
			data.append("\"draw\":" + draw + "," + "\"recordsTotal\":" + result.getTotalAmount() + ","
					+ "\"recordsFiltered\":" + result.getTotalAmount() + "," + "\"data\":"
					+ JSON.toJSONString(result.getResult(), filter));
			System.out.println(data.toString());
			// repStr = ActionUtil.getResponse("200", "操作成功", data.toString());
			repStr = "{" + data + "}";

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
		User us = (User) session.getAttribute("user");
		int role = us.getRole();
		String draw = request.getParameter("draw");
		String st = request.getParameter("start");
		int start = Integer.parseInt(st);
		String le = request.getParameter("length");
		int length = Integer.parseInt(le);
		// 获取前台额外传递过来的查询条件
		String department_qu = request.getParameter("department");
		if (department_qu.equals("不限"))
			department_qu = null;
		if(role == 3){
			department_qu = us.getNickname();
		}
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

			QueryResult result = reportService.reportBonus(start, length, query);

			StringBuffer data1 = new StringBuffer();
			StringBuffer data = new StringBuffer();
			data1.append("\"draw\":" + draw + "," + "\"recordsTotal\":" + result.getTotalAmount() + ","
					+ "\"recordsFiltered\":" + result.getTotalAmount() + "," + "\"data\":"+"[");
				//+ JSON.toJSONString(result.getResult(), filter));
			// repStr = ActionUtil.getResponse("200", "操作成功", data.toString());
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			for(Object o : result.getResult()) {
				Equity e = (Equity) o;
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
				BigDecimal retained_amount = dir_amount.multiply(new BigDecimal(0.1)).setScale(2,BigDecimal.ROUND_HALF_UP);
				BigDecimal prebonus_amount = dir_amount.multiply(new BigDecimal(0.9)).setScale(2,BigDecimal.ROUND_HALF_UP);
				BigDecimal retained_rate = new BigDecimal(0.1).setScale(4,BigDecimal.ROUND_HALF_UP);
				BigDecimal prebonus_rate = new BigDecimal(0.9).setScale(4,BigDecimal.ROUND_HALF_UP);
				BigDecimal dir_amount1 = new BigDecimal(0);
				BigDecimal dir_amount2 = new BigDecimal(0);
				BigDecimal dir_amount3 = new BigDecimal(0);
				BigDecimal dir_rate1 = new BigDecimal(0);
				BigDecimal dir_rate2 = new BigDecimal(0);
				BigDecimal dir_rate3 = new BigDecimal(0);
				if(dir_count == 3) {
					dir_amount1 = prebonus_amount.multiply(new BigDecimal(0.5)).setScale(2,BigDecimal.ROUND_HALF_UP);
					dir_amount2 = prebonus_amount.multiply(new BigDecimal(0.25)).setScale(2,BigDecimal.ROUND_HALF_UP);
					dir_amount3 = prebonus_amount.subtract(dir_amount1).subtract(dir_amount2).setScale(2,BigDecimal.ROUND_HALF_UP);
					if(dir_rate != null) {
						dir_rate1 = prebonus_rate.multiply(new BigDecimal(0.5)).multiply(dir_rate).setScale(4,BigDecimal.ROUND_HALF_UP);
						dir_rate2 = prebonus_rate.multiply(new BigDecimal(0.25)).multiply(dir_rate).setScale(4,BigDecimal.ROUND_HALF_UP);
						dir_rate3 = prebonus_rate.multiply(new BigDecimal(0.25)).multiply(dir_rate).setScale(4,BigDecimal.ROUND_HALF_UP);
					}
				}
				if(dir_count == 2){
					dir_amount1 = prebonus_amount.multiply(new BigDecimal(0.667)).setScale(2,BigDecimal.ROUND_HALF_UP);
					dir_amount2 = prebonus_amount.subtract(dir_amount1).setScale(2,BigDecimal.ROUND_HALF_UP);
					dir_amount3 = new BigDecimal(0);
					if(dir_rate != null) {
						dir_rate1 = prebonus_rate.multiply(new BigDecimal(0.667)).multiply(dir_rate).setScale(4,BigDecimal.ROUND_HALF_UP);
						dir_rate2 = prebonus_rate.multiply(new BigDecimal(0.333)).multiply(dir_rate).setScale(4,BigDecimal.ROUND_HALF_UP);
						dir_rate3 = new BigDecimal(0); 
					}
				}
				if(dir_count == 1){
				}
				data.append("{");
				data.append("\"department\":\"" + e.getDepartment()+"\",");
				data.append("\"type\":\"" + filter.process(null, "type", e.getType())+"\",");
				data.append("\"account_date\":\"" + sdf.format(e.getAccount_date())+"\",");
				data.append("\"cardno\":\"" + filter.process(null, "cardno", e.getCardno())+"\",");
				data.append("\"account_item\":\"" + filter.process(null, "account_item", e.getAccount_item())+"\",");
				data.append("\"income\":\"" + filter.process(null, "income", e.getIncome())+"\",");
				data.append("\"account_rate\":\"" + filter.process(null, "account_rate", e.getAccount_rate())+"\",");
				data.append("\"prize_rate\":\"" + filter.process(null, "prize_rate", e.getPrize_rate())+"\",");
				data.append("\"dir_count\":\""+ dir_count+" \",");
				if(retained_rate.compareTo(new BigDecimal(0))!=0) {
					data.append("\"retained_rate\":\""+ retained_rate+" \",");
					data.append("\"retained_amount\":\""+ retained_amount+" \",");
					data.append("\"prebonus_rate\":\""+ prebonus_rate+" \",");
					data.append("\"prebonus_amount\":\""+ prebonus_amount+" \",");
					data.append("\"dir_rate1\":\""+ dir_rate1+" \",");
					data.append("\"dir_amount1\":\""+ dir_amount1+" \",");
					data.append("\"dir_rate2\":\""+ dir_rate2+" \",");
					data.append("\"dir_amount2\":\""+ dir_amount2+" \",");
					data.append("\"dir_rate3\":\""+ dir_rate3+" \",");
					data.append("\"dir_amount3\":\""+ dir_amount3+" \"");
				}else {
					data.append("\"retained_rate\":\"  \",");
					data.append("\"retained_amount\":\""+ retained_amount+" \",");
					data.append("\"prebonus_rate\":\" \",");
					data.append("\"prebonus_amount\":\""+ prebonus_amount+" \",");
					data.append("\"dir_rate1\":\" \",");
					data.append("\"dir_amount1\":\""+ dir_amount1+" \",");
					data.append("\"dir_rate2\":\" \",");
					data.append("\"dir_amount2\":\""+ dir_amount2+" \",");
					data.append("\"dir_rate3\":\" \",");
					data.append("\"dir_amount3\":\""+ dir_amount3+" \"");
				}
				data.append("},");
			}
			int ind = data.lastIndexOf(",");
			if(ind>0)
			data.deleteCharAt(ind);
			data1.append(data.toString());
			data1.append("]");
			repStr = "{" + data1 + "}";
			System.out.println(data1.toString());
		} catch (Exception e) {
			e.printStackTrace();
			repStr = ActionUtil.getResponse("500", "提交过程出错！");
		}

		ActionUtil.sendJSONToClient(repStr, response);
	}
	
	@RequestMapping("/report/balance")
	public void queryBalance(HttpServletRequest request, HttpServletResponse response, String json) {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User us = (User) session.getAttribute("user");
		int role = us.getRole();
		String draw = request.getParameter("draw");
		String st = request.getParameter("start");
		int start = Integer.parseInt(st);
		String le = request.getParameter("length");
		int length = Integer.parseInt(le);
		// 获取前台额外传递过来的查询条件
		String department_qu = request.getParameter("department");
		if (department_qu.equals("不限"))
			department_qu = null;
		if(role == 3){
			department_qu = us.getNickname();
		}
		String year_p = request.getParameter("year");
		int year = -1;
		String month_p = request.getParameter("month");
		int month = -1;

		Enumeration<String> headers = request.getHeaderNames();
		String repStr = "";
		while (headers.hasMoreElements()) {
			String head = headers.nextElement();
		}
		try {
			if (year_p != null && year_p != "") {
				year = Integer.parseInt(year_p);
			}
			if (month_p != null && month_p != "") {
				month = Integer.parseInt(month_p);
			}
			Balance query = new Balance();
			query.setDepartment(department_qu);
			query.setYear(year);
			query.setMonth(month);

			QueryResult result = reportService.reportBalance(start, length, query);

			StringBuffer data = new StringBuffer();
			data.append("\"draw\":" + draw + "," + "\"recordsTotal\":" + result.getTotalAmount() + ","
					+ "\"recordsFiltered\":" + result.getTotalAmount() + "," + "\"data\":[");
				//+ JSON.toJSONString(result.getResult(), filter));
			// repStr = ActionUtil.getResponse("200", "操作成功", data.toString());
			for(Object o : result.getResult()) {
				Balance b = (Balance) o;
				String department = b.getDepartment()==null?"":b.getDepartment();
				year = b.getYear();
				month = b.getMonth();
				String type = b.getType();
				if(type.equals("1")){
					type = "当月余额";
				}
				if(type.equals("0")){
					type = "发生额";
				}
				BigDecimal equity = b.getEquity();
				BigDecimal pro_bonus = b.getPro_bonus();
				BigDecimal expense = b.getExpense();
				BigDecimal dir_bonus = b.getDir_bonus();
				data.append("{");
				data.append("\"department\":\"" + b.getDepartment()+"\",");
				data.append("\"year\":\"" + year+"\",");
				data.append("\"month\":\"" + month+"\",");
				data.append("\"type\":\"" +type +"\",");
				data.append("\"equity\":\"" + filter.process(null, "equity", equity)+"\",");
				data.append("\"pro_bonus\":\"" + filter.process(null, "pro_bonus", pro_bonus)+"\",");
				data.append("\"expense\":\"" + filter.process(null, "expense", expense)+"\",");
				data.append("\"dir_bonus\":\"" + filter.process(null, "dir_bonus", dir_bonus)+"\"");
				data.append("},");
			}
			data.deleteCharAt(data.lastIndexOf(","));
			data.append("]");
			repStr = "{" + data + "}";
			System.out.println("结算报表："+repStr);

		} catch (Exception e) {
			e.printStackTrace();
			repStr = ActionUtil.getResponse("500", "提交过程出错！");
		}

		ActionUtil.sendJSONToClient(repStr, response);
	}
	
	@RequestMapping("/download/balance")
	public void downloadBalance(HttpServletRequest request, HttpServletResponse response, String json) throws Exception{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User us = (User) session.getAttribute("user");
		int role = us.getRole();
		res.setContentType("application/vnd.ms-excel");
		res.setHeader("Content-disposition", "attachment;filename=summary.xls");
		OutputStream os = response.getOutputStream();
		
		String department_qu = request.getParameter("department");
		if (department_qu.equals("不限"))
			department_qu = null;
		String year_p = request.getParameter("year");
		int year = -1;
		String month_p = request.getParameter("month");
		int month = -1;
		if (year_p != null && year_p != "") {
			year = Integer.parseInt(year_p);
		}
		if (month_p != null && month_p != "") {
			month = Integer.parseInt(month_p);
		}
		WritableWorkbook wwb = reportService.downloadBalance(department_qu, year, month, os);
        wwb.write(); 
        // 关闭文件 
        wwb.close(); 
	}

	@RequestMapping("/download/bonus")
	public void downloadBonus(HttpServletRequest request, HttpServletResponse response, String json) throws Exception{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User us = (User) session.getAttribute("user");
		int role = us.getRole();
		res.setContentType("application/vnd.ms-excel");
		res.setHeader("Content-disposition", "attachment;filename=bonus.xls");
		OutputStream os = response.getOutputStream();
		
		String department_qu = request.getParameter("department");
		if (department_qu.equals("不限"))
			department_qu = null;
		String account_date_st = request.getParameter("account_date_start");
		String account_date_en = request.getParameter("account_date_end");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date account_date_start = null;
		if (account_date_st != null && account_date_st != "") {
			account_date_start = sdf.parse(account_date_st);
		}
		Date account_date_end = null;
		if (account_date_en != null && account_date_en != "") {
			account_date_end = sdf.parse(account_date_en);
		}

		WritableWorkbook wwb = reportService.downloadBonus(department_qu, account_date_start, account_date_end, os);
        wwb.write(); 
        // 关闭文件 
        wwb.close(); 
	}
	
	@RequestMapping("/download/detail")
	public void downloadDetail(HttpServletRequest request, HttpServletResponse response, String json) throws Exception{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User us = (User) session.getAttribute("user");
		int role = us.getRole();
		res.setContentType("application/vnd.ms-excel");
		res.setHeader("Content-disposition", "attachment;filename=detail.xls");
		OutputStream os = response.getOutputStream();
		
		String department_qu = request.getParameter("department");
		if (department_qu.equals("不限"))
			department_qu = null;
		String type = request.getParameter("type");

		String account_date_st = request.getParameter("start");
		String account_date_en = request.getParameter("end");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date account_date_start = null;
		if (account_date_st != null && account_date_st != "") {
			account_date_start = sdf.parse(account_date_st);
		}
		Date account_date_end = null;
		if (account_date_en != null && account_date_en != "") {
			account_date_end = sdf.parse(account_date_en);
		}

		WritableWorkbook wwb = reportService.downloadDetail(department_qu, type, account_date_start, account_date_end, os);
        wwb.write(); 
        // 关闭文件 
        wwb.close(); 
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
