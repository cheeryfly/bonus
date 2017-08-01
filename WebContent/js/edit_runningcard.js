function load(){
	var id = getUrlParam('id');
	
	$("#account_rate").val(0.32);
	$("#prize_rate").val(1);
	$("#dir_count").val(3);
	$("#pro_bonus_rate").val(0.21);
	$("#expense_rate").val(0.06);
	$("#dir_rate").val(0.05);
	
	
	var dataStr = "{\"id\":\"" + id + "\"";
	
	dataStr = dataStr + "}";
	$.ajax( {
		type : 'post',
		url : 'check/query',
		data : {json : dataStr},
		dataType : 'json',
		timeout: 10000,
		success : function(result) {
		    
			if (result.returnFlag === "200") {
				initiate(result);
			}
			if(result.returnFlag === "500"){
				alert(result.returnMsg);
			}

		},
		error : function(result) {
			alert("失败" + result.returnMsg);

		}
	});
}

function initiate(data){
	var id = data.equitiesList[0].id;
	var department = data.equitiesList[0].department;
	var account_date = data.equitiesList[0].account_date;
	var account_item = data.equitiesList[0].account_item;
	var pro_id = data.equitiesList[0].pro_id;
	var cardno = data.equitiesList[0].cardno;
	var income = data.equitiesList[0].income;
	var account_rate = data.equitiesList[0].account_rate;
	var prize_rate = data.equitiesList[0].prize_rate;
	var dir_count = data.equitiesList[0].dir_count;
	var remark = data.equitiesList[0].remark;
	var equity = data.equitiesList[0].equity;
	var pro_bonus_amount = data.equitiesList[0].pro_bonus_amount;
	var pro_bonus_rate = data.equitiesList[0].pro_bonus_rate;
	var expense_amount = data.equitiesList[0].expense_amount;
	var expense_rate = data.equitiesList[0].expense_rate;
	var dir_amount = data.equitiesList[0].dir_amount;
	var dir_rate = data.equitiesList[0].dir_rate;
	var check_remark = data.equitiesList[0].check_remark;
	
	if (typeof(department) == "undefined")department='';
	if (typeof(account_date) == "undefined")account_date='';
	if (typeof(account_item) == "undefined")account_item='';
	if (typeof(pro_id) == "undefined")pro_id='';
	if (typeof(cardno) == "undefined")cardno='';
	if (typeof(income) == "undefined")income='';
	if (typeof(account_rate) == "undefined")account_rate='';
	if (typeof(prize_rate) == "undefined")prize_rate='';
	if (typeof(dir_count) == "undefined")dir_count='';
	if (typeof(remark) == "undefined")remark='';
	if (typeof(equity) == "undefined")equity='';
	if (typeof(pro_bonus_amount) == "undefined")pro_bonus_amount='';
	if (typeof(pro_bonus_rate) == "undefined")pro_bonus_rate='';
	if (typeof(expense_amount) == "undefined")expense_amount='';
	if (typeof(expense_rate) == "undefined")expense_rate='';
	if (typeof(dir_amount) == "undefined")dir_amount='';
	if (typeof(dir_rate) == "undefined")dir_rate='';
	if (typeof(check_remark) == "undefined")check_remark='';
	if(account_date != ''){
		var show_date = new Date(account_date);
		var mon = show_date.getMonth()+1;
		account_date = show_date.getFullYear()+'-'+mon+'-'+show_date.getDate();
	}
	$("#department").val(department);
	$("#account_date").val(account_date);
	$("#account_item").val(account_item);
	$("#pro_id").val(pro_id);
	$("#cardno").val(cardno);
	$("#income").val(income);
	$("#account_rate").val(account_rate);
	$("#prize_rate").val(prize_rate);
	$("#dir_count").val(dir_count);
	$("#remark").val(remark);
	$("#equity").val(equity);
	$("#pro_bonus_amount").val(pro_bonus_amount);
	$("#pro_bonus_rate").val(pro_bonus_rate);
	$("#expense_amount").val(expense_amount);
	$("#expense_rate").val(expense_rate);
	$("#dir_amount").val(dir_amount);
	$("#dir_rate").val(dir_rate);
	$("#id").val(id);
	$("#check_remark").val(check_remark);	
}

function runningCard_save(){
	var id = $("#id").val();
	var department = $("#department").val();
	var account_date = $("#account_date").val();
	var account_item = $("#account_item").val();
	var project_id = $("#project_id").val();
	var cardno = $("#cardno").val();
	var income = $("#income").val();
	var account_rate = $("#account_rate").val();
	var prize_rate = $("#prize_rate").val();
	var dir_count = $("#dir_count").val();
	var remark = $("#remark").val();
	var equity = $("#equity").val();
	var pro_bonus_amount = $("#pro_bonus_amount").val();
	var pro_bonus_rate = $("#pro_bonus_rate").val();
	var expense_amount = $("#expense_amount").val();
	var expense_rate = $("#expense_rate").val();
	var dir_amount = $("#dir_amount").val();
	var dir_rate = $("#dir_rate").val();
	var check_remark = $("#check_remark").val();

	var dataStr = "{\"query\":\"query\"";

	if(id != ""){
		dataStr =dataStr+ ",\"id\":\"" + id + "\"";
	}
	if(department != ""){
		dataStr =dataStr+ ",\"department\":\"" + department + "\"";
	}
	if(account_date != ""){
		dataStr =dataStr+ ",\"account_date\":\"" + account_date + "\"";
	}
	if(account_item != ""){
		dataStr =dataStr+ ",\"account_item\":\"" + account_item + "\"";
	}
	if(project_id != ""){
		dataStr =dataStr+ ",\"project_id\":\"" + project_id + "\"";
	}
	if(cardno != ""){
		dataStr =dataStr+ ",\"cardno\":\"" + cardno + "\"";
	}
	if(income != ""){
		dataStr =dataStr+ ",\"income\":\"" + income + "\"";
	}
	if(account_rate != ""){
		dataStr =dataStr+ ",\"account_rate\":\"" + account_rate + "\"";
	}
	if(prize_rate != ""){
		dataStr =dataStr+ ",\"prize_rate\":\"" + prize_rate + "\"";
	}
	if(dir_count != ""){
		dataStr =dataStr+ ",\"dir_count\":\"" + dir_count + "\"";
	}
	if(remark != ""){
		dataStr =dataStr+ ",\"remark\":\"" + remark + "\"";
	}
	if(equity != ""){
		dataStr =dataStr+ ",\"equity\":\"" + equity + "\"";
	}
	if(pro_bonus_amount != ""){
		dataStr =dataStr+ ",\"pro_bonus_amount\":\"" + pro_bonus_amount + "\"";
	}
	if(pro_bonus_rate != ""){
		dataStr =dataStr+ ",\"pro_bonus_rate\":\"" + pro_bonus_rate + "\"";
	}
	if(expense_amount != ""){
		dataStr =dataStr+ ",\"expense_amount\":\"" + expense_amount + "\"";
	}
	if(expense_rate != ""){
		dataStr =dataStr+ ",\"expense_rate\":\"" + expense_rate + "\"";
	}
	if(dir_amount != ""){
		dataStr =dataStr+ ",\"dir_amount\":\"" + dir_amount + "\"";
	}
	if(dir_rate != ""){
		dataStr =dataStr+ ",\"dir_rate\":\"" + dir_rate + "\"";
	}
	if(check_remark != ""){
		dataStr =dataStr+ ",\"check_remark\":\"" + check_remark + "\"";
	}
	
	dataStr = dataStr + "}";
	$.ajax( {
		type : 'post',
		url : 'runningcard/edit',
		data : {json : dataStr},
		dataType : 'json',
		timeout: 10000,
		success : function(result) {
		    
			if (result.returnFlag === "200") {
				window.location.href = 'index.html';
			}else {
				alert(result.returnMsg); 
			}

		},
		error : function(result) {
			alert("修改失败：" + result.returnMsg);
		}
	});
}