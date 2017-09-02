package com.bonus.service.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonus.bean.Balance;
import com.bonus.bean.Director;
import com.bonus.bean.Equity;
import com.bonus.bean.QueryResult;
import com.bonus.dao.BalanceDao;
import com.bonus.dao.DirectorDao;
import com.bonus.dao.EquityDao;
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

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private EquityDao equitydao;
	@Autowired
	private BalanceDao balancedao;
	@Autowired
	private DirectorDao directordao;
	
	@Transactional
	public QueryResult reportDetail(int start, int length, Equity e) {
		return equitydao.reportDetail(start, length, e);
	}

	@Transactional
	public QueryResult reportBonus(int start, int length, Equity e) {
		return equitydao.reportBonus(start, length, e);
	}
	
	@Transactional
	public QueryResult reportBalance(int start, int length, Balance b){
		return balancedao.reportBalance(start, length, b);
	}

	@Transactional
	public WritableWorkbook downloadBalance(String department, int year, int month, OutputStream os)throws Exception {
		Balance b = new Balance();
		b.setDepartment(department);
		b.setYear(year);
		b.setMonth(month);
		QueryResult qr = balancedao.reportBalance(0, 999999, b);
		List<Object> list = qr.getResult();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet sheet = wwb.createSheet("经济责任制汇总情况表", 0);
		jxl.write.WritableFont wfont = new jxl.write.WritableFont(WritableFont.createFont("楷书"), 10); 
		WritableCellFormat wc = new WritableCellFormat(wfont); 
        // 设置居中 
        wc.setAlignment(Alignment.CENTRE); 
        // 设置边框线 
        wc.setBorder(Border.ALL, BorderLineStyle.THIN);
        // 设置单元格的背景颜色 
        wc.setBackground(jxl.format.Colour.WHITE); 
        WritableCellFormat wc2 = new WritableCellFormat(wfont); 
        // 设置居中 
        wc2.setAlignment(Alignment.CENTRE); 
        // 设置边框线 
        wc2.setBorder(Border.ALL, BorderLineStyle.THIN);
        // 设置单元格的背景颜色 
        wc2.setBackground(jxl.format.Colour.SKY_BLUE); 
        Label label1 = new Label(0, 0, "设计所");
		label1.setCellFormat(wc);
		Label label2 = new Label(1, 0, "账期");
		label2.setCellFormat(wc);
		Label label7 = new Label(2, 0, "项目");
		label7.setCellFormat(wc);
		Label label3 = new Label(3, 0, "所权益");
		label3.setCellFormat(wc);
		Label label4 = new Label(4, 0, "项目奖金");
		label4.setCellFormat(wc);
		Label label5 = new Label(5, 0, "报账成本");
		label5.setCellFormat(wc);
		Label label6 = new Label(6, 0, "所长奖金");
		label6.setCellFormat(wc);
		sheet.addCell(label1);
		sheet.addCell(label2);
		sheet.addCell(label3);
		sheet.addCell(label4);
		sheet.addCell(label5);
		sheet.addCell(label6);
		sheet.addCell(label7);
		NumberFormat nf = new jxl.write.NumberFormat("#.##"); 
        WritableCellFormat wcf = new jxl.write.WritableCellFormat(nf); 
        int row = 1;
		for(Object o : list){
			WritableCellFormat w = null;

			Balance ba = (Balance) o;
			String type = ba.getType();
			if(type.equals("1")){
				type="当期余额";
				w = wc2;
			}
			if(type.equals("0")){
				type="发生额";
				w = wc;
			}
			Label l_department = new Label(0, row, ba.getDepartment());
			l_department.setCellFormat(w);
			Label l_year = new Label(1, row, ba.getYear().toString()+"-"+ba.getMonth().toString());
			l_year.setCellFormat(w);
			
			Label l_type = new Label(2, row, type);
			l_type.setCellFormat(w);
			Label n_equity = new Label(3, row, ba.getEquity().toPlainString(), w); 
			Label n_pro_bonus = new Label(4, row, ba.getPro_bonus().toPlainString(), w); 
			Label n_expense = new Label(5, row, ba.getExpense().toPlainString(), w); 
			Label n_dir_bonus = new Label(6, row, ba.getDir_bonus().toPlainString(), w); 
			
			sheet.addCell(l_department);
			sheet.addCell(l_year);
			sheet.addCell(l_type);
			sheet.addCell(n_equity);
			sheet.addCell(n_pro_bonus);
			sheet.addCell(n_expense);
			sheet.addCell(n_dir_bonus);
			row++;
		}
		return wwb;
	}

	@Transactional
	public WritableWorkbook downloadBonus(String department, Date start_date, Date end_date, OutputStream os)
			throws Exception {
		Equity e = new Equity();
		e.setDepartment(department);
		e.setAccount_date(start_date);
		e.setRec_date(end_date);
		QueryResult qr = equitydao.reportBonus(0, 999999, e);
		List<Object> list = qr.getResult();
		Director d = new Director();
		d.setDepartment(department);
		List<Director> ds = directordao.queryDirectors(d);
		int dir_count = ds.size();
		String dir1 = ds.get(0).getName();
		String dir2 = ds.get(1).getName();
		String dir3 = "";
		String dir1_total = ds.get(0).getBonus_total().toString();
		String dir1_draw = ds.get(0).getBonus_draw().toString();
		String dir1_surplus = ds.get(0).getBonus_surplus().toString();
		String dir2_total = ds.get(1).getBonus_total().toString();
		String dir2_draw = ds.get(1).getBonus_draw().toString();
		String dir2_surplus = ds.get(1).getBonus_surplus().toString();
		String dir3_total = "";
		String dir3_draw = "";
		String dir3_surplus = "";
		if(dir_count == 3){
			dir3 = ds.get(2).getName();
			dir3_total = ds.get(2).getBonus_total().toString();
			dir3_draw = ds.get(2).getBonus_draw().toString();
			dir3_surplus = ds.get(2).getBonus_surplus().toString();
		}
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet sheet = wwb.createSheet("所长奖金表", 0);
		jxl.write.WritableFont wfont = new jxl.write.WritableFont(WritableFont.createFont("楷书"), 10); 
		WritableCellFormat wc = new WritableCellFormat(wfont); 
        // 设置居中 
        wc.setAlignment(Alignment.CENTRE); 
        // 设置边框线 
        wc.setBorder(Border.ALL, BorderLineStyle.THIN);
        // 设置单元格的背景颜色 
        wc.setBackground(jxl.format.Colour.WHITE); 
        WritableCellFormat wc2 = new WritableCellFormat(wfont); 
        Label label1 = new Label(0, 0, "设计所");
		label1.setCellFormat(wc);
		Label label2 = new Label(1, 0, "到账类型");
		label2.setCellFormat(wc);
		Label label3 = new Label(2, 0, "到账时间");
		label3.setCellFormat(wc);
		Label label4 = new Label(3, 0, "卡号");
		label4.setCellFormat(wc);
		Label label5 = new Label(4, 0, "项目名称");
		label5.setCellFormat(wc);
		Label label6 = new Label(5, 0, "到账产值");
		label6.setCellFormat(wc);
		Label label7 = new Label(6, 0, "核算比例");
		label7.setCellFormat(wc);
		Label label8 = new Label(7, 0, "各奖励系数");
		label8.setCellFormat(wc);
		Label label9 = new Label(8, 0, "所长人数");
		label9.setCellFormat(wc);
		Label label10 = new Label(9, 0, "所长奖金");
		label10.setCellFormat(wc);
		Label label11 = new Label(10, 0, dir1+"比例");
		label11.setCellFormat(wc);
		Label label12 = new Label(11, 0, dir1+"金额");
		label12.setCellFormat(wc);
		Label label13 = new Label(12, 0, dir2+"比例");
		label13.setCellFormat(wc);
		Label label14 = new Label(13, 0, dir2+"金额");
		
		
		
		sheet.addCell(label1);
		sheet.addCell(label2);
		sheet.addCell(label3);
		sheet.addCell(label4);
		sheet.addCell(label5);
		sheet.addCell(label6);
		sheet.addCell(label7);
		sheet.addCell(label8);
		sheet.addCell(label9);
		sheet.addCell(label10);
		sheet.addCell(label11);
		sheet.addCell(label12);
		sheet.addCell(label13);
		sheet.addCell(label14);
		if(dir_count == 3){
			label14.setCellFormat(wc);
			Label label15 = new Label(14, 0, dir3+"比例");
			label15.setCellFormat(wc);
			Label label16 = new Label(15, 0, dir3+"金额");
			label16.setCellFormat(wc);
			sheet.addCell(label15);
			sheet.addCell(label16);
		}
		
//		NumberFormat nf = new jxl.write.NumberFormat("#.##"); 
//        WritableCellFormat wcf = new jxl.write.WritableCellFormat(nf); 
        int row = 1;
		for(Object o : list){

			Equity eq = (Equity) o;
			Label l_department = new Label(0, row, eq.getDepartment(), wc);
			String type = eq.getType();
			if(type.equals("1")){
				type="运行卡";
			}
			if(type.equals("2")){
				type="结算卡";
			}
			if(type.equals("3")){
				type="其他入账";
			}
			if(type.equals("11")){
				type="提取项目奖金";
			}
			if(type.equals("12")){
				type="提取所长奖金";
			}
			if(type.equals("13")){
				type="成本报账";
			}
			if(type.equals("14")){
				type="冲预发";
			}
			if(type.equals("15")){
				type="其他出账";
			}
			Label l_type = new Label(1, row, type, wc);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Label l_account_date = new Label(2, row, sdf.format(eq.getAccount_date()), wc);
			Label l_cardo = new Label(3, row, eq.getCardno(), wc);
			Label l_account_item = new Label(4, row, eq.getAccount_item(), wc);
			Label l_income = new Label(5, row, getString(eq.getIncome()), wc);
			Label l_account_rate = new Label(6, row, getString(eq.getAccount_rate()), wc);
			Label l_prize_rate = new Label(7, row, getString(eq.getPrize_rate()), wc);
			Label l_dir_count = new Label(8, row, getString(eq.getDir_count()), wc);
			Label l_dir_amount = new Label(9, row, getString(eq.getDir_amount()), wc);
			Label l_dir1_rate ;
			Label l_dir2_rate ;
			Label l_dir3_rate ;
			Label l_dir1_amount ;
			Label l_dir2_amount ;
			Label l_dir3_amount ;
			if(eq.getDir1_name().equals(dir1)){
				l_dir1_rate = new Label(10, row, getString(eq.getDir1_rate()), wc);
				l_dir1_amount = new Label(11, row, getString(eq.getDir1_amount()), wc);
				sheet.addCell(l_dir1_rate);
				sheet.addCell(l_dir1_amount);
			}
			if(eq.getDir2_name().equals(dir1)){
				l_dir1_rate = new Label(10, row, getString(eq.getDir2_rate()), wc);
				l_dir1_amount = new Label(11, row, getString(eq.getDir2_amount()), wc);
				sheet.addCell(l_dir1_rate);
				sheet.addCell(l_dir1_amount);
			}
			if(eq.getDir3_name()!=null && eq.getDir3_name().equals(dir1)){
				l_dir1_rate = new Label(10, row, getString(eq.getDir3_rate()), wc);
				l_dir1_amount = new Label(11, row, getString(eq.getDir3_amount()), wc);
				sheet.addCell(l_dir1_rate);
				sheet.addCell(l_dir1_amount);
			}
			
			if(eq.getDir1_name().equals(dir2)){
				l_dir2_rate = new Label(12, row, getString(eq.getDir1_rate()), wc);
				l_dir2_amount = new Label(13, row, getString(eq.getDir1_amount()), wc);
				sheet.addCell(l_dir2_rate);
				sheet.addCell(l_dir2_amount);
			}
			if(eq.getDir2_name().equals(dir2)){
				l_dir2_rate = new Label(12, row, getString(eq.getDir2_rate()), wc);
				l_dir2_amount = new Label(13, row, getString(eq.getDir2_amount()), wc);
				sheet.addCell(l_dir2_rate);
				sheet.addCell(l_dir2_amount);
			}
			if(eq.getDir3_name()!=null && eq.getDir3_name().equals(dir2)){
				l_dir2_rate = new Label(12, row, getString(eq.getDir3_rate()), wc);
				l_dir2_amount = new Label(13, row, getString(eq.getDir3_amount()), wc);
				sheet.addCell(l_dir2_rate);
				sheet.addCell(l_dir2_amount);
			}
			

			sheet.addCell(l_department);
			sheet.addCell(l_type);
			sheet.addCell(l_account_date);
			sheet.addCell(l_cardo);
			sheet.addCell(l_account_item);
			sheet.addCell(l_income);
			sheet.addCell(l_account_rate);
			sheet.addCell(l_prize_rate);
			sheet.addCell(l_dir_count);
			sheet.addCell(l_dir_amount);
			
			
			if(dir_count == 3){
				if(eq.getDir1_name().equals(dir3)){
					l_dir3_rate = new Label(14, row, getString(eq.getDir1_rate()), wc);
					l_dir3_amount = new Label(15, row, getString(eq.getDir1_amount()), wc);
					sheet.addCell(l_dir3_rate);
					sheet.addCell(l_dir3_amount);
				}
				if(eq.getDir2_name().equals(dir3)){
					l_dir3_rate = new Label(14, row, getString(eq.getDir2_rate()), wc);
					l_dir3_amount = new Label(15, row, getString(eq.getDir2_amount()), wc);
					sheet.addCell(l_dir3_rate);
					sheet.addCell(l_dir3_amount);
				}
				if(eq.getDir3_name()!=null && eq.getDir3_name().equals(dir3)){
					l_dir3_rate = new Label(14, row, getString(eq.getDir3_rate()), wc);
					l_dir3_amount = new Label(15, row, getString(eq.getDir3_amount()), wc);
					sheet.addCell(l_dir3_rate);
					sheet.addCell(l_dir3_amount);
				}
			}
			

			row++;
		}
		
		row = row+3;
		Label l_name = new Label(0, row, "所长", wc);
		Label l_total = new Label(1, row, "累计奖金", wc);
		Label l_draw = new Label(2, row, "已提取奖金", wc);
		Label l_surplus = new Label(3, row, "可提取奖金", wc);
		row++;
		Label l_d1_name = new Label(0, row, dir1, wc);
		Label l_d1_total = new Label(1, row, dir1_total, wc);
		Label l_d1_draw = new Label(2, row, dir1_draw, wc);
		Label l_d1_surplus = new Label(3, row, dir1_surplus, wc);
		row++;
		Label l_d2_name = new Label(0, row, dir2, wc);
		Label l_d2_total = new Label(1, row, dir2_total, wc);
		Label l_d2_draw = new Label(2, row, dir2_draw, wc);
		Label l_d2_surplus = new Label(3, row, dir2_surplus, wc);
		
		sheet.addCell(l_name);
		sheet.addCell(l_total);
		sheet.addCell(l_draw);
		sheet.addCell(l_surplus);
		sheet.addCell(l_d1_name);
		sheet.addCell(l_d1_total);
		sheet.addCell(l_d1_draw);
		sheet.addCell(l_d1_surplus);
		sheet.addCell(l_d2_name);
		sheet.addCell(l_d2_total);
		sheet.addCell(l_d2_draw);
		sheet.addCell(l_d2_surplus);
		if(dir_count ==3){
			row++;
			Label l_d3_name = new Label(0, row, dir3, wc);
			Label l_d3_total = new Label(1, row, dir3_total, wc);
			Label l_d3_draw = new Label(2, row, dir3_draw, wc);
			Label l_d3_surplus = new Label(3, row, dir3_surplus, wc);
			sheet.addCell(l_d3_name);
			sheet.addCell(l_d3_total);
			sheet.addCell(l_d3_draw);
			sheet.addCell(l_d3_surplus);
		}
		return wwb;
	}
	
	@Transactional
	public WritableWorkbook downloadDetail(String department,String type, Date start_date, Date end_date, OutputStream os)
			throws Exception {
		Equity e = new Equity();
		e.setDepartment(department);
		e.setType(type);
		e.setAccount_date(start_date);
		e.setRec_date(end_date);
		QueryResult qr = equitydao.reportDetail(0, 999999, e);
		List<Object> list = qr.getResult();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet sheet = wwb.createSheet("权益明细表", 0);
		jxl.write.WritableFont wfont = new jxl.write.WritableFont(WritableFont.createFont("楷书"), 10); 
		WritableCellFormat wc = new WritableCellFormat(wfont); 
        // 设置居中 
        wc.setAlignment(Alignment.CENTRE); 
        // 设置边框线 
        wc.setBorder(Border.ALL, BorderLineStyle.THIN);
        // 设置单元格的背景颜色 
        wc.setBackground(jxl.format.Colour.WHITE); 
        WritableCellFormat wc2 = new WritableCellFormat(wfont); 
        Label label0 = new Label(0, 0, "序号");
		label0.setCellFormat(wc);
        Label label1 = new Label(1, 0, "设计所");
		label1.setCellFormat(wc);
		Label label2 = new Label(2, 0, "到账类型");
		label2.setCellFormat(wc);
		Label label3 = new Label(3, 0, "到账时间");
		label3.setCellFormat(wc);
		Label label4 = new Label(4, 0, "卡号");
		label4.setCellFormat(wc);
		Label label5 = new Label(5, 0, "项目名称");
		label5.setCellFormat(wc);
		Label label6 = new Label(6, 0, "到账产值");
		label6.setCellFormat(wc);
		Label label7 = new Label(7, 0, "核算比例");
		label7.setCellFormat(wc);
		Label label8 = new Label(8, 0, "各奖励系数");
		label8.setCellFormat(wc);
		Label label9 = new Label(9, 0, "已结算系数");
		label9.setCellFormat(wc);
		Label label10 = new Label(10, 0, "所长人数");
		label10.setCellFormat(wc);
		Label label11 = new Label(11, 0, "所权益");
		label11.setCellFormat(wc);
		Label label12 = new Label(12, 0, "项目奖金比例");
		label12.setCellFormat(wc);
		Label label13 = new Label(13, 0, "项目奖金金额");
		label13.setCellFormat(wc);
		Label label14 = new Label(14, 0, "报账成本比例");
		label14.setCellFormat(wc);
		Label label15 = new Label(15, 0, "报账成本金额");
		label15.setCellFormat(wc);
		Label label16 = new Label(16, 0, "所长奖金比例");
		label16.setCellFormat(wc);
		Label label17 = new Label(17, 0, "所长奖金金额");
		label17.setCellFormat(wc);
		Label label18 = new Label(18, 0, "备注");
		label18.setCellFormat(wc);

		sheet.addCell(label0);
		sheet.addCell(label1);
		sheet.addCell(label2);
		sheet.addCell(label3);
		sheet.addCell(label4);
		sheet.addCell(label5);
		sheet.addCell(label6);
		sheet.addCell(label7);
		sheet.addCell(label8);
		sheet.addCell(label9);
		sheet.addCell(label10);
		sheet.addCell(label11);
		sheet.addCell(label12);
		sheet.addCell(label13);
		sheet.addCell(label14);
		sheet.addCell(label15);
		sheet.addCell(label16);
		sheet.addCell(label17);
		sheet.addCell(label18);
		
        int row = 1;
		for(Object o : list){

			Equity eq = (Equity) o;
			Label l_id = new Label(0, row, new Integer(row).toString(), wc);
			Label l_department = new Label(1, row, eq.getDepartment(), wc);
			String ty = eq.getType();
			if(ty.equals("1")){
				ty="运行卡";
			}
			if(ty.equals("2")){
				ty="结算卡";
			}
			if(ty.equals("3")){
				ty="其他入账";
			}
			if(ty.equals("11")){
				ty="提取项目奖金";
			}
			if(ty.equals("12")){
				ty="提取所长奖金";
			}
			if(ty.equals("13")){
				ty="成本报账";
			}
			if(ty.equals("14")){
				ty="冲预发";
			}
			if(ty.equals("15")){
				ty="其他出账";
			}
			Label l_type = new Label(2, row, ty, wc);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Label l_account_date = new Label(3, row, sdf.format(eq.getAccount_date()), wc);
			Label l_cardo = new Label(4, row, eq.getCardno(), wc);
			Label l_account_item = new Label(5, row, eq.getAccount_item(), wc);
			Label l_income = new Label(6, row, getString(eq.getIncome()), wc);
			Label l_account_rate = new Label(7, row, getString(eq.getAccount_rate()), wc);
			Label l_prize_rate = new Label(8, row, getString(eq.getPrize_rate()), wc);
			Label l_card_discount = new Label(9, row, getString(eq.getCard_discount()), wc);
			Label l_dir_count = new Label(10, row, getString(eq.getDir_count()), wc);
			Label l_equity = new Label(11, row, getString(eq.getEquity()), wc);
			Label l_pro_rate = new Label(12, row, getString(eq.getPro_bonus_rate()), wc);
			Label l_pro_amount = new Label(13, row, getString(eq.getPro_bonus_amount()), wc);
			Label l_expense_rate = new Label(14, row, getString(eq.getExpense_rate()), wc);
			Label l_expense_amount = new Label(15, row, getString(eq.getExpense_amount()), wc);
			Label l_dir_rate = new Label(16, row, getString(eq.getDir_rate()), wc);
			Label l_dir_amount = new Label(17, row, getString(eq.getDir_amount()), wc);
			Label l_remark = new Label(18, row, eq.getCardno(), wc);
			
			sheet.addCell(l_id);
			sheet.addCell(l_department);
			sheet.addCell(l_type);
			sheet.addCell(l_account_date);
			sheet.addCell(l_cardo);
			sheet.addCell(l_account_item);
			sheet.addCell(l_income);
			sheet.addCell(l_account_rate);
			sheet.addCell(l_prize_rate);
			sheet.addCell(l_dir_count);
			sheet.addCell(l_equity);
			sheet.addCell(l_card_discount);
			sheet.addCell(l_pro_rate);
			sheet.addCell(l_pro_amount);
			sheet.addCell(l_expense_rate);
			sheet.addCell(l_expense_amount);
			sheet.addCell(l_dir_rate);
			sheet.addCell(l_dir_amount);
			sheet.addCell(l_remark);

			row++;
		}
		return wwb;
	}
	
	public String getString(Object o){
		if(o == null)return "";
		if(o instanceof BigDecimal){
			BigDecimal b = (BigDecimal)o;
			return b.toPlainString();
		}
		return o.toString();
	}


	

}
