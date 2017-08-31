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
				showControl();
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

	var dir1_id = data.equitiesList[0].dir1_id;
	var dir1_name = data.equitiesList[0].dir1_name;
	var dir1_amount = data.equitiesList[0].dir1_amount;
	var dir2_id = data.equitiesList[0].dir2_id;
	var dir2_name = data.equitiesList[0].dir2_name;
	var dir2_amount = data.equitiesList[0].dir2_amount;
	var dir3_id = data.equitiesList[0].dir3_id;
	var dir3_name = data.equitiesList[0].dir3_name;
	var dir3_amount = data.equitiesList[0].dir3_amount;
	
	
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
	
	if (typeof(dir1_id) == "undefined")dir1_id='';
	if (typeof(dir1_name) == "undefined")dir1_name='';
	if (typeof(dir1_amount) == "undefined")dir1_amount='';
	if (typeof(dir2_id) == "undefined")dir2_id='';
	if (typeof(dir2_name) == "undefined")dir2_name='';
	if (typeof(dir2_amount) == "undefined")dir2_amount='';
	if (typeof(dir3_id) == "undefined")dir3_id='';
	if (typeof(dir3_name) == "undefined")dir3_name='';
	if (typeof(dir3_amount) == "undefined")dir3_amount='';
	
	
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
	
	$("#dir1_id").val(dir1_id);
	$("#dir1_name").val(dir1_name);
	$("#dir1_name1").html(dir1_name+"奖金比例");
	$("#dir1_name2").html(dir1_name+"奖金");
	$("#dir1_amount").val(dir1_amount);
	$("#dir2_id").val(dir2_id);
	$("#dir2_name").val(dir2_name);
	$("#dir2_name1").html(dir2_name+"奖金比例");
	$("#dir2_name2").html(dir2_name+"奖金");
	$("#dir2_amount").val(dir2_amount);
	if(dir_count == 3){
		$("#dir3_id").val(dir3_id);
		$("#dir3_name").val(dir3_name);
		$("#dir3_name1").html(dir3_name+"奖金比例");
		$("#dir3_name2").html(dir3_name+"奖金");
		$("#dir3_amount").val(dir3_amount);
	}
	
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
	var dir1_id = $("#dir1_id").val();
	var dir1_name = $("#dir1_name").val();
	var dir1_rate = $("#dir1_rate").val();
	var dir1_amount = $("#dir1_amount").val();
	var dir2_id = $("#dir2_id").val();
	var dir2_name = $("#dir2_name").val();
	var dir2_rate = $("#dir2_rate").val();
	var dir2_amount = $("#dir2_amount").val();

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
	if(dir1_id != ""){
		dataStr =dataStr+ ",\"dir1_id\":\"" + dir1_id + "\"";
	}
	if(dir1_name != ""){
		dataStr =dataStr+ ",\"dir1_name\":\"" + dir1_name + "\"";
	}
	if(dir1_rate != ""){
		dataStr =dataStr+ ",\"dir1_rate\":\"" + dir1_rate + "\"";
	}
	if(dir1_amount != ""){
		dataStr =dataStr+ ",\"dir1_amount\":\"" + dir1_amount + "\"";
	}
	if(dir2_id != ""){
		dataStr =dataStr+ ",\"dir2_id\":\"" + dir2_id + "\"";
	}
	if(dir2_name != ""){
		dataStr =dataStr+ ",\"dir2_name\":\"" + dir2_name + "\"";
	}
	if(dir2_rate != ""){
		dataStr =dataStr+ ",\"dir2_rate\":\"" + dir2_rate + "\"";
	}
	if(dir2_amount != ""){
		dataStr =dataStr+ ",\"dir2_amount\":\"" + dir2_amount + "\"";
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
	if(dir_count == 3){
		var dir3_id = $("#dir3_id").val();
		var dir3_name = $("#dir3_name").val();
		var dir3_rate = $("#dir3_rate").val();
		var dir3_amount = $("#dir3_amount").val();
		if(dir3_id != ""){
			dataStr =dataStr+ ",\"dir3_id\":\"" + dir3_id + "\"";
		}
		if(dir3_name != ""){
			dataStr =dataStr+ ",\"dir3_name\":\"" + dir3_name + "\"";
		}
		if(dir3_rate != ""){
			dataStr =dataStr+ ",\"dir3_rate\":\"" + dir3_rate + "\"";
		}
		if(dir3_amount != ""){
			dataStr =dataStr+ ",\"dir3_amount\":\"" + dir3_amount + "\"";
		}
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

function initDir(){
	var department = $("#department").val();
	var dataStr = "{\"query\":\"query\"";

	if(department != ""){
		dataStr =dataStr+ ",\"department\":\"" + department + "\"";
	}
	
	dataStr = dataStr + "}";
	$.ajax( {
		type : 'post',
		url : 'director/info',
		data : {json : dataStr},
		dataType : 'json',
		timeout: 10000,
		success : function(result) {
			if (result.returnFlag === "200") {
				var dir_count = result.dir_count;
				$("#dir_count").val(dir_count);
				if(dir_count == 3){
					$("#dir1_id").val(result.dirList[0].id);
					$("#dir1_name1").html(result.dirList[0].name+"奖金比例");
					$("#dir1_name").val(result.dirList[0].name);
					$("#dir1_name2").html(result.dirList[0].name+"奖金");
					$("#dir2_id").val(result.dirList[1].id);
					$("#dir2_name1").html(result.dirList[1].name+"奖金比例");
					$("#dir2_name").val(result.dirList[1].name);
					$("#dir2_name2").html(result.dirList[1].name+"奖金");
					$("#dir3_id").val(result.dirList[2].id);
					$("#dir3_name1").html(result.dirList[2].name+"奖金比例");
					$("#dir3_name").val(result.dirList[2].name);
					$("#dir3_name2").html(result.dirList[2].name+"奖金");
				}
				if(dir_count == 2){
					$("#dir1_id").val(result.dirList[0].id);
					$("#dir1_name1").html(result.dirList[0].name+"奖金比例");
					$("#dir1_name").val(result.dirList[0].name);
					$("#dir1_name2").html(result.dirList[0].name+"奖金");
					$("#dir2_id").val(result.dirList[1].id);
					$("#dir2_name1").html(result.dirList[1].name+"奖金比例");
					$("#dir2_name").val(result.dirList[1].name);
					$("#dir2_name2").html(result.dirList[1].name+"奖金");
					$("#dir3_id").val("");
					$("#dir3_name1").html("奖金比例");
					$("#dir3_name").val("");
					$("#dir3_rate").val("");
					$("#dir3_name2").html("奖金");
				}
				$("#dir_amount").val("0");
				$("#dir1_amount").val("0");
				$("#dir2_amount").val("0");
				$("#dir3_amount").val("0");
			}else {
				alert(result.returnMsg); 
			}
			showControl();
		},
		error : function(result) {
			alert("创建失败：" + result.returnMsg);
		}
	});
}

function showControl(){
	var type = $("#type option:selected").val();
	var dir_count = $("#dir_count").val();
	if(type == "11"){
		$("#div_pro").show();
		$("#div_dir").hide();
		$("#div_exp").hide();
	}
	if(type == "12"){
		$("#div_dir").show();
		$("#div_pro").hide();
		$("#div_exp").hide();
		$("#div_dir_amount").hide();
		$("#dir1").show();
		$("#dir2").show();
	    if(dir_count == 3){
	    	$("#dir3").show();
	    }else{
	    	$("#dir3").hide();
	    }
	}
	if(type == "13"){
		$("#div_exp").show();
		$("#div_dir").hide();
		$("#div_pro").hide();
	}
	if(type == "15"){
		$("#div_pro").show();
		$("#div_dir").show();
		$("#div_exp").show();
		$("#div_dir_amount").show();
		$("#dir1").hide();
		$("#dir2").hide();
		$("#dir3").hide();
	}
}