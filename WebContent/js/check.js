/**
 * 公共校验函数
 * 校验所有带有check属性的表单元素
 */
function checkInput(parent_id){
	$(".error").removeClass('error');
	$(".help-block").hide();
	var parent = $(parent_id);
	var err=0;
	$(":input[check]",parent).each(function(){
		// if( err>0 ) return false;
		var check = $(this).attr("check");
		if(!check) return false;
		var checks= check.split(",");
		var value = $(this).val();
		if(checks[1]=="n" && !value){
			if(err==0) $(this).focus();
			showError($(this),"不能为空");
			err = 1;
			// return false;
		}else if(checks[1]=="y" && !value){
			return true;
		}	
		switch(checks[0]){
		case "date" :
			if ( ! CheckIsDateTime(value,checks[2]||"yyyy-mm-dd") ){
				if(err==0) $(this).focus();
				showError($(this),"日期格式错误,应该为"+(checks[2]||"yyyy-mm-dd"));
				err = 1;
				// return false;
			}
			break;
		case "number" :		
			if( !checkNumber(value,checks[2],checks[3]) ){
				if(err==0) $(this).focus();
				showError($(this),"数字格式错误,应该为小数点前"+checks[2]+"位,后"+checks[3]+"位");
				err = 1;
				// return false;				
			} 
			break;
		case "email" :
			if( !checkEmail(value)) {
				if(err==0) $(this).focus();
				showError($(this),"email格式有误，请检查");
				err = 1;
			}
			break;
		}
	});
	if(err>0)
		return false;
	return true;
}

function showError(obj,text){
	if(obj.parent().hasClass(".controls") || obj.parents(".controls").length>0) {
		obj.parents(".controls").find(".help-block").remove();
		obj.parents(".controls").append("<span class='help-block'>"+text+"</span>");
		obj.parents(".control-group").addClass('error');
	} else {
		obj.parent().append("<span class='help-block text_red'>"+text+"</span>");
	}
 //    var width = obj.width();
 //    var top = obj.offset().top;
 //    var left = obj.offset().left;
 //    if(width < 100)width = 100;
	// var s = [];
	// s.push("<div id=errTip ");
	// s.push("style='z-index:6;width: 120; position: absolute; background-color: #ffffff; border: 2px solid red; display:none; padding: 2;'>");
	// s.push(text);
	// s.push("</div>");
	// var o = $(s.join(""));
	// $(document.body).append(o);
	// o.css("left",left)
	// o.css("top",top+20)
	// o.css("width",width+8);
	// //o.focus();
	// o.blur(function(){
	// 	$(this).hide();
	// });
	// o.click(function(){
	// 	$(this).hide();
	// });
	// o.show();
}

/**
 * email格式校验
 * @param {Object} email 邮件地址内容
 */
function checkEmail(email){
    if ((email.length > 128) || (email.length < 6)) {
        return false;
    }
    var format = /^[A-Za-z0-9+]+[A-Za-z0-9\.\_\-+]*@([A-Za-z0-9\-]+\.)+[A-Za-z0-9]+$/;
    if (!email.match(format)) {
        return false;
    }
    return true;
}

/**
 * 校验number合法性
 * str : 需要检查的字符串
 * a   : 小数点前保留位数
 * b   : 小数点保留位数
 */
function checkNumber(str,a,b){
	if(!str)
		return false;
	if(!/\-?[0-9]+(\.[0-9]+)?$/g.test(str)){
		return false;
	}
	
	var num = str.split(".");
	if(num[0].length > a)
		return false;
	if(num.length > 1){
		if(b==0 && num[1] != 0){
			return false;
		}
		if(b!=0 && num[1].length > b ){
			return false;
		}
	}
	return true;
	
}

/**
 * 检查日期格式 20XX-XX-XX
 * @param str 
 * @return
 */
function checkDate(str)
{
    if(str == "")
        return true;
    var pattern = /^((\d{4})|(\d{2}))-(\d{2})-(\d{2})$/g;
    if(!pattern.test(str)){
        return false;
    }
    var arrDate = str.split("-");
    if(parseInt(arrDate[0],10) < 1000){
        return false;}
    if(parseInt(arrDate[1],10) >12){
        return false;}
    if(parseInt(arrDate[2],10) >31){
        return false;}
    return true;
}

//检查日期（YYYYMMDD）格式
function checkDateFmt(dateStr,fmtStr)
{
    var dateStrNew = "";

    if (dateStr == "" ) return false;
    if (dateStr.length == 8){
        dateStrNew = dateStr.substring(0,4) + "-" + dateStr.substring(4,6) + "-" + dateStr.substring(6,8);
        if (CheckIsDateTime(dateStrNew,fmtStr)){
            return true;
        }else{
			return false;
        }
    }
	else if (dateStr.length == 10){
        if (!CheckIsDateTime(dateStr,fmtStr)){
			return false;
        }else 
		    return true;
    }else{
    	return false;
    }
}

function CheckIsDateTime( ADtStr , FmtStr )
{
    var res=true;
    if ( ADtStr=="" || FmtStr=="")  return false

    var fstr = FmtStr.toUpperCase();
    var i =0 ; var p = 0; var Li = 0; var Lp = 0;
    var y = -1 ;var m = -1; var d = -1; var h= -1; var mi= -1; var s=-1;
    while( i < fstr.length )
    {
        var fmtch = fstr.substring(i,i+1);       // current
        var fmtchn = fstr.substring(i+1,i+2);    // next
        var adtch = ADtStr.substring(p, p+1);    // current
        var adtchn = ADtStr.substring(p+1, p+2); // next
        if ( fmtch=="Y" ) // get year
        {
            if ( fmtchn=="Y" )
            {
                var y3=fstr.substring(i+2,i+3);  // next
                var y4=fstr.substring(i+3,i+4);  // next
                if ( y3 =="Y" && y4 =="Y" ){ Li = 4; Lp = 4; }
                else { Li=2; Lp=2; }
                y = GetIntegerValue( ADtStr, p, Lp );
                if ( !( y>=1900 && y<=9999 ) ) res = false;
            }else
            {
                if ( fmtch == adtch ) { Li=1; Lp=1 }
                else{   res =false; }
            }
        }else // get m .d . h .mi s
        {
            if ( fmtchn == fmtch ) Li = 2; else Li = 1;
            if ( IsDigital( adtchn ) ) Lp = 2 ;else Lp = 1;
            var avalue = GetIntegerValue( ADtStr, p, Lp);

            if ( fmtch=="M" )
            {
                if ( fmtchn=="I" )// Minute
                {
                    Li=2;  // special
                    mi = avalue;
                    if ( !( mi>=0 && mi<=59 ) ) res = false;
                }else // default is as Month
                {
                    m = avalue;
                    if ( !( m>=1 && m<=12 ) ) res = false;
                }
            }else
            if ( fmtch=="D" )
            {
                d = avalue;
                if ( !( d>=1 && d<=31 ) ) res = false;
            }else
            if ( fmtch=="H" )
            {
                h = avalue;
                if ( !( h>=0 && h<=23 ) ) res = false;
            }else
            if ( fmtch=="S" )
            {
                s = avalue;
                if ( !( s>=0 && s<=59 ) ) res = false;
            }else
            {
                if ( fmtch == adtch ) { Li=1; Lp=1 }
            }
        }
        i += Li; p+=Lp;
        if (! res ) return res;
    }
    if ( res && i != fstr.length || p != ADtStr.length ) res = false;
    if ( ! ( res &&  m!=-1 && d!=-1 && d<=GetDaysOfMonth(m, y ) ) )  res = false;
    return res;
}

function GetDaysOfMonth(AMonth,AYear)
{
    var monthdays = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30,31, 30, 31)
    var days = -1;
    if ( AMonth>=1 && AMonth<=12 )  days = monthdays[AMonth -1 ];
    if (AMonth==2 && IsLeapYear(AYear) ) days=29;
    return (days);
}

//计算日期
function AddDate(n){
	//获取系统时间 
	var target = new Date(); 
	target.setDate(target.getDate()+n);// 取得系统时间的前一天,重点在这里,负数是前几天,正数是后几天
	var target_M=target.getMonth();
	target_M++; 
	var LSTR_MM=target_M >= 10?target_M:("0"+target_M) 
	var target_D=target.getDate(); 
	var LSTR_DD=target_D >= 10?target_D:("0"+target_D) 
	// 得到最终结果
	target_STR = target.getFullYear() + "-" + LSTR_MM + "-" + LSTR_DD; 
	return target_STR;
}

//计算日期加上天数后的日期
function addDays(dateTemp, days) {
    var dateTemp = dateTemp.split("-");
    var nDate = new Date(dateTemp[1] + '-' + dateTemp[2] + '-' + dateTemp[0]); //转换为MM-DD-YYYY格式  
    var millSeconds = Math.abs(nDate) + (days * 24 * 60 * 60 * 1000);
    var rDate = new Date(millSeconds);
    var year = rDate.getFullYear();
    var month = rDate.getMonth() + 1;
    if (month < 10) month = "0" + month;
    var date = rDate.getDate();
    if (date < 10) date = "0" + date;
    return (year + "-" + month + "-" + date);
} 

//计算日期之差
function getDays(strDateStart,strDateEnd){
   var strSeparator = "-"; // 日期分隔符
   var oDate1;
   var oDate2;
   var iDays;
   oDate1= strDateStart.split(strSeparator);
   oDate2= strDateEnd.split(strSeparator);
   var strDateS = new Date(oDate1[0], oDate1[1]-1, oDate1[2]);
   var strDateE = new Date(oDate2[0], oDate2[1]-1, oDate2[2]);
   iDays = parseInt(Math.abs(strDateS - strDateE ) / 1000 / 60 / 60 /24)// 把相差的毫秒数转换为天数
   return iDays ;
}

//计算日期之差
function getRealDays(strDateStart,strDateEnd){
   var strSeparator = "-"; // 日期分隔符
   var oDate1;
   var oDate2;
   var iDays;
   oDate1= strDateStart.split(strSeparator);
   oDate2= strDateEnd.split(strSeparator);
   var strDateS = new Date(oDate1[0], oDate1[1]-1, oDate1[2]);
   var strDateE = new Date(oDate2[0], oDate2[1]-1, oDate2[2]);
   iDays = parseInt((strDateE - strDateS) / 1000 / 60 / 60 /24)// 把相差的毫秒数转换为天数
   return iDays ;
}

function IsLeapYear(AYear)
{
    var b = ((AYear % 4)==0) && ((AYear % 100)!=0) || ((AYear % 400)==0) ;
    return (b);
}

function GetIntegerValue(Str, pos, len )
{
    var result = 0;
    var astr = Str.substring(pos, pos+len)
    if ( CheckIsInteger( astr ) )
    {
        result = astr ;
    } else { result = -1 }
    return result;
}

function CheckIsInteger( str )
{
    var Val = $.trim(str);
    var myRegExp = /^([0-9]+)$/;
    return (myRegExp.test(Val))
}

function IsDigital( achar )
{
    var achar = achar ;
    return( achar >="0" && achar <="9")
}

/**
 * 返回当前日期的字符串
 * @return
 */
function currentDate(){
	var now= new Date();
	var year=now.getYear();
	var month=now.getMonth()+1;
	var day=now.getDate();
	if(month<10){
		month="0"+month;
	}
	if(day<10){
		day="0"+day;
	}
	return year+'-'+month+'-'+day;
}

/**
*  Number 类型 数字格式转换函数,示例如下
*          ‘0′ - (123456) 
*          ‘0.00′ - (123456.78) 
*          ‘0.0000′ - (123456.7890) 
*          ‘0,000′ - (123,456) 
*          ‘0,000.00′ - (123,456.78) 
*          ‘0,0.00′ - (123,456.78) 
*/
Number.prototype.format = function(format) {
	var hasComma = -1 < format.indexOf(','),
		psplit = format.split('.'),
		that = this;
	
	var snum = that.toString().split('.');
	that = parseFloat(Math.round(that*10000))/10000;
	
	if (2 == psplit.length) {
		if(snum.length > 1 && snum[1].length>=4)
			that = that.toFixed(psplit[1].length);
	}
	// 错误:包含2个及以上的'.'
	else if (2 < psplit.length) {
		throw('错误的格式:'  + format);
	}
	// 移除精度
	else {
		that = that.toFixed(0);
	}

	// 获取精度调整过后的数据
	var fnum = that.toString();
	if (hasComma) {
		psplit = fnum.split('.');

		var cnum = psplit[0];
		var positive = '';//符号
		if(cnum.indexOf('-')==0){
			positive = '-';
			cnum = cnum.substr(1);//去掉符号
		}
		var parr = [],
		j = cnum.length,
		m = Math.floor(j / 3),	// m => 完整的3位数字的个数
		n = cnum.length % 3 || 3; 	// n => 剩余不完整的个数

		for (var i = 0; i < j; i += n) {
			if (i != 0) {n = 3;}
			parr[parr.length] = cnum.substr(i, n);
			m -= 1;
		}

		fnum = positive + parr.join(',');
		if (psplit[1]) {fnum += '.' + psplit[1];}
	}
	return fnum;
};

Date.prototype.format = function(format)
{
var o =
{
"M+" : this.getMonth()+1, //month
"d+" : this.getDate(), //day
"h+" : this.getHours(), //hour
"m+" : this.getMinutes(), //minute
"s+" : this.getSeconds(), //second
"q+" : Math.floor((this.getMonth()+3)/3), //quarter
"S" : this.getMilliseconds() //millisecond
}


if(/(y+)/.test(format))
{
format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
}

for(var k in o)
{
if(new RegExp("("+ k +")").test(format))
{
format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
}
}
return format;
};
