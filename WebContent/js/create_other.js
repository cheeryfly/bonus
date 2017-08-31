function other_save(){
	var department = $("#department").val();
	var account_date = $("#account_date").val();
	var account_item = $("#account_item").val();
	var type = $("#type option:selected").val();
	var remark = $("#remark").val();
	var equity = $("#equity").val();
	var dir_count = $("#dir_count").val();
	var pro_bonus_amount = $("#pro_bonus_amount").val();
	var expense_amount = $("#expense_amount").val();
	var dir_amount = $("#dir_amount").val();
	
	var dir1_id = $("#dir1_id").val();
	var dir1_name = $("#dir1_name").val();
	var dir1_rate = $("#dir1_rate").val();
	var dir1_amount = $("#dir1_amount").val();
	var dir2_id = $("#dir2_id").val();
	var dir2_name = $("#dir2_name").val();
	var dir2_rate = $("#dir2_rate").val();
	var dir2_amount = $("#dir2_amount").val();

	var dataStr = "{\"query\":\"query\"";

	if(department != ""){
		dataStr =dataStr+ ",\"department\":\"" + department + "\"";
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
		url : 'other/save',
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
			alert("创建失败：" + result.returnMsg);
		}
	});

}

function initDir(){
	var department = $("#department").val();
	var type =  $("#type option:selected").val();
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
