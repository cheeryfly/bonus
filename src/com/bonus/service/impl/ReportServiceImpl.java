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
import com.bonus.bean.Equity;
import com.bonus.bean.QueryResult;
import com.bonus.dao.BalanceDao;
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
	public WritableWorkbook downladBalance(String department, int year, int month, OutputStream os)throws Exception {
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
	public WritableWorkbook downladBonus(String department, Date start_date, Date end_date, OutputStream os)
			throws Exception {
		Equity e = new Equity();
		e.setDepartment(department);
		e.setAccount_date(start_date);
		e.setRec_date(end_date);
		QueryResult qr = equitydao.reportBonus(0, 999999, e);
		List<Object> list = qr.getResult();
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
		Label label10 = new Label(9, 0, "留存比例");
		label10.setCellFormat(wc);
		Label label11 = new Label(10, 0, "留存金额");
		label11.setCellFormat(wc);
		Label label12 = new Label(11, 0, "预发比例");
		label12.setCellFormat(wc);
		Label label13 = new Label(12, 0, "预发金额");
		label13.setCellFormat(wc);
		Label label14 = new Label(13, 0, "所长1比例");
		label14.setCellFormat(wc);
		Label label15 = new Label(14, 0, "所长1金额");
		label15.setCellFormat(wc);
		Label label16 = new Label(15, 0, "所长2比例");
		label16.setCellFormat(wc);
		Label label17 = new Label(16, 0, "所长2金额");
		label17.setCellFormat(wc);
		Label label18 = new Label(17, 0, "所长3比例");
		label18.setCellFormat(wc);
		Label label19 = new Label(18, 0, "所长3金额");
		label19.setCellFormat(wc);
		
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
		sheet.addCell(label19);
		
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
			Label l_reserve_rate = new Label(9, row, "10%", wc);
			Label l_reserve_amount = new Label(10, row, getString(eq.getDir_amount().multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP)), wc);
			Label l_pre_rate = new Label(11, row, "90%", wc);
			Label l_pre_bonus = new Label(12, row, getString(eq.getDir_amount().multiply(new BigDecimal(0.9)).setScale(2, BigDecimal.ROUND_HALF_UP)), wc);
			BigDecimal pre_amount = eq.getDir_amount().multiply(new BigDecimal(0.9)).setScale(2,BigDecimal.ROUND_HALF_UP);
			BigDecimal dir_rate = eq.getDir_rate();
			Label l_dir1_rate ;
			Label l_dir2_rate ;
			Label l_dir3_rate ;
			if(dir_rate == null) {
				l_dir1_rate = new Label(13, row, "-", wc);
				l_dir2_rate = new Label(15, row, "-", wc);
				l_dir3_rate = new Label(17, row, "-", wc);
			
			}
			else {
				l_dir1_rate = new Label(13, row, getString(eq.getDir_rate().multiply(new BigDecimal(0.5)).setScale(4, BigDecimal.ROUND_HALF_UP)), wc);
				l_dir2_rate = new Label(15, row, getString(eq.getDir_rate().multiply(new BigDecimal(0.25)).setScale(4, BigDecimal.ROUND_HALF_UP)), wc);
				l_dir3_rate = new Label(17, row, getString(eq.getDir_rate().multiply(new BigDecimal(0.25)).setScale(4, BigDecimal.ROUND_HALF_UP)), wc);
			}
			Label l_dir1_amount = new Label(14, row, getString(pre_amount.multiply(new BigDecimal(0.5)).setScale(2, BigDecimal.ROUND_HALF_UP)), wc);
			Label l_dir2_amount = new Label(16, row, getString(pre_amount.multiply(new BigDecimal(0.25)).setScale(2, BigDecimal.ROUND_HALF_UP)), wc);
			Label l_dir3_amount = new Label(18, row, getString(pre_amount.multiply(new BigDecimal(0.25)).setScale(2, BigDecimal.ROUND_HALF_UP)), wc);
			
			
			sheet.addCell(l_department);
			sheet.addCell(l_type);
			sheet.addCell(l_account_date);
			sheet.addCell(l_cardo);
			sheet.addCell(l_account_item);
			sheet.addCell(l_income);
			sheet.addCell(l_account_rate);
			sheet.addCell(l_prize_rate);
			sheet.addCell(l_dir_count);
			sheet.addCell(l_reserve_rate);
			sheet.addCell(l_reserve_amount);
			sheet.addCell(l_pre_rate);
			sheet.addCell(l_pre_bonus);
			sheet.addCell(l_dir1_rate);
			sheet.addCell(l_dir1_amount);
			sheet.addCell(l_dir2_rate);
			sheet.addCell(l_dir2_amount);
			sheet.addCell(l_dir3_rate);
			sheet.addCell(l_dir3_amount);

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
