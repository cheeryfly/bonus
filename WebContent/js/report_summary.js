function query(){
	var department = $("#department").val();
	var year =  $("#year").val();
	var month = $("#month option:selected").val();
	var param = {"department":department,"year":year,"month":month};
	if(table == null){
		table =  $('#tb_test').DataTable( {
			  "serverSide":true,
			  "searching": false,
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
			        { data: 'year' }  ,
			        { data: 'month' }  ,
			        { data: 'income' }  ,
			        { data: 'equity' }  ,
			        { data: 'pro_bonus' }  ,
			        { data: 'expense' }  ,
			        { data: 'dir_bonus' }
	          ],
			  "ajax": {
				 "url": 'report/balance',
				 "type": 'POST',
			     "data": function ( d ) {
			      return $.extend( {}, d, {
					   "department":$("#department").val(),
					   "year":$("#year").val(),
					   "month":$("#month option:selected").val()
			      } );
			    }
			  }
			});
	}
	else{
		table.settings()[0].ajax.data=param;
		table.ajax.url("report/bonus").load();
	}
	
}