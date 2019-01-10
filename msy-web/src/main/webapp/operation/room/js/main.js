$.ajaxSetup ({cache: false });

function getLoginUser()
{
	return loginUser;
}
var loginUser;
//初始化加载
//$(function() {
//
//	topLoading();

//	var userName = getParam("userName");
//	
//	$("#userNameLabel").html(userName);
//	
//	var menuList ='['
//		+ '{"id": "bus","name": "酒店管理","open": false,"orderNo": 0,"parent": true,"parentId": "","uri": ""}'
//		+ ',{"id": "bus-1","name": "酒店查询","open": false,"orderNo": 0,"parent": false,"parentId": "bus","uri": "/operation/entity/selhotal.html"}'
//		+ ',{"id": "roommgr","name": "房间管理","open": false,"orderNo": 0,"parent": true,"parentId": "","uri": ""}'
//		+ ',{"id": "roommgr-1","name": "房间查询","open": false,"orderNo": 0,"parent": false,"parentId": "roommgr","uri": "/operation/order/index.html"}'
//		+ ',{"id": "conroom","name": "绑定房间号","open": false,"orderNo": 0,"parent": true,"parentId": "","uri": ""}'
//		+ ',{"id": "conroom-1","name": "绑定房间号","open": false,"orderNo": 0,"parent": false,"parentId": "conroom","uri": "/operation/order/index.html"}'
//		+ ']';
//	
//
//
//	if(userName == 'admin'){
//		menuList = '['
//			+ '{"id": "zonghe","name": "用户管理","open": false,"orderNo": 0,"parent": true,"parentId": "","uri": ""}'
//			+ ',{"id": "zonghe-1","name": "用户管理","open": false,"orderNo": 0,"parent": false,"parentId": "zonghe","uri": "/operation/user/list.html"}'
//			+ ',{"id": "bus","name": "酒店管理","open": false,"orderNo": 0,"parent": true,"parentId": "","uri": ""}'
//			+ ',{"id": "bus-1","name": "酒店查询","open": false,"orderNo": 0,"parent": false,"parentId": "bus","uri": "/operation/entity/selhotal.html"}'
//			+ ',{"id": "roommgr","name": "房间管理","open": false,"orderNo": 0,"parent": true,"parentId": "","uri": ""}'
//			+ ',{"id": "roommgr-1","name": "房间查询","open": false,"orderNo": 0,"parent": false,"parentId": "roommgr","uri": "/operation/order/index.html"}'
//			+ ',{"id": "conroom","name": "绑定房间号","open": false,"orderNo": 0,"parent": true,"parentId": "","uri": ""}'
//			+ ',{"id": "conroom-1","name": "绑定房间号","open": false,"orderNo": 0,"parent": false,"parentId": "conroom","uri": "/operation/order/index.html"}'
//			+ ']';
//	}
//	
//	var obj = eval('(' + menuList + ')');
//
//	buildTreeMenu(obj);
//
//	topLoaded();
//});
//
//function getParam(paramName) { 
//    paramValue = "", isFound = !1; 
//    if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) { 
//        arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0; 
//        while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++ 
//    } 
//    return paramValue == "" && (paramValue = null), paramValue 
//} 
//
//function goLogout() {
//    topLoading();
//    	
//    	$.post(getContextPath()+"/api/out/logout",{},function(resultBean, status, xhRequest)
//    	   		{
//    	       		if(resultBean.resultCode == '0000'){
//    	       			topLoaded();
//    	       			top.document.location.href = getContextPath() + "/operation/sys/index.html";
//    				}else{
//    				    alert(resultBean.resultMsg);
//    				    topLoaded();
//    				}
//    	       		
//    	   		},'json');
//    	
//}

//构建菜单树
//function buildTreeMenu(menuList) {
//    var t = $("#nav-tree");
//    t = $.fn.zTree.init(t, setting, menuList);
//    demoIframe = $("#iframeid");
//}

//var zTree;
//var demoIframe;
//
//var setting = {
//    view: {
//        showLine: false,
//        showIcon: false,
//        dblClickExpand: false,
//        showLine: false,
//        selectedMulti: false
//    },
//    data: {
//        simpleData: {
//            enable: true,
//            idKey: "id",
//            pIdKey: "parentId",
//            rootPId: ""
//        }
//    },
//    callback: {
//        beforeClick: function (treeId, treeNode) {
//            zTree = $.fn.zTree.getZTreeObj(treeId);
//            if (treeNode.isParent) {
//                zTree.expandNode(treeNode);
//                return false;
//            } else {
//            	var randomNum = Math.floor(Math.random() * (182014 + 1));
//            	if(typeof(treeNode.uri) == 'undefined' || !treeNode.uri){
//            		demoIframe.attr("src", getContextPath() + "/operation/sys/home.html?vNum=" + randomNum);
//            		return;
//            	}
//            	if(treeNode.uri.lastIndexOf(".html")>0)
//            	{
//            		demoIframe.attr("src", getContextPath() + treeNode.uri+"?vNum=" + randomNum);
//            	}else
//        		{
//            		demoIframe.attr("src", getContextPath() + treeNode.uri + ".html?vNum=" + randomNum);
//        		}
//                return true;
//            }
//        }
//    }
//};
// 新增房间
function addRoom(){
	var temp_url = getContextPath() + "/operation/room/addroom.html";
    _Dialog('新增房间',temp_url); 
}
//修改酒店
function updatehotal(entityId){
	console.log(""+entityId);
	localStorage.setItem('entityId', entityId);
	var temp_url = getContextPath() + "/operation/room/updatehotal.html";
    _Dialog('修改酒店',temp_url); 
}