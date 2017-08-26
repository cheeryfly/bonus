function runningCard_save(){
	var department = $("#department").val();
	var account_date = $("#account_date").val();
	var account_item = $("#account_item").val();
	var pro_id = $("#pro_id").val();
	var cardno = $("#cardno").val();
	var income = $("#income").val();
	var account_rate = $("#account_rate").val();
	var prize_rate = $("#prize_rate").val();
	var remark = $("#remark").val();
	var dir1_id = $("#dir1_id").val();
	var dir1_name = $("#dir1_name").val();
	var dir1_rate = $("#dir1_rate").val();
	var dir1_amount = $("#dir1_amount").val();
	var dir2_id = $("#dir2_id").val();
	var dir2_name = $("#dir2_name").val();
	var dir2_rate = $("#dir2_rate").val();
	var dir2_amount = $("#dir2_amount").val();
	var dir3_id = $("#dir3_id").val();
	var dir3_name = $("#dir3_name").val();
	var dir3_rate = $("#dir3_rate").val();
	var dir3_amount = $("#dir3_amount").val();
	var equity = $("#equity").val();
	var pro_bonus_amount = $("#pro_bonus_amount").val();
	var pro_bonus_rate = $("#pro_bonus_rate").val();
	var expense_amount = $("#expense_amount").val();
	var expense_rate = $("#expense_rate").val();
	var dir_amount = $("#dir_amount").val();
	var dir_rate = $("#dir_rate").val();

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
	if(pro_id != ""){
		dataStr =dataStr+ ",\"pro_id\":\"" + pro_id + "\"";
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
	
	dataStr = dataStr + "}";
	$.ajax( {
		type : 'post',
		url : 'runningcard/save',
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

function settlement_save(){
	var department = $("#department").val();
	var account_date = $("#account_date").val();
	var account_item = $("#account_item").val();
	var pro_id = $("#pro_id").val();
	var cardno = $("#cardno").val();
	var income = $("#income").val();
	var account_rate = $("#account_rate").val();
	var prize_rate = $("#prize_rate").val();
	var dir_count = $("#dir_count").val();
	var card_discount = $("#card_discount").val();
	var remark = $("#remark").val();
	var equity = $("#equity").val();
	var pro_bonus_amount = $("#pro_bonus_amount").val();
	var pro_bonus_rate = $("#pro_bonus_rate").val();
	var expense_amount = $("#expense_amount").val();
	var expense_rate = $("#expense_rate").val();
	var dir_amount = $("#dir_amount").val();
	var dir_rate = $("#dir_rate").val();

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
	if(pro_id != ""){
		dataStr =dataStr+ ",\"pro_id\":\"" + pro_id + "\"";
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
	if(card_discount != ""){
		dataStr =dataStr+ ",\"card_discount\":\"" + card_discount + "\"";
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
	
	dataStr = dataStr + "}";
	$.ajax( {
		type : 'post',
		url : 'settlement/save',
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