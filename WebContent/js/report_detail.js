function query(){
	
	var page = 1;
	var department = $("#department").val();
	if(department == '不限'){
		department = '';
	}
	var type = $("#type option:selected").val();
	var account_date_start = $("#account_date_start").val();
	var account_date_end = $("#account_date_end").val();
	
	var dataStr = "{\"page\":\"" + page + "\"";
	if(department != ""){
		dataStr =dataStr+ ",\"department\":\"" + department + "\"";
	}
	if(account_date_start != ""){
		dataStr =dataStr+ ",\"account_date_start\":\"" + account_date_start + "\"";
	}
	if(account_date_end != ""){
		dataStr =dataStr+ ",\"account_date_end\":\"" + account_date_end + "\"";
	}
	if(type != ""){
		dataStr =dataStr+ ",\"type\":\"" + type + "\"";
	}

	dataStr = dataStr + "}";
	
	$.ajax( {
		type : 'post',
		url : 'report/detail',
		data : {json : dataStr},
		dataType : 'json',
		timeout: 10000,
		success : function(result) {
		    
			if (result.returnFlag === "200") {
				generateTable(result);
			}
			if(result.returnFlag === "500"){
				jQuery('#detail-record').html('<tr><td colspan=12>'+result.returnMsg+'</td></tr>');
			}

		},
		error : function(result) {
			alert("失败" + result.returnMsg);
			jQuery('#detail-record').html('');

		}
	});
}

function generateTable(data) {
	var html;
	if (data.amount > 0) {
		jQuery('#detail-record').html('');
	} else {
		jQuery('#detail-record').html('<tr><td colspan=12>无查询信息</td></tr>');
	}
	var id;
	var department;
	var type_int;
	var type;
	var account_date;
	var cardno;
	var account_item;
	var income;
	var account_rate;
	var prize_rate;
	var card_discount;
	var dir_count;
	var equity;
	var pro_bonus_rate;
	var pro_bonus_amount;
	var expense_rate;
	var expense_amount;
	var dir_rate;
	var dir_amount;
	var remark;
	
	for ( var i = 0; i < data.amount; i++) {
		id = data.equitiesList[i].id;
		department=data.equitiesList[i].department;
		type_int=data.equitiesList[i].type;
		account_date=data.equitiesList[i].account_date;
		cardno=data.equitiesList[i].cardno;
		account_item=data.equitiesList[i].account_item;
		income=data.equitiesList[i].income;
		account_rate=data.equitiesList[i].account_rate;
		prize_rate=data.equitiesList[i].prize_rate;
		card_discount=data.equitiesList[i].card_discount;
		dir_count=data.equitiesList[i].dir_count;
		equity=data.equitiesList[i].equity;
		pro_bonus_rate=data.equitiesList[i].pro_bonus_rate;
		expense_rate=data.equitiesList[i].expense_rate;
		dir_rate=data.equitiesList[i].dir_rate;
		pro_bonus_amount=data.equitiesList[i].pro_bonus_amount;
		expense_amount=data.equitiesList[i].expense_amount;
		dir_amount=data.equitiesList[i].dir_amount;
		remark=data.equitiesList[i].remark;
	
		if (typeof(department) == "undefined")department='';
		if (typeof(type_int) == "undefined")type_int='';
		switch(type_int){
			case "1":
				type="运行卡";
				break;
			case "2":
				type="结算卡";
				break;
			case "11":
				type="提取项目奖金";
				break;
			case "12":
				type="提取所长奖金";
				break;
			case "13":
				type="成本报账";
				break;
			case "15":
				type="其他入账";
				break;
			default:
				type="未定义";
		}
		if (typeof(account_date) == "undefined")account_date='';
		if (typeof(account_item) == "undefined")account_item='';
		if (typeof(income) == "undefined")income='';
		if (typeof(cardno) == "undefined")cardno='';
		if (typeof(account_rate) == "undefined")account_rate='';
		if (typeof(prize_rate) == "undefined")prize_rate='';
		if (typeof(card_discount) == "undefined")card_discount='';
		if (typeof(dir_count) == "undefined")dir_count='';

		
		if (typeof(equity) == "undefined")equity='';
		if (typeof(pro_bonus_amount) == "undefined")pro_bonus_amount='';
		if (typeof(expense_amount) == "undefined")expense_amount='';
		if (typeof(dir_amount) == "undefined")dir_amount='';
		if (typeof(pro_bonus_rate) == "undefined")pro_bonus_rate='';
		if (typeof(expense_rate) == "undefined")expense_rate='';
		if (typeof(dir_rate) == "undefined")dir_rate='';
		if (typeof(remark) == "undefined")remark='';
		if(account_date != ''){
			var show_date = new Date(account_date);
			var mon = show_date.getMonth()+1;
			account_date = show_date.getFullYear()+'-'+mon+'-'+show_date.getDate();
		}
		
		html+='<tr>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="id">'+id+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="设计所">'+department+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="入账类型">'+type+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="到账时间">'+account_date+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="卡号">'+cardno+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="项目名称">'+account_item+'</td>';
		
		html+='<td style="text-align:center;vertical-align:middle;" data-title="到账产值">'+income+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="核算比例">'+account_rate+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="各奖励系数">'+prize_rate+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="已结算系数">'+card_discount+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="所长人数">'+dir_count+'</td>';
		

		html+='<td style="text-align:center;vertical-align:middle;" data-title="所权益">'+equity+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="项目奖金比例">'+pro_bonus_rate+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="项目奖金">'+pro_bonus_amount+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="报账成本比例">'+expense_rate+'</td>'
		html+='<td style="text-align:center;vertical-align:middle;" data-title="报账成本">'+expense_amount+'</td>'
		html+='<td style="text-align:center;vertical-align:middle;" data-title="所长奖金比例">'+dir_rate+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="所长奖金">'+dir_amount+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="备注">'+remark+'</td>';
		html+='</tr>';
    }               
    jQuery('#detail-record').html(html);
    $('table tr').find('td:eq(0)').hide();
}