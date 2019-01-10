package com.msy.controller.api.out;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.msy.config.WXConfig;
import com.msy.constant.Constants;
import com.msy.model.Result;
import com.msy.model.wxpublic.QRCodeMsg;
import com.msy.utils.wxpublic.RequestMethod;
import com.msy.utils.wxpublic.TokenUtil;


@RestController
@RequestMapping(value = "/api/out" )
public class WXPublicController {
	
	private static final Logger logger = Logger.getLogger(WXPublicController.class);
	@Autowired
	WXConfig wxConfig;
	
	@RequestMapping(value = "/weixin/getQRcode",produces="application/json;charset=UTF-8")
    public Result sendData(@RequestParam(value="projectorid") String projectorid ){
		Result result = new Result();
		
		String accessToken = TokenUtil.getToken(this.wxConfig.getFollowappId(),this.wxConfig.getFollowappSecret()).getAccessToken();
	     //获取数据的地址（微信提供）
	     String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken;

	     //发送给微信服务器的数据
	     String jsonStr = "{\"action_name\":\"QR_LIMIT_STR_SCENE\", \"action_info\":{\"scene\": {\"scene_str\":\""+projectorid+"\"}}}";

	     //post请求得到返回数据（这里是封装过的，就是普通的java post请求）
	     String response = RequestMethod.sendPost(jsonStr, url);
	     
//	     JsonParser jp = new JsonParser();
//       JsonObject jsonObj = (JsonObject)jp.parse(response.toString());
	    Gson gson = new Gson();
	    QRCodeMsg prcode =  gson.fromJson(response, QRCodeMsg.class);
		try {		
			result.setResultData(prcode);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(Constants.RESULT_FAIL);
			result.setResultMsg("获取二维码异常");
			return result;
		}
    }
}
