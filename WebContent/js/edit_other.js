function load(){
	var id = getUrlParam('id');
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
	var type = data.equitiesList[0].type;
	var account_item = data.equitiesList[0].account_item;
	var pro_bonus_amount_bak = data.equitiesList[0].pro_bonus_amount;
	var expense_amount_bak = data.equitiesList[0].expense_amount;
	var dir_amount_bak = data.equitiesList[0].dir_amount;
	var remark = data.equitiesList[0].remark;
	var dir_count = data.equitiesList[0].dir_count;
	var equity = data.equitiesList[0].equity;
	var pro_bonus_amount = data.equitiesList[0].pro_bonus_amount;
	var pro_bonus_rate = data.equitiesList[0].pro_bonus_rate;
	var expense_amount = data.equitiesList[0].expense_amount;
	var expense_rate = data.equitiesList[0].expense_rate;
	var dir_amount = data.equitiesList[0].dir_amount;
	var dir_rate = data.equitiesList[0].dir_rate;
	var check_remark = data.equitiesList[0].check_remark;
	
	if (typeof(department) == "undefined")department='';
	if (typeof(dir_count) == "undefined")dir_count='';
	if (typeof(account_date) == "undefined")account_date='';
	if (typeof(type) == "undefined")type='';
	if (typeof(account_item) == "undefined")account_item='';
	if (typeof(pro_bonus_amount_bak) == "undefined")pro_bonus_amount_bak='0';
	if (typeof(expense_amount_bak) == "undefined")expense_amount_bak='0';
	if (typeof(dir_amount_bak) == "undefined")dir_amount_bak='0';
	if (typeof(remark) == "undefined")remark='';
	if (typeof(equity) == "undefined")equity='';
	if (typeof(pro_bonus_amount) == "undefined")pro_bonus_amount='0';
	if (typeof(pro_bonus_rate) == "undefined")pro_bonus_rate='';
	if (typeof(expense_amount) == "undefined")expense_amount='0';
	if (typeof(expense_rate) == "undefined")expense_rate='';
	if (typeof(dir_amount) == "undefined")dir_amount='0';
	if (typeof(dir_rate) == "undefined")dir_rate='';
	if (typeof(check_remark) == "undefined")check_remark='';
	if(account_date != ''){
		var show_date = new Date(account_date);
		var mon = show_date.getMonth()+1;
		if(mon < 10) mon = '0'+ mon;
		var day = show_date.getDate();
		if(day < 10) day = '0'+ day;
		account_date = show_date.getFullYear()+'/'+mon+'/'+day;
	}
	
	$("#department").val(department);
	$("#dir_count").val(dir_count);
	$("#account_date").val(account_date);
	$("#type").val(type);
	$("#account_item").val(account_item);
	$("#pro_bonus_amount_bak").val(pro_bonus_amount_bak);
	$("#expense_amount_bak").val(expense_amount_bak);
	$("#dir_amount_bak").val(dir_amount_bak);
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

function other_save(){
	var id = $("#id").val();
	var department = $("#department").val();
	var account_date = $("#account_date").val();
	var account_item = $("#account_item").val();
	var dir_count = $("#dir_count").val();
	var type = $("#type option:selected").val();
	var remark = $("#remark").val();
	var equity = $("#equity").val();
	var pro_bonus_amount = $("#pro_bonus_amount").val();
	var expense_amount = $("#expense_amount").val();
	var dir_amount = $("#dir_amount").val();
	var check_remark = $("#check_remark").val();

	var dataStr = "{\"query\":\"query\"";

	if(id != ""){
		dataStr =dataStr+ ",\"id\":\"" + id + "\"";
	}
	if(department != ""){
		dataStr =dataStr+ ",\"department\":\"" + department + "\"";
	}
	if(dir_count != ""){
		dataStr =dataStr+ ",\"dir_count\":\"" + dir_count + "\"";
	}
	if(account_date != ""){
		dataStr =dataStr+ ",\"account_date\":\"" + account_date + "\"";
	}
	if(account_item != ""){
		dataStr =dataStr+ ",\"account_item\":\"" + account_item + "\"";
	}
	if(type != ""){
		dataStr =dataStr+ ",\"type\":\"" + type + "\"";
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
	if(expense_amount != ""){
		dataStr =dataStr+ ",\"expense_amount\":\"" + expense_amount + "\"";
	}
	if(dir_amount != ""){
		dataStr =dataStr+ ",\"dir_amount\":\"" + dir_amount + "\"";
	}
	if(check_remark != ""){
		dataStr =dataStr+ ",\"check_remark\":\"" + check_remark + "\"";
	}
	
	dataStr = dataStr + "}";
	$.ajax( {
		type : 'post',
		url : 'other/edit',
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