function getContextPath()
{
//	if(document.location.href.indexOf("000861.com/")!=-1)
//	{
//		return "";
//	}
	return "/msy-web";
} 

Array.prototype.remove = function(index)
{
	if(isNaN(index)||(index>=this.length)||(index<0)) return false;
	this.splice(index,1);
	return true;
}
Array.prototype.del=function(n){if(n<0){return this;}else{return this.slice(0,n).concat(this.slice(n+1,this.length));}}
Array.prototype.indexOf = function(value)
{
	for(var i=0;i<this.length;i++)
	{
		if(typeof(value)=="function")
		{
			if(value.equals(this[i])) return i;
		}else if(value==this[i]){ 
			return i;
		}
	}
	return -1;
}
Array.prototype.contains = function(value){return this.indexOf(value)>=0;}
Array.prototype.clear = function(){while(this.length>0) this.remove(this.length-1);}
Array.prototype.add = function(index,value)
{
	if(value==undefined)
	{ 
		this.put(index);
	}else{
		var len = this.length;
		this.push(this[len-1]);
		for(var i=len-1;i>index;i--) {this[i] = this[i-1];}
		this[index] = value;
	}
}
Array.prototype.put = function(value)
{
	if(!this.contains(value)) this.push(value);
}
Array.prototype.circle = function(degressive)
{
	if(degressive)
	{
	  	var a = this[0];
	  	for(var i=0;i<this.length-1;i++){ this[i] = this[i+1];}
	  	this[this.length-1] = a;
	}else{
	  	var a = this[this.length-1];
	  	for(var i=this.length-1;i>0;i--){ this[i] = this[i-1];}
	  	this[0] = a;
	}
}

/**
 * 判斷是否是正整數
 * @param {Object} s
 */
function isPos(s)
{
	var p = /^[0-9]*[1-9][0-9]*$/;
	return !(p.exec(s)==null);
}


function handleAjaxRequest(resultBean, status, XMLHttpRequest) {
	if (resultBean.resultCode == '0001') {
		checkLoginError_addbackdrop();
		alert(resultBean.resultMsg);
		window.top.location.href = getContextPath() + "/front/sys/";
		return false;
	}
	return true;
}
	

function topLoading()
{
	if($("#topLoadingDiv").html()==undefined)
	{
		$("body").append("<div id='topLoadingDiv' class='top_loading'><img border='0' src='"+getContextPath()+"/front/resources/images/loading.gif'/></div>");
	}
	$("#topLoadingDiv").attr("class","top_loading");
	$("#topLoadingDiv").css("left",(document.documentElement.clientWidth-32)/2+"px");
	$("#topLoadingDiv").css("top",(document.documentElement.clientHeight-32)/2+"px");
}

function topLoaded()
{
	if($("#topLoadingDiv").html()!=undefined)
	{
		$("#topLoadingDiv").attr("class","top_loaded");
	}
}

function appendTrToTBody(rowNo,tBody,data,cls)
{
	var html="<tr class='"+(rowNo%2==0?'row_0':'row_1')+"'>";
	var s=data.length;
	if(cls==undefined || cls==null) cls=new Array(s);
	for(var i=0;i<s;i++)
	{
		if(cls[i]==undefined || cls[i]==null || cls[i]=="")
		{
			html+="<td>";
		}else{
			html+="<td class='"+cls[i]+"'>";
		}
		html+=data[i];
		html+="</td>";
	}
	html+="</tr>";
	$("#"+tBody).html($("#"+tBody).html()+html);
}

function initSelectOptions()
{
	$("select").each(function()
	{
		var isWithBlank=false;
		var reqUrl=null,select=null,opt=null;
		if($(this).attr("isWithBlank"))
		{
			isWithBlank=$(this).attr("isWithBlank").toLowerCase()=="true"?true:false;
		}

		if($(this).attr("data") && this.options.length==0)
		{
			select=this;
			
			reqUrl=$(this).attr("data");
			if(reqUrl.indexOf(getContextPath()+"/")==-1)
			{
				reqUrl=getContextPath()+reqUrl;
			}
			var s= reqUrl.indexOf("?")==-1 ? "?_=" : "&_=";
			
			reqUrl=reqUrl+s+new Date().getTime();
			
			blankLabel=$(this).attr("blankLabel");
			if(blankLabel==undefined)
			{
				blankLabel="";
			}
			topLoading();
			$.ajax({type:"GET",dataType:"json",url:reqUrl,async:false,success:function(resultBean, status, xhRequest)
			{
				topLoaded();
				if(!handleAjaxRequest(resultBean, status,xhRequest)){return;}
				
				for(var i=select.options.length;i>=0;i--) select.options.remove(i);	
				
				if(isWithBlank)
				{
					opt=document.createElement('option');
					select.options.add(opt);
					opt.value="";
					opt.innerHTML=blankLabel; 
				}
				var dataMap=resultBean.data;
				for(var key in dataMap)
				{
					opt=document.createElement('option');
					select.options.add(opt);
					opt.value=key;
					opt.innerHTML=dataMap[key]; 	
				}
				
			}});	
		}
	});
}

function selectOption(id,value)
{
  var obj=document.getElementById(id).options;
  for(var i=0;i<obj.length;i++)
  {
    if(obj[i].value==value)
    {
      obj[i].selected=true;
      break;
    }
  }
}

function getLength(value)
{
	var len=0;
	if(value==undefined || value=="")
	{
		return len;
	}
	for(var k=0;k<value.length;k++)
	{
		v=value.charCodeAt(k);
	    if(v<0||v>255){len+=2;}else{len++;}
	}
	return len;
}

/**地区 下拉数据的 动态添加
 * @author tanjin
 * @param optType
 * @param selectClazz
 * @param url
 * @param needFirst 是否需要“请选择”
 * @param defaultValue 默认值
 * 
 */
function initRegionSelectOptions(selectClazz,url,needFirst,defaultValue)
{
	//api/sys/getRegions
	//api/sys/getRegions?parentId=?	
	var select=null;//,opt=null;
	select = $("#"+selectClazz);	
	$.getJSON(url,{},function(resultBean, status, xhRequest)
			{
				//if(!handleAjaxRequest(resultBean, status,xhRequest)){return;}
				var dataMap=resultBean.data;				
				//$("#"+selectClazz).empty();
				$(select).html("");
				if(needFirst==false){
					
				}else{
					$("<option selected='true' value=''>请选择</option>").appendTo(select);
				}
				$.each(dataMap, function (i, n) {					
					if(defaultValue&&defaultValue!=null&&defaultValue==i){
						$("<option value='" + i + "' selected='selected' >" + n + "</option>").appendTo(select);
					}else{
						$("<option value='" + i + "'>" + n + "</option>").appendTo(select);
					}
		          /*  if (eval("n." + options.valuefiled) == options.selectedindex) {
		                $("<option selected='true' value='" + eval("n." + options.valuefiled) + "'>" + eval("n." + options.textfield) + "</option>").appendTo(select);
		            } else {
		                $("<option value='" + eval("n." + options.valuefiled) + "'>" + eval("n." + options.textfield) + "</option>").appendTo(select);
		            }*/
					//console.log(n+"===="+i);
		        });	
				
			});	
}

//根据用户权限控制页面显示内容
function checkPermission()
{
	var loginUser=window.top.getLoginUser();
	if(loginUser!=undefined)
	{
		  var permissionIdSet=loginUser.user.permissionIdSet;
		  var permissionId;
		  $("a,input[type='button'],button").each(function()
		  {
			  permissionId=$(this).attr("permissionId");
			  if(permissionId!=undefined && !permissionIdSet.contains(permissionId))
			  {
				  $(this).css("display","none");
			  }
		  });	
	 }
}

function initXytItem()
{
	initSelectOptions();
	checkPermission();
}

/**
 * 获得当前用户的类型。服务端根据CommonConstants.java中的定义返回用户类型（运营商：OPT；政府部门：ORG；餐厅：RST；学校：SCH；企业：ENT；公众会员：MBR）。
 * 
 * @Author: 许继俊
 *  
 * @returns 用户类型，或null
 */
function getUserType(){
	var loginUser=window.top.getLoginUser();
	if(loginUser!=undefined){
		return loginUser.user.userType;
	}
	return null;
}

/**
 * 判断元素element中的_userType属性是否和登录用户的userType相匹配
 * 
 * @Author: 许继俊
 *  
 * @param element：元素ID
 * @returns {Boolean}：如果_userType属性没有被定义，或该属性包含了当前用户的userType，则返回true
 */
function userTypeMatched(element){
	var userTypeSet=$(element).attr("_userType");
	if(userTypeSet==undefined || userTypeSet.split(" ").contains(getUserType())){
		return true;
	}else{
		return false;
	}
}

/**
 * 根据用户类型(userType)控制页面显示内容
 * 
 * 使用方法：例子：如果要限制某个内容只给学校和企业用户看到，则在元素中加入这个属性： _userType="SCH ENT"，然后在页面加载后运行此函数。
 * 
 * @Author: 许继俊
 */
function checkUserType(){
	if(getUserType()!=null){
		$("*").each(function(){
			if(!userTypeMatched(this)){
				$(this).hide();
			}
		});	
	}
}


/**
 * 获取URL中参数的值
 * 
 * 例子：http://abc.com?action=update&id=987654321789
 * var action = getUrlParam("action"); //返回action的值为"update"
 * 
 * @Author: 许继俊
 * 
 * @Param: name: 要获取的参数名字
 * @param: _location：可选参数，页面的URL，在弹出窗口中使用
 * @return：返回参数的值
 */
function getUrlParam(name, _location){
	//console.log(window.location);
	var _location_url =_location || window.location.search; //window.location.search：URL中问号及其后面的内容
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = _location_url.substr(1).match(reg); //匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; //返回参数值
}

function _Dialog(title, url,callback,width) {
	var _width = width || 530;
	 options = {
			 _type:'Dialog',
             _dialogW: _width,
             _url:url,
             _title:title,
             _callbackfn: callback,
             _jquery: $ // 测试Jq 的实例传出
         };
	 if(width)
		 options._dialogW = width;
    $(window.parent.showPrompt(options));
}

/**
 * 错误提示框，在iframe中使用
 * @param msg
 * @param callback
 * @param icontype: "success"/"error"
 */
function _message(msg, callback, icontype){	
	var _icontype=icontype|| "success";
	options = {
            _type: 'MsgDialog',
            _countdown:true,
            _msg: msg, 
            _msgicon: _icontype,
            _closeDialog:true,
            _callbackfn: callback
        };

	$(window.parent.showPrompt(options));
}

/**
 * 错误提示框，在弹出框中使用
 * @param msg
 * @param icontype: "success"/"error"
 * @parama times显示时间，默认3000毫秒
 */
function _showMessage(msg, icontype,times){
	var _icontype=icontype|| "success";
	var _times=times||3000;
	options = {
            _type: 'MsgDialog',
            _times:_times,
            _countdown:true,
            _msgicon: _icontype,
            _msg:msg,
            _closeDialog:true,
            _callbackfn: function () {
            	$("#iframeid").contents().find("#but-search").click();
            }
        };
        showPrompt(options);
}

/**
 * 错误提示框，在弹出框中使用
 * @param msg
 * @param icontype: "success"/"error"
 * @param closeDialog: true/false 是否关闭弹出框
 */
function _alert(msg, icontype, closeDialog){
	var _icontype=icontype|| "success";
	options = {
            _type: 'MsgDialog',
            _countdown:true,
            _msgicon: _icontype,
            _msg:msg,
            _closeDialog:closeDialog
        };
   showPrompt(options);
}

function _confirmDialog(msg,title,callback,okBtn,cancleBtn){
	options = {
            _type: 'ConfirmDialog',
            _dialogW:450,
            _title:title,
            _msg: msg,
            _closeDialog: true,
            _callbackfn:  callback,
            _okBtn: okBtn,
            _cancleBtn: cancleBtn
        };
	$(window.parent.showPrompt(options));
 
}

function getRealPath(){
	return "/fs";	
}
/**
 * 统计输入字符数
 */
function countWorld(){
	$.each($("textarea"),function(x,y){
		_showCount($(this));
		$(this).on("keyup",function(){
			_showCount($(this));
		});
		$(this).on("change",function(){
			_showCount($(this));
		});
	});
}

function change(){
	 $("input").css({"color":"black"});
}

function _showCount(element){
	var maxlength = element.attr("maxlength");
		
	var counter = element.val() ? element.val().length : 0;
	
	if (counter > maxlength)
		//如果元素区字符数大于最大字符数，按照最大字符数截断；
		element.val(element.val().substring(0, maxlength));
	else
		//在记数区文本框内显示剩余的字符数；
		element.next("label").html('(还可以输入<var style="color: #C00">'+(maxlength - counter)+'</var>个字符)');
}

/**
 * 检查上传图片的类型
 * @param fileName
 * @returns {Boolean}
 */
function CheckFile(fileName) {
    var array = new Array('gif', 'jpeg', 'png', 'jpg','jpe','jpg','bmp');  //可以上传的文件类型
    if (fileName == '') {
        _alert("让选择要上传的图片!");
        return false;
    }
    else {
        var fileContentType = fileName.match(/^(.*)(\.)(.{1,8})$/)[3]; //这个文件类型正则很有用：）
        var isExists = false;
        for (var i in array) {
            if (fileContentType.toLowerCase() == array[i].toLowerCase()) {
                isExists = true;
                return true;
            }
        }
        if (isExists == false) {
            _alert("上传图片类型不正确!");
            return false;
        }
        return false;
    }
}
/*
 * 判断是否图片路径
 */
function isImage (str) {
	return str.match(/(^data:image\/.*,)|(\.(jp(e|g|eg)|gif|png|bmp|webp|svg)((\?|#).*)?$)/i);
}

/**
 * 兼容IE9以下的placeholder属性显示
 * 注意在加载列表数据之前要先调$('[placeholder]').focusin() 清空placeholder的显示值,否 作参数提交
 * 数据加载完成之后,可调用$('[placeholder]').focusout() 重新显示placeholder;
 */
function placeholder(){
		if(!placeholderSupport()){   // 判断浏览器是否支持 placeholder
			 $('[placeholder]').focusin(function() {
			var input = $(this);
			if (input.val() == input.attr('placeholder')) {
			    input.val('');
			    input.removeClass('placeholder');
			}
		    }).focusout(function() {
			var input = $(this);
			if (input.val() == '' || input.val() == input.attr('placeholder')) {
			    input.addClass('placeholder');
			    input.val(input.attr('placeholder'));
			}
		    }).focusout();
		
		
		}; 
	   }

function placeholderSupport() {return 'placeholder' in document.createElement('input'); } 

/**isTest:是否本地测试环境  */
function getFsDomain(isTest)
{
	isTest=isTest==undefined?false:isTest;
	if(isTest)
	{
		return "http://l.x-eyess.com:8080/ocr";
	}else{
		return window.top.getLoginUser().fsDomain;
	}
}

/*
 * 日期格式化
 * */
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};
//未登录。直接给页面加个白色蒙板
checkLoginError_addbackdrop =function(){
	var Login_Backdrop = '<div class="login-backdrop"></div>';
	$(Login_Backdrop).appendTo('body');
}
//弹出框移动算法
dialoguiDraggable= function (_dlg) {
    var elem = _dlg.find(".modal-content");
    var startX, startY, x, y;
    x = 0;
    y = 0;
    startX = 0;
    startY = 0;

    var mousemove, mouseup;
    elem.css({
        position: "relative"
    });
    elem.find(".modal-header").css({
        cursor: "move"
    });
    elem.find(".modal-header").bind("mousedown", function (event) {
        startX = event.screenX - x;
        startY = event.screenY - y;
        $(document).bind("mousemove", mousemove);
        $(document).bind("mouseup", mouseup);
    });
    mousemove = function (event) {
        y = event.screenY - startY;
        x = event.screenX - startX;
        elem.css({
            top: y + "px",
            left: x + "px"
        });
    };
    mouseup = function () {
        $(document).unbind("mousemove", mousemove);
        $(document).unbind("mouseup", mouseup);
    };
}

function changesel_input_style(){
	//搜索框初始为灰
    $(".table-group select").css({"color":"gray"});
    $(".table-group input").css({"color":"gray"});
    //$("input").addClass("placeholder");
	//****下拉框 样式改变 设置****//
	$(".table-group select").each(function(){
		if(''==$(this).val() || "null"===$(this).val()){ $(this).removeClass("bl_class").addClass("gr_class"); }else{ $(this).removeClass("gr_class").addClass("bl_class"); }
	})
	$(".table-group select").focus(function(){
		
		$(this).removeClass("gr_class");
		//$(this).addClass("bl_class");
		$(this).css({"color":"black"});
	});
	$(".table-group select").blur(function(){
		 if(''==$(this).val()){ 
			 $(this).removeClass("bl_class");
			 $(this).css({"color":"gray"});
		 }else{ 
			 $(this).removeClass("gr_class");
			 $(this).css({"color":"black"});
		 }
	});
	$(".table-group select").change(function(){
		 $(this).removeClass("gr_class");
		 $(this).css({"color":"black"});
	});
	//**End**下拉框 样式改变 设置****//
	//****placeholder样式改变 设置****//
	$(".table-query input").addClass("gr_class");
	$(".table-query input").focus(function(){
		$(this).removeClass("gr_class");
		$(this).css({"color":"black"});
	});
	$(".table-query input").blur(function(){
		 if(''==$(this).val()||$(this).val()==$(this).attr('placeholder')){
			 $(this).removeClass("bl_class");
			 $(this).css({"color":"gray"});
		}else{ 
			$(this).removeClass("gr_class"); 
			$(this).css({"color":"black"});
		}
	});
	//****placeholder样式改变 设置****//
}
//时间戳转日期
function timestampToTime(timestamp,tename) {
	var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000 如timestamp * 1000
	Y = date.getFullYear() + '-';
	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	D = date.getDate() + ' ';
	h = date.getHours() + ':';
	m = date.getMinutes() + ':';
	s = date.getSeconds();
	
	Y1 = date.getFullYear();
	M1 = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1);
	D1 = date.getDate();
	h1 = date.getHours();
	m1 = date.getMinutes();
	if(tename=='Y'){
		return Y1;
	}
	else if (tename=='M'){
		return M1;
	}
	else if (tename=='D'){
		return D1;
	}
	else if (tename=='h'){
		return h1;
	}
	else if (tename=='mx'){
		return m1;
	}
	else if (tename=='s'){
		return s;
	}
	else{
		return Y+M+D+h+m+s;
	}
}
//公共修改窗
function modPwd(tletii,urlnngetgo){
	var temp_url = getContextPath() + urlnngetgo;
    _Dialog(tletii,temp_url);
    
}