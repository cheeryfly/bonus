function change(){
	var username = $("#username").val();
	var password = $("#password").val();
	var newpassword = $("#newpassword").val();
	var repeatword = $("#repeatword").val();
	
	if(newpassword != repeatword){
		alert("两次新密码不一致，请重新输入");
		$("#newpassword").val("");
		$("#repeatword").val("");
		$("#newpassword").focus();
		return;
	}else{
	
	var dataStr = "";
	dataStr = "{\"username\":\"" + username + "\",\"password\":\""+password+"\",\"newpassword\":\""+newpassword+"\"}";

	$.ajax( {
		type : 'post',
		url : 'login/change',
		data : {json : dataStr},
		dataType : 'json',
		timeout: 10000,
		success : function(result) {
		    
			if (result.returnFlag === "200") {
				window.location.href = 'index.html';
			}else{
				alert("失败" + result.returnMsg);
				$("#password").val("");
				$("#newpassword").val("");
				$("#repeatword").val("");
				$("#password").focus();
			}

		},
		error : function(result) {

			alert("失败" + result.returnMsg);

		}
	});
	}
}