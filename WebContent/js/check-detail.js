function query(){
	var department = $("#department").val();
	if(department == '不限'){
		department = '';
	}
	var account_date = $("#account_date").val();
	var account_item = $("#account_item").val();
	var type = $("#type option:selected").val();
	
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
	dataStr =dataStr+ ",\"status\":\"" + "0" + "\"";
	dataStr = dataStr + "}";
	
	$.ajax( {
		type : 'post',
		url : 'check/query',
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

function generateTable(data) {
	var html;
	if (data.amount > 0) {
		jQuery('#detail-record').html('');
	} else {
		jQuery('#detail-record').html('<tr><td colspan=12>无待审批信息</td></tr>');
	}
	var department;
	var type_int;
	var type;
	var account_item;
	var account_date;
	var equity;
	var pro_bonus_amount;
	var expense_amount;
	var dir_amount;
	
	for ( var i = 0; i < data.amount; i++) {
		id = data.equitiesList[i].id;
		department=data.equitiesList[i].department;
		type_int=data.equitiesList[i].type;
		account_item=data.equitiesList[i].account_item;
		account_date=data.equitiesList[i].account_date;
		equity=data.equitiesList[i].equity;
		pro_bonus_amount=data.equitiesList[i].pro_bonus_amount;
		expense_amount=data.equitiesList[i].expense_amount;
		dir_amount=data.equitiesList[i].dir_amount;
	
		if (typeof(department) == "undefined")department='';
		if (typeof(type_int) == "undefined")type_int='';

		switch(type_int){
			case "1":
				type="运行卡";
				break;
			case "2":
				type="结算卡";
				break;
			case "11":
				type="提取项目奖金";
				break;
			case "12":
				type="提取所长奖金";
				break;
			case "13":
				type="成本报账";
				break;
			case "15":
				type="其他入账";
				break;
			default:
				type="未定义";
		}

		if (typeof(account_item) == "undefined")account_item='';
		if (typeof(account_date) == "undefined")account_date='';
		if (typeof(equity) == "undefined")equity='';
		if (typeof(pro_bonus_amount) == "undefined")pro_bonus_amount='';
		if (typeof(expense_amount) == "undefined")expense_amount='';
		if (typeof(dir_amount) == "undefined")dir_amount='';
		if(account_date != ''){
			var show_date = new Date(account_date);
			var mon = show_date.getMonth()+1;
			account_date = show_date.getFullYear()+'-'+mon+'-'+show_date.getDate();
		}

		html+='<tr>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="ID">'+id+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="设计所">'+department+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="入账类型">'+type+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="项目名称">'+account_item+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="到账时间">'+account_date+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="所权益">'+equity+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="项目奖金">'+pro_bonus_amount+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="报账成本">'+expense_amount+'</td>'
		html+='<td style="text-align:center;vertical-align:middle;" data-title="所长奖金">'+dir_amount+'</td>';
		html+='<td style="text-align:center;vertical-align:middle;" data-title="操作"><div align="center">';
		html+='<button class="btn btn-warning "  title="查看" onclick="showModal(this);"><i class="fa fa-pencil-square-o"></i>&nbsp;查看</button> &nbsp;&nbsp;';
		html+='<button class="btn btn-success"  title="审核通过" onclick="directPass(this);"><i class="fa fa-pencil-square-o"></i>&nbsp;审核通过</button>';
		html+='</div></td>';
		html+='</tr>';
    }               
    jQuery('#detail-record').html(html);
    $('table tr').find('td:eq(0)').hide();
}

function generateMD(data) {
	var html;
	if (data.amount == 0) {
		return;
	}
	var eq = data.equitiesList[0];
	var department = eq.department;
	var type_int = eq.type;
	var type;
	var account_item = eq.account_item;
	var account_date = eq.account_date;
	var cardno = eq.cardno;
	var income = eq.income;
	var account_rate = eq.account_rate;
	var prize_rate = eq.prize_rate;
	var card_discount = eq.card_discount;
	var dir_count = eq.dir_count;
	var equity = eq.equity;
	var pro_bonus_rate = eq.pro_bonus_rate;
	var pro_bonus_amount = eq.pro_bonus_amount;
	var expense_rate = eq.expense_rate;
	var expense_amount = eq.expense_amount;
	var dir_rate = eq.dir_rate;
	var dir_amount = eq.dir_amount;
	var remark = eq.remark;

	if (typeof (department) == "undefined")
		department = '';
	if (typeof (type_int) == "undefined")
		type_int = '';

	switch (type_int) {
	case "1":
		type = "运行卡";
		break;
	case "2":
		type = "结算卡";
		break;
	case "11":
		type = "提取项目奖金";
		break;
	case "12":
		type = "提取所长奖金";
		break;
	case "13":
		type = "成本报账";
		break;
	case "15":
		type = "其他入账";
		break;
	default:
		type = "未定义";
	}

	if (typeof (account_item) == "undefined")
		account_item = '';
	if (typeof (account_date) == "undefined")
		account_date = '';
	if (typeof (cardno) == "undefined")
		cardno = '';
	if (typeof (income) == "undefined")
		income = '';
	if (typeof (account_rate) == "undefined")
		account_rate = '';
	if (typeof (prize_rate) == "undefined")
		prize_rate = '';
	if (typeof (card_discount) == "undefined")
		card_discount = '';
	if (typeof (dir_count) == "undefined")
		dir_count = '';
	if (typeof (equity) == "undefined")
		equity = '';
	if (typeof (pro_bonus_rate) == "undefined")
		pro_bonus_rate = '';
	if (typeof (pro_bonus_amount) == "undefined")
		pro_bonus_amount = '';
	if (typeof (expense_rate) == "undefined")
		expense_rate = '';
	if (typeof (expense_amount) == "undefined")
		expense_amount = '';
	if (typeof (dir_rate) == "undefined")
		dir_rate = '';
	if (typeof (dir_amount) == "undefined")
		dir_amount = '';
	if (typeof (remark) == "undefined")
		remark = '';

	if (account_date != '') {
		var show_date = new Date(account_date);
		var mon = show_date.getMonth() + 1;
		account_date = show_date.getFullYear() + '-' + mon + '-'
				+ show_date.getDate();
	}

	$('#department-md').html(department);
	$('#type-md').html(type);
	$('#account_item-md').html(account_item);
	$('#account_date-md').html(account_date);
	$('#cardno-md').html(cardno);
	$('#income-md').html(income);
	$('#account_rate-md').html(account_rate);
	$('#prize_rate-md').html(prize_rate);
	$('#card_discount-md').html(card_discount);
	$('#dir_count-md').html(dir_count);
	$('#equity-md').html(equity);
	$('#pro_bonus_rate-md').html(pro_bonus_rate);
	$('#pro_bonus_amount-md').html(pro_bonus_amount);
	$('#expense_rate-md').html(expense_rate);
	$('#expense_amount-md').html(expense_amount);
	$('#dir_rate-md').html(dir_rate);
	$('#dir_amount-md').html(dir_amount);
	$('#remark-md').html(remark);
}


function showModal(obj) {
	var id = $(obj).parent().parent().parent().find('td:eq(0)').text();
	var header = '入账详情';
	var form = $('<div class="form-group">'
			+ '					<table id="tb_detail" class="table m-table table-bordered table-hover table-heading">'
			+

			'					<tbody id="bd_main">                                                                                 '
			+ '						<tr>                                                                                  '
			+ '							<td style="text-align:center;vertical-align:middle;"><b>设计所</b></td>             '
			+ '							<td style="text-align:center;vertical-align:middle;" id="department-md"></td>       '
			+ '							<td style="text-align:center;vertical-align:middle;"><b>入账类型</b></td>           '
			+ '							<td style="text-align:center;vertical-align:middle;" id="type-md"></td>             '
			+ '						</tr>                                                                                 '
			+ '						<tr>                                                                                  '
			+ '							<td style="text-align:center;vertical-align:middle;"><b>项目名称</b></td>           '
			+ '							<td style="text-align:center;vertical-align:middle;" id="account_item-md"></td>     '
			+ '							<td style="text-align:center;vertical-align:middle;"><b>到账时间</b></td>           '
			+ '							<td style="text-align:center;vertical-align:middle;" id="account_date-md"></td>             '
			+ '						</tr>                                                                                 '
			+ '						<tr>                                                                                  '
			+ '							<td style="text-align:center;vertical-align:middle;"><b>卡号</b></td>               '
			+ '							<td style="text-align:center;vertical-align:middle;" id="cardno-md"></td>           '
			+ '							<td style="text-align:center;vertical-align:middle;"><b>所长人数</b></td>           '
			+ '							<td style="text-align:center;vertical-align:middle;" id="dir_count-md"></td>        '
			+ '						</tr>                                                                                 '
			+ '						<tr>                                                                                  '
			+ '							<td style="text-align:center;vertical-align:middle;"><b>到账产值</b></td>           '
			+ '							<td style="text-align:center;vertical-align:middle;" id="income-md"></td>           '
			+ '							<td style="text-align:center;vertical-align:middle;"><b>核算比例</b></td>           '
			+ '							<td style="text-align:center;vertical-align:middle;" id="account_rate-md"></td>     '
			+ '						</tr>                                                                                 '
			+ '						<tr>                                                                                  '
			+ '							<td style="text-align:center;vertical-align:middle;"><b>奖励系数</b></td>           '
			+ '							<td style="text-align:center;vertical-align:middle;" id="prize_rate-md"></td>       '
			+ '							<td style="text-align:center;vertical-align:middle;"><b>已核算系数</b></td>         '
			+ '							<td style="text-align:center;vertical-align:middle;" id="card_discount-md"></td>    '
			+ '						</tr>                                                                                 '
			+ '						<tr>                                                                                  '
			+ '							<td style="text-align:center;vertical-align:middle;"><b>所权益</b></td>             '
			+ '							<td  colspan="3"  style="text-align:center;vertical-align:middle;" id="equity-md"></td>           '
			+ '						</tr>                                                                                 '
			+ '						<tr>                                                                                  '
			+ '							<td style="text-align:center;vertical-align:middle;"><b>项目奖金比例</b></td>       '
			+ '							<td style="text-align:center;vertical-align:middle;" id="pro_bonus_rate-md"></td>   '
			+ '							<td style="text-align:center;vertical-align:middle;"><b>项目奖金</b></td>           '
			+ '							<td style="text-align:center;vertical-align:middle;" id="pro_bonus_amount-md"></td> '
			+ '						</tr>                                                                                 '
			+ '						<tr>                                                                                  '
			+ '							<td style="text-align:center;vertical-align:middle;"><b>报账成本比例</b></td>       '
			+ '							<td style="text-align:center;vertical-align:middle;" id="expense_rate-md"></td>     '
			+ '							<td style="text-align:center;vertical-align:middle;"><b>报账成本</b></td>           '
			+ '							<td style="text-align:center;vertical-align:middle;" id="expense_amount-md"></td>   '
			+ '						</tr>                                                                                 '
			+ '						<tr>                                                                                  '
			+ '							<td style="text-align:center;vertical-align:middle;"><b>所长奖金比例</b></td>       '
			+ '							<td style="text-align:center;vertical-align:middle;" id="dir_rate-md"></td>         '
			+ '							<td style="text-align:center;vertical-align:middle;"><b>所长奖金</b></td>           '
			+ '							<td style="text-align:center;vertical-align:middle;" id="dir_amount-md"></td>       '
			+ '						</tr>                                                                                 '
			+ '						<tr>                                                                                  '
			+ '							<td style="text-align:center;vertical-align:middle;"><b>备注</b></td>               '
			+ '							<td colspan="3" style="text-align:center;vertical-align:middle;" id="remark-md"></td>'
			+ '						</tr>                                                                                 '
			+ '					</tbody>                                                                                '
			+ '				</table>                                                                                  '
			+ '</div>'+
			'<div class="well">                                                           '+
			'	<h3>审核意见</h3>                                                          '+
			'	<div class="row show-grid-forms">                                           '+
			'		<div class="form-group has-error has-feedback">                           '+
			'			<div class="col-sm-12">                                                 '+
			'				<textarea class="form-control" rows="3" id="check_remark"></textarea> '+
			'			</div>                                                                  '+
			'		</div>                                                                    '+
			'	</div>                                                                      '+
			'</div>                                                                       ');
	
	
	var button = $('<div class="form-group"><div class="col-sm-offset-2 col-sm-2">'+
			'<button id="btn_pass" type="cancel" class="btn btn-primary btn-label-left"><span><i class="fa fa-clock-o "></i></span>通过</button></div><div class="col-sm-2"><button id="btn_deny" type="submit" class="btn btn-warning btn-label-left"><span><i class="fa fa-clock-o txt-danger"></i></span>驳回</button></div></div>');
		

	var modalbox = $('#modalbox');
	modalbox.find('.modal-header-name span').html(header);
	modalbox.find('.devoops-modal-inner').html(form);
	modalbox.find('.devoops-modal-bottom').html(button);
	modalbox.fadeIn('fast');
	$('body').addClass("body-expanded");
	
	
	var dataStr = "{\"query\":\"query\"";
	if(id != ""){
		dataStr =dataStr+ ",\"id\":\"" + id + "\"";
	}
	dataStr = dataStr + "}";
	
	$.ajax( {
		type : 'post',
		url : 'check/query',
		data : {json : dataStr},
		dataType : 'json',
		timeout: 10000,
		success : function(result) {
		    
			if (result.returnFlag === "200") {
				generateMD(result);
			}
			if(result.returnFlag === "500"){
				jQuery('#tb_detail').html('<tr><td colspan=12>'+result.returnMsg+'</td></tr>');
			}

		},
		error : function(result) {
			alert("失败" + result.returnMsg);
			jQuery('#tb_detail').html('<tr><td >'+result.returnMsg+'</td></tr>');

		}
	});
	
	$('#btn_pass').on('click', function(e) {
		e.preventDefault();
		check_pass(id);
	});
	$('#btn_deny').on('click', function(e) {
		e.preventDefault();
		check_deny(id);
	});
}

function directPass(obj){
	var id = $(obj).parent().parent().parent().find('td:eq(0)').text();
	check_pass(id);
}

function check_pass(id){
	var check_remark = $('#check_remark').val();
	var dataStr = "{\"action\":\"1\"";
	if(id != ""){
		dataStr =dataStr+ ",\"id\":\"" + id + "\"";
	}
	if(check_remark != ""){
		dataStr =dataStr+ ",\"check_remark\":\"" + check_remark + "\"";
	}
	dataStr = dataStr + "}";
	$.ajax( {
		type : 'post',
		url : 'check/submit',
		data : {json : dataStr},
		dataType : 'json',
		timeout: 10000,
		success : function(result) {
		    
			if (result.returnFlag === "200") {
				CloseModalBox();
				query();
			}
			if(result.returnFlag === "500"){
				alert("失败" + result.returnMsg);
			}

		},
		error : function(result) {
			alert("失败" + result.returnMsg);

		}
	});
}
function check_deny(id){
	var check_remark = $('#check_remark').val();
	var dataStr = "{\"action\":\"2\"";
	if(id != ""){
		dataStr =dataStr+ ",\"id\":\"" + id + "\"";
	}
	if(check_remark != ""){
		dataStr =dataStr+ ",\"check_remark\":\"" + check_remark + "\"";
	}
	dataStr = dataStr + "}";
	$.ajax( {
		type : 'post',
		url : 'check/submit',
		data : {json : dataStr},
		dataType : 'json',
		timeout: 10000,
		success : function(result) {
		    
			if (result.returnFlag === "200") {
				CloseModalBox();
				query();
				
			}
			if(result.returnFlag === "500"){
				alert("失败" + result.returnMsg);
			}

		},
		error : function(result) {
			alert("失败" + result.returnMsg);

		}
	});
}



