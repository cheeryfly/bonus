function download(){
	 var url = 'download/detail';
	 var department = $("#department").val();
	 var type =  $("#type option:selected").val();
	 var start =  $("#account_date_start").val();
	 var end = $("#account_date_end").val();
	 var form=$("<form>");//定义一个form表单
	 form.attr("style","display:none");
	 form.attr("target","");
	 form.attr("method","get");  //请求类型
	 form.attr("action",url);   //请求地址
	 $("body").append(form);//将表单放置在web中
	 
	 var input1=$("<input>");
	 input1.attr("type","hidden");
	 input1.attr("name","department");
	 input1.attr("value",department);
	 form.append(input1);
	 
	 var input2=$("<input>");
	 input2.attr("type","hidden");
	 input2.attr("name","type");
	 input2.attr("value",type);
	 form.append(input2);
	 
	 var input3=$("<input>");
	 input3.attr("type","hidden");
	 input3.attr("name","start");
	 input3.attr("value",start);
	 form.append(input3);
	 
	 var input4=$("<input>");
	 input4.attr("type","hidden");
	 input4.attr("name","end");
	 input4.attr("value",end);
	 form.append(input4);
	 
	 form.submit();//表单提交
}


function query(){

	var type = $("#type option:selected").val();
	var department = $("#department").val();
	var account_date_start =  $("#account_date_start").val();
	var account_date_end = $("#account_date_end").val();
	var param = {"type":type,"department":department,"account_date_start":account_date_start,"account_date_end":account_date_end};
	if(table == null){
		table =  $('#tb_test').DataTable( {
			  "serverSide":true,
			  "searching": false,
			  "scrollX": true,
			  "language": {  //对表格国际化  
				  "sProcessing": "处理中...",
			        "sLengthMenu": "显示 _MENU_ 项结果",
			        "sZeroRecords": "没有匹配结果",
			        "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
			        "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
			        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
			        "sInfoPostFix": "",
			        "sSearch": "搜索:",
			        "sUrl": "",
			        "sEmptyTable": "表中数据为空",
			        "sLoadingRecords": "载入中...",
			        "sInfoThousands": ",",
			        "oPaginate": {
			            "sFirst": "首页",
			            "sPrevious": "上页",
			            "sNext": "下页",
			            "sLast": "末页"
			        }
			  },
			 // "bDestroy": true
			  "columns": [
			        { data: 'department' },
			        { data: 'type' }  ,
			        { data: 'account_date' }  ,
			        { data: 'cardno' } , 
			        { data: 'account_item' }  ,
			        { data: 'income' }  ,
			        { data: 'account_rate' }  ,
			        { data: 'prize_rate' }  ,
			        { data: 'card_discount' }  ,
			        { data: 'dir_count' }  ,
			        { data: 'equity' }  ,
			        { data: 'pro_bonus_rate' } , 
			        { data: 'pro_bonus_amount' }  ,
			        { data: 'expense_rate' }  ,
			        { data: 'expense_amount' }  ,
			        { data: 'dir_rate' }  ,
			        { data: 'dir_amount' } ,
			        { data: 'remark' }
	          ],
			  "ajax": {
				 "url": 'report/detail',
					 "type": 'POST',
			     "data": function ( d ) {
			      return $.extend( {}, d, {
			    	    "type": $("#type option:selected").val(),
					        "department":$("#department").val(),
					        "account_date_start":$("#account_date_start").val(),
					        "account_date_end":$("#account_date_end").val()
			      } );
			    }
			  }
			});
	}
	else{
		table.settings()[0].ajax.data=param;
		table.ajax.url("report/detail").load();
	}
	
}

function initiateForm(){
	var dataStr =  "{\"pwd\":\""+"123"+"\"}";
	$.ajax( {
		type : 'post',
		url : 'user/getinfo',
		data : {json : dataStr},
		dataType : 'json',
		timeout: 10000,
		success : function(result) {
		    
			if (result.returnFlag === "200") {
				var showname = result.showname;
				var username = result.username;
				var role = result.role;
				if(role == 3){
					$("#department").attr("disabled",true);
					 $("#department").val(showname);
				}
			}else {
				alert(result.returnMsg); 
			}

		},
		error : function(result) {
			alert("失败" + result.returnMsg);

		}
	});
}