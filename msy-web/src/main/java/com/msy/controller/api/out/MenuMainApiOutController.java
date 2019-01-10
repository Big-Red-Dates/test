package com.msy.controller.api.out;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cuiyongzhi.wechat.menu.ClickButton;
import com.cuiyongzhi.wechat.menu.ViewButton;
import com.cuiyongzhi.wechat.util.HttpUtils;
import com.msy.config.WXConfig;
import com.msy.utils.wxpublic.TokenUtil;

import net.sf.json.JSONArray;

@RestController
@RequestMapping(value = "/api/out" )
public class MenuMainApiOutController {
	@Autowired
	WXConfig wxConfig;
@ResponseBody
@RequestMapping(value = "/wxclick")
public void wxclick(HttpServletRequest request, HttpServletResponse response){
  
 ClickButton cbt=new ClickButton();
 cbt.setKey("image");
 cbt.setName("码上影规划");
 cbt.setType("click");
 
 ClickButton cbt2=new ClickButton();
 cbt2.setKey("image");
 cbt2.setName("产品介绍");
 cbt2.setType("click");
 
 ClickButton cbt3=new ClickButton();
 cbt3.setKey("image");
 cbt3.setName("公司简介");
 cbt3.setType("click");
 
 ClickButton cbt4=new ClickButton();
 cbt4.setKey("image");
 cbt4.setName("公司文化");
 cbt4.setType("click");
 
 JSONArray sub_button1=new JSONArray();
 sub_button1.add(cbt);
 sub_button1.add(cbt2);
 sub_button1.add(cbt3);
 sub_button1.add(cbt4);
 
 JSONObject buttonOne1=new JSONObject();
 buttonOne1.put("name", "关于码上影");
 buttonOne1.put("sub_button", sub_button1);
 
 ClickButton cbt5=new ClickButton();
 cbt5.setKey("image");
 cbt5.setName("加入码上影");
 cbt5.setType("click");
   
// ViewButton vbt=new ViewButton();
// vbt.setUrl("http://www.baidu.com");
// vbt.setName("博客");
// vbt.setType("view");
 
 ClickButton cbt6=new ClickButton();
 cbt6.setKey("image");
 cbt6.setName("公司新闻");
 cbt6.setType("click");
 
 ClickButton cbt7=new ClickButton();
 cbt7.setKey("image");
 cbt7.setName("最新活动");
 cbt7.setType("click");
 
 ClickButton cbt8=new ClickButton();
 cbt8.setKey("image");
 cbt8.setName("码上影官网");
 cbt8.setType("click");
 
 ClickButton cbt9=new ClickButton();
 cbt9.setKey("image");
 cbt9.setName("投资建议");
 cbt9.setType("click");
 
 ClickButton cbt10=new ClickButton();
 cbt10.setKey("image");
 cbt10.setName("联系我们");
 cbt10.setType("click");
 
 JSONArray sub_button=new JSONArray();
 sub_button.add(cbt6);
 sub_button.add(cbt7);
 sub_button.add(cbt8);
 sub_button.add(cbt9);
 sub_button.add(cbt10);
   
   
 JSONObject buttonOne=new JSONObject();
 buttonOne.put("name", "关注码上影");
 buttonOne.put("sub_button", sub_button);
   
 JSONArray button=new JSONArray();
 button.add(buttonOne1);
 button.add(cbt5);
 button.add(buttonOne);
   
 JSONObject menujson=new JSONObject();
 menujson.put("button", button);
 System.out.println(menujson);
 
 String accessToken = TokenUtil.getToken(this.wxConfig.getFollowappId(),this.wxConfig.getFollowappSecret()).getAccessToken();
 //这里为请求接口的url +号后面的是token，这里就不做过多对token获取的方法解释
 String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken;
   
 try{
  String rs=HttpUtils.sendPostBuffer(url, menujson.toJSONString());
  System.out.println(rs);
  System.out.println("成功");
 }catch(Exception e){
  System.out.println("请求错误！");
 }
  
 }
  
}