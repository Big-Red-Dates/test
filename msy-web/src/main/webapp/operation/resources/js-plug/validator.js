//计算GBK字符串的长度
function GBKLength(str){
	var realLength = 0;
	var len = str.length;
	var charCode = -1;
	for(var i = 0; i < len; i++){
		charCode = str.charCodeAt(i);
		if (charCode >= 0 && charCode <= 128) { 
			realLength += 1;
		}else{ 
			// 如果是中文则长度加2
			realLength += 2;
		}
	} 
	return realLength;
}
//判断字符串str的GBK长度是否超过maxLength
function BGKLengthOverLimit(str, maxLength){
	
	return GBKLength(str)>maxLength ? true : false;
}


function fixedCharCodeAt(str, idx) {
    idx = idx || 0;
    var code = str.charCodeAt(idx);
    var hi, low;
    if (0xD800 <= code && code <= 0xDBFF) { // High surrogate (could change last hex to 0xDB7F to treat high private surrogates as single characters)
        hi = code;
        low = str.charCodeAt(idx + 1);
        if (isNaN(low)) {
            throw 'Error!';
        }
        return ((hi - 0xD800) * 0x400) + (low - 0xDC00) + 0x10000;
    }
    if (0xDC00 <= code && code <= 0xDFFF) { // Low surrogate
        // We return false to allow loops to skip this iteration since should have already handled high surrogate above in the previous iteration
        return false;
    }
    return code;
}

//计算UTF-8字符串的长度，包括汉字
function Utf8Length(str) {
    var result = 0;
    for (var n = 0; n < str.length; n++) {
        var charCode = fixedCharCodeAt(str, n);
        if (typeof charCode === "number") {
            if (charCode < 128) {
                result = result + 1;
            } else if (charCode < 2048) {
                result = result + 2;
            } else if (charCode < 65536) {
                result = result + 3;
            } else if (charCode < 2097152) {
                result = result + 4;
            } else if (charCode < 67108864) {
                result = result + 5;
            } else {
                result = result + 6;
            }
        }
    }
    return result;
}
var trueOrfalsevar=true;
function trueOrfalse(trueOrfalsevar){
	if(!trueOrfalsevar){
		var obj ={
				re:function(){
					return;
				}
		};
		return obj;
	}
}
/**
 * 输入长度校验(对象，长度)
 * @return obj{true/false,提示}
 */
function lengthCheck(obj,lengthVar){
	var pvar="";
	var objvar={};
	if(GBKLength(obj.value)>lengthVar){
		pvar="输入长度过长！（不能超过"+lengthVar+"个字符，2个字符为一个汉字）";
		objvar={trueOrfalse:false,showvar:pvar};
	}else{
		objvar={trueOrfalse:true,showvar:pvar};
	}
	return objvar;
}
/**
 * 字符长度校验。（字符串，长度）
 * return false/true,提示
 */
function strLengthCheck(str,lengthVar){
	var pvar="";
	var objvar={};
	if(GBKLength(str)>lengthVar){
		pvar="输入长度过长！（不能超过"+lengthVar+"个字符，2个字符为一个汉字）";
		objvar={trueOrfalse:false,showvar:pvar};
	}else{
		objvar={trueOrfalse:true,showvar:pvar};
	}
	return objvar;
}
/**
 * 校验非空（文本框输入）
 * @param str 输入的字符串
 * return false/true,提示
 */
function strNotNull(str){
	var pvar="";
	var objvar={};
	if(str==null || str==""){
		pvar="文本框不允许为空！";
		 objvar={trueOrfalse:false,showvar:pvar};
	}else{
		 objvar={trueOrfalse:true,showvar:pvar};
	}
	return objvar;
}

/**
 * 删除图片
 * @param path 图片路径
 */
function deleteImg(path){
	var pathVar="path="+path;
	$.post(getContextPath() + "/pc/sys/photoupload/delimg", pathVar, function(resultBean, status, xhRequest) {
    }, 'json');
}
/**
 * 判断是否删除图片
 * 修改图片的时候处理，判断修改前的图片是否是原图，是：不删除，不是：删除修改前的图片。
 * 提交事件的时候处理，判断修改后的图片是否是原图，是：不删除，不是：删除原图。
 * @param inputImg 原图的路径
 * @param imgUrl1  修改前图片路径/修改后的图片路径
 * @param path 	   修改前的路径/原图的路径
 */
function imgDelet(inputImg,imgUrl1,path){
	if(inputImg!=imgUrl1){
		var pathVar="path="+path;
		$.post(getContextPath() + "/pc/sys/photoupload/delimg", pathVar, function(resultBean, status, xhRequest) {
	    }, 'json');
	}
}
$(function(){
	/**
	 * 校验
	 * 必备：在文本框的class属性添加相对应的要校验的调用命。并且在文本框的后面添加一个<p></p>标签,<p>是用来提示。
	 * 例子：
	 * <input class="numberClass" maxlength="10" type="number"  id="iKey" name="iKey" /><p></p>
	 * 这样既可调用了下面的校验方法
	 * $(".numberClass").blur(function(){}
	 * 
	 * 可以根据需求进行编写校验，只需修改：
	 * $(".numberClass").blur(function(){}当中的".numberClass"是前端输入校验控件的class类调用命名，请确保该命名唯一。
	 * if(!/^-?\d+$/.test(this.value))当中的“^-?\d+$”正则表达式既可修改为你所需的校验。
	 * pvar.innerHTML是校验不成功的提示。
	 * pvar.setAttribute是校验的提示文字样式
	 * 
	 * 正则表达式校验：
	 * 验证数字的正则表达式集 
	 * 验证数字：^[0-9]*$ 
	 * 验证n位的数字：^\d{n}$ 
	 * 验证至少n位数字：^\d{n,}$
	 * 验证m-n位的数字：^\d{m,n}$
	 * 验证零和非零开头的数字：^(0|[1-9][0-9]*)$
	 * 验证有两位小数的正实数：^[0-9]+(.[0-9]{2})?$
	 * 验证有1-3位小数的正实数：^[0-9]+(.[0-9]{1,3})?$
	 * 验证非零的正整数：^\+?[1-9][0-9]*$
	 * 验证非零的负整数：^\-[1-9][0-9]*$
	 * 验证非负整数（正整数 + 0） ^\d+$
	 * 验证非正整数（负整数 + 0） ^((-\d+)|(0+))$
	 * 验证长度为3的字符：^.{3}$
	 * 验证由26个英文字母组成的字符串：^[A-Za-z]+$
	 * 验证由26个大写英文字母组成的字符串：^[A-Z]+$
	 * 验证由26个小写英文字母组成的字符串：^[a-z]+$
	 * 验证由数字和26个英文字母组成的字符串：^[A-Za-z0-9]+$
	 * 验证由数字、26个英文字母或者下划线组成的字符串：^\w+$
	 * 验证用户密码:^[a-zA-Z]\w{5,17}$ 正确格式为：以字母开头，长度在6-18之间，只能包含字符、数字和下划线。
	 * 验证是否含有 ^%&',;=?$\" 等字符：[^%&',;=?$\x22]+
	 * 验证汉字：^[\u4e00-\u9fa5],{0,}$
	 * 验证Email地址：^\w+[-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$
	 * 验证InternetURL：^http://([\w-]+\.)+[\w-]+(/[\w-./?%&=]*)?$ ；^[a-zA-z]+://(w+(-w+)*)(.(w+(-w+)*))*(?S*)?$
	 * 验证电话号码：^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$：--正确格式为：XXXX-XXXXXXX，XXXX-XXXXXXXX，XXX-XXXXXXX，XXX-XXXXXXXX，XXXXXXX，XXXXXXXX。
	 * 验证身份证号（15位或18位数字）：^\d{15}|\d{}18$
	 * 验证一年的12个月：^(0?[1-9]|1[0-2])$ 正确格式为：“01”-“09”和“1”“12”
	 * 验证一个月的31天：^((0?[1-9])|((1|2)[0-9])|30|31)$ 正确格式为：01、09和1、31。
	 * 整数：^-?\d+$
	 * 非负浮点数（正浮点数 + 0）：^\d+(\.\d+)?$
	 * 正浮点数 ^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$
	 * 非正浮点数（负浮点数 + 0） ^((-\d+(\.\d+)?)|(0+(\.0+)?))$
	 * 负浮点数 ^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$
	 * 浮点数 ^(-?\d+)(\.\d+)?$
	 */

	//非空校验
	$(".notNullClass").blur(function(){
		if(!trueOrfalsevar){
			trueOrfalsevar=true;
			return;
		}
		var pvar=this.nextSibling;
		if(this.value==null || this.value==""){
			pvar.innerHTML="不允许为空，请输入！";
			pvar.setAttribute("style","color: red");
			trueOrfalsevar=false;
		}else{
			pvar.innerHTML="";
			trueOrfalsevar=true;
		}
	});
	/**
	 * 长度校验(离开文本框校验)，通过获得设置的最长输入，来判断允许输入的长度。
	 */
	$(".lengthClass").blur(function(){
		if(!trueOrfalsevar){
			trueOrfalsevar=true;
			return;
		}
		var pvar=this.nextSibling;
		var lengthVar=this.maxLength;
		if(GBKLength(this.value)>lengthVar){
			pvar.innerHTML="输入长度过长！（不能超过"+lengthVar+"个字符，2个字符为一个汉字）";
			pvar.setAttribute("style","color: red");
			trueOrfalsevar=false;
		}else{
			pvar.innerHTML="";
			trueOrfalsevar=true;
		}
	});
	/**
	 * 长度校验(键盘输入校验)，通过获得设置的最长输入，来判断允许输入的长度。
	 */
	$(".lengthClass").keyup(function(){
		if(!trueOrfalsevar){
			trueOrfalsevar=true;
			return;
		}
		var pvar=this.nextSibling;
		var lengthVar=this.maxLength;
		if(GBKLength(this.value)>lengthVar){
			pvar.innerHTML="输入长度过长！（不能超过"+lengthVar+"个字符，2个字符为一个汉字）";
			pvar.setAttribute("style","color: red");
			trueOrfalsevar=false;
		}else{
			pvar.innerHTML="";
			trueOrfalsevar=true;
		}
	});
	//整数校验
	$(".numberClass").blur(function(){
		var _num =$(this).val();
		if(_num ==="")
			return;
		if(!trueOrfalsevar){
			trueOrfalsevar=true;
			return;
		}
		var pvar=this.nextSibling;
		if(!/^-?\d+$/.test(this.value)){
			pvar.innerHTML="请输入整数！";
			pvar.setAttribute("style","color: red");
			trueOrfalsevar=false;
	    }else{
	    	pvar.innerHTML="";
	    	trueOrfalsevar=true;
	    }
    });

	//浮点数校验
	$(".floatClass").blur(function(){
		if(!trueOrfalsevar){
			trueOrfalsevar=true;
			return;
		}
		var pvar=this.nextSibling;
		if(!/^(-?\d+)(\.\d+)?$/.test(this.value)){
			pvar.innerHTML="请输入浮点数！";
			pvar.setAttribute("style","color: red");
			trueOrfalsevar=false;
		}else{
			pvar.innerHTML="";
			trueOrfalsevar=true;
		}
	});
	
});
