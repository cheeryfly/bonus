var Login = function () {
    
    return {
        //main function to initiate the module
        init: function () {
           $('#submitForm').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            rules: {
	                name: {
	                    required: true
	                },
	                pwd: {
	                    required: true
	                }
	            },

	            messages: {
	                name: {
	                    required: "请输入用户名."
	                },
	                pwd: {
	                    required: "请输入密码."
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   
	                alert("请正确填写用户名与密码");
	            },
/*
	            highlight: function (element) { // hightlight error inputs
	            },

	            success: function (label) {
	            },

	            errorPlacement: function (error, element) {
	            },
*/
	            submitHandler: function (form) {
	            	//form校验
	            	/*
	            	if(!checkInput("submitForm")){
	            		alert("请正确填写用户名与密码"); 
	            	    return False;}
	            	 */   

	//            	$("#login_sub").attr('disabled', '1');
			
				    var name = document.getElementById("name").value;
				    var pwd = document.getElementById("pwd").value;
					var dataStr = "";
					dataStr = "{\"name\":\"" + name + "\",\"pwd\":\""+pwd+"\"}";
				
					$.ajax( {
						type : 'post',
						url : 'login/validate',
						data : {json : dataStr},
						dataType : 'json',
						timeout: 10000,
						success : function(result) {
						    
							if (result.returnFlag === "200") {
								var role = result.role;
								var event = result.event;
								/*
								if(role == 3){
									window.location.href = 'query.html';
								}else{
									window.location.href = 'index.html';
								}
								*/
								window.location.href = 'index.html';
							}else {
								alert(result.returnMsg); 
								$("#name").val('');
								$("#name").focus();
								$("#pwd").val('');
								
							}
				
						},
						error : function(result) {

							alert("失败" + result.returnMsg);
				
						}
					});
               
            }
	     });
	        
        } 
    };
}();