function query(){

	var type = $("#type option:selected").val();
	var department = $("#department").val();
	var account_date_start =  $("#account_date_start").val();
	var account_date_end = $("#account_date_end").val();
	var param = {"type":type,"department":department,"account_date_start":account_date_start,"account_date_end":account_date_end};
	if(table == null){
		
		table =  $('#tb_test').DataTable( {
			  "serverSide":true,
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
				 "url": 'report/test',
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
		table.ajax.url("report/test").load();
	}
	
}