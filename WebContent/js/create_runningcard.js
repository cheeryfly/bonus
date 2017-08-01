function runningCard_save(){
	var department = $("#department").val();
	var account_date = $("#account_date").val();
	var account_item = $("#account_item").val();
	var pro_id = $("#pro_id").val();
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