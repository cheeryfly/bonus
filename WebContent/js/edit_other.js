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