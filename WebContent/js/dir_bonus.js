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
					if(dir_count == 3){
						$("#dir1_id").val(result.dirList[0].id);
						$("#dir1_name").html(result.dirList[0].name+"奖金比例");
						$("#dir1_rate").val("0.5");
						$("#dir2_id").val(result.dirList[1].id);
						$("#dir2_name").html(result.dirList[1].name+"奖金比例");
						$("#dir2_rate").val("0.25");
						$("#dir3_id").val(result.dirList[2].id);
						$("#dir3_name").html(result.dirList[2].name+"奖金比例");
						$("#dir3_rate").val("0.25");
						$("#dir3").show();
					}
					if(dir_count == 2){
						$("#dir1_id").val(result.dirList[0].id);
						$("#dir1_name").html(result.dirList[0].name+"奖金比例");
						$("#dir1_rate").val("0.667");
						$("#dir2_id").val(result.dirList[1].id);
						$("#dir2_name").html(result.dirList[1].name+"奖金比例");
						$("#dir2_rate").val("0.333");
						$("#dir3").hide();
					}
				}else {
					alert(result.returnMsg); 
				}

			},
			error : function(result) {
				alert("创建失败：" + result.returnMsg);
			}
		});

}