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
						$("#dir1_rate").val("0.5");
						$("#dir1_name2").html(result.dirList[0].name+"奖金");
						$("#dir2_id").val(result.dirList[1].id);
						$("#dir2_name1").html(result.dirList[1].name+"奖金比例");
						$("#dir2_name").val(result.dirList[1].name);
						$("#dir2_rate").val("0.25");
						$("#dir2_name2").html(result.dirList[1].name+"奖金");
						$("#dir3_id").val(result.dirList[2].id);
						$("#dir3_name1").html(result.dirList[2].name+"奖金比例");
						$("#dir3_name").val(result.dirList[2].name);
						$("#dir3_rate").val("0.25");
						$("#dir3_name2").html(result.dirList[2].name+"奖金");
						$("#dir3").show();
						var account_rate = $("#account_rate").val();
						var expense_rate = $("#expense_rate").val();
						var pro_bonus_rate = 0;
						var dir_rate = 0.05;
						pro_bonus_rate = account_rate - expense_rate - dir_rate;
						pro_bonus_rate = parseFloat(pro_bonus_rate.toFixed(2));
						$("#dir_rate").val(dir_rate);
						$("#pro_bonus_rate").val(pro_bonus_rate);
					}
					if(dir_count == 2){
						$("#dir1_id").val(result.dirList[0].id);
						$("#dir1_name1").html(result.dirList[0].name+"奖金比例");
						$("#dir1_name").val(result.dirList[0].name);
						$("#dir1_rate").val("0.667");
						$("#dir1_name2").html(result.dirList[0].name+"奖金");
						$("#dir2_id").val(result.dirList[1].id);
						$("#dir2_name1").html(result.dirList[1].name+"奖金比例");
						$("#dir2_name").val(result.dirList[1].name);
						$("#dir2_rate").val("0.333");
						$("#dir2_name2").html(result.dirList[1].name+"奖金");
						$("#dir3_id").val("");
						$("#dir3_name1").html("奖金比例");
						$("#dir3_name").val("");
						$("#dir3_rate").val("");
						$("#dir3_name2").html("奖金");
						$("#dir3").hide();
						var dir_count = $("#dir_count").val();
						var account_rate = $("#account_rate").val();
						var expense_rate = $("#expense_rate").val();
						var pro_bonus_rate = 0;
						var dir_rate = 0.0395;
						pro_bonus_rate = account_rate - expense_rate - dir_rate;
						pro_bonus_rate = parseFloat(pro_bonus_rate.toFixed(4));
						$("#dir_rate").val(dir_rate);
						$("#pro_bonus_rate").val(pro_bonus_rate);
					}
					calculate();
				}else {
					alert(result.returnMsg); 
				}

			},
			error : function(result) {
				alert("创建失败：" + result.returnMsg);
			}
		});
}


