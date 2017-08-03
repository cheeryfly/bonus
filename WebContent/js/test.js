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