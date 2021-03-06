<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="../common/taglib.jsp"%>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title></title>
	<link href="../../../front/resources/style/page.public.css" rel="stylesheet" />
	<link href="../../../front/resources/style/page.table.css" rel="stylesheet" />
	
	<script src="../../../front/resources/js-plug/jquery/jquery-1.9.1.js"></script>
	<link href="../../../front/resources/style/sys.css" rel="stylesheet" />
	
	<link href="../../../front/resources/xyt-pager/style.css" rel="stylesheet" />
	<script src="../../../front/resources/xyt-pager/pager.js"></script>
	<script src="../../../front/resources/js-plug/My97DatePicker/WdatePicker.js"></script>
	<script src="../../../front/resources/js-plug/sys.js"></script>
	<style type="text/css">
	 /*注意：案例中使用的图片在页面下方提供有下载*/
    /*在开发阶段，为div加入边框，便于调整和判断div的位置*/
    div{
        border: 1px solid #eee5de;
    }
    body{
        margin: 0px;
        padding: 0px;
    }
    /*主面板div样式*/
    #panel{   
        width: 980px;
		height: 400px;
        margin: 0px auto;/*div居中显示*/
    }
    /*顶部div样式*/
    #header{
        height: 132px;
    }
    /*导航主菜单DIV样式*/
    #menu{
        height: 32px;
    }
    /*无序列表与列表项消除间距*/
    ul, li{
        margin: 0px;
        padding: 0px;
    }
    /*列表项样式*/
    #menu li{
        float: left; /*所有列表项水平排列*/
        width: 84px;
        height: 32px;
        text-align: center;
        list-style:none; /*去掉列表项的样式(小圆点)*/
        line-height: 32px;/*行高*/
        font-weight: bold;/*字体加粗*/
        font-size: 13px;
    }
   /*超链接样式*/
    #menu a, #menu a:hover{
        color: #333333; /*深灰色*/
        text-decoration: none;/*去下划线*/
        height: 132px;
        width: 84px;
        /*
        将a标签设置为"块级呈现"，当鼠标移动到
        a标签上时便于呈现出全尺寸的高亮背景图片
        */
        display: block;
    }

	</style>
</head>
<body style="overflow-y:hidden;overflow-x:hidden">

	<!-- BEGIN 内容区-->
	<div class="main-big">
		<div class="main-b-box">
			<!-- BEGIN table组件-->
			<div class="main-b-contentbox">
				<div class="contentbox-title-nav">
					<div class="navigations">
						<ul>
							<li><a href="#">首页</a> <span>&gt;</span></li>
							<li><a href="#">房间管理</a> <span>&gt;</span></li>
							<li><a href="#">二维码生成</a></li>
						</ul>
					</div>
				</div>
				
<div id="panel">        
    <div id="header">  
        <div id="menu"> 
            <ul id="roomUl"> 
                <c:forEach var="room" items="${ roomList}" varStatus="status">
											<li><a href="#">${room.roomName }</a></li> 
										</c:forEach>
                
            </ul>
			<div style="height:30px">
			    <div id="imgDiv"><img id="imgUrl" src="./qrcode.jpg" style="height:150px;padding-top:150px;" /><span id="roomQRUrl" >xxx二维码</span><div>
				
			</div>
        </div> 
    </div>        
</div> 

			</div>
		</div>
	</div>

	<script type="text/javascript">
		/* $(document).ready(function() {
			//searchRoom();
			$("#imgDiv").hide();
		}); */
		
		/* function searchRoom() {
			var html = "";
			 $.post(getContextPath() + "/room/getList", {}, function(
					resultBean, status, request) {
				if (!handleAjaxRequest(resultBean, status, request)) return;
				var roomList = resultBean.resultData.roomList;
				var bean = "";
				for (var i = 0; i < roomList.length; i++) {
					bean = roomList[i];
					html += '<li id=roomid_'+bean.roomId+' title='+bean.roomName+'><a href="javascript:getQR('+bean.roomId+','+bean.entityId+');">'+bean.roomName+'</a></li>';
				}
				$("#roomUl").html(html);
			}, 'json');  
		} */
		
		function getQR(roomId,entityId){
			console.log(roomId+":"+entityId);
			//console.log(document.getElementById("roomid_"+roomId).value);
			console.log($("#roomid_"+roomId).attr("title"));
			$.post(getContextPath() + "/qr/createImg", {roomId:roomId,entityId:entityId,type:'wx'}, function(
					resultBean, status, request) {
				if (!handleAjaxRequest(resultBean, status, request)) return;
				
						if(resultBean.resultCode == '0000'){
							console.log('二维码生成成功'+resultBean.resultData.url);
							var url = resultBean.resultData.url;
							
							url=url.replace(/(\\|\|\/\/)/g,"/");
							console.log(url);
							$("#roomQRUrl").html("房间【"+$("#roomid_"+roomId).attr("title")+"】二维码");
							document.getElementById("imgUrl").src = '../../../'+url;
							$("#imgDiv").show();
						}else{
							console.log('二维码生成失败');
						}
						
			}, 'json'); 
		}
	</script>

</body>
</html>
