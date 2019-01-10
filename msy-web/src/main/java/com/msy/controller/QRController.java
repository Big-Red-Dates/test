package com.msy.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msy.config.WXConfig;
import com.msy.constant.Constants;
import com.msy.model.Operator;
import com.msy.model.Result;
import com.msy.utils.QrCodeCreateUtil;

@Controller
@RequestMapping(value = "/qr/" )
public class QRController extends BaseController{

	private Log log = LogFactory.getLog(this.getClass().getName());
	
	@Autowired
	WXConfig wxConfig;

	/**
	 * 
	 * @param request
	 * @param response
	 * @param type 上传类型
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/createImg", "" })
	public Result createImg(HttpServletRequest request, HttpServletResponse response,String type,String roomId,String entityId)
	{
		log.debug("type ==>"+type +" roomId==>"+roomId);
		
		Operator operator = super.getLoginUser(request);
		Result rb = new Result();
		
		if(null == operator){
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("用户未登录或会话超时");
			return rb;
		}
		
		// /fs/wx/entityId/roomId/roomId.jpg
		
		String fileName = roomId+".jpg";
		String path = request.getRealPath(File.separator);
		String realPath = File.separator+"fs"+File.separator+type+File.separator+entityId+File.separator+roomId+File.separator;
		path +=  realPath;
		System.out.println("==>]]][][]["+path);
		boolean flag = false;
		OutputStream outputStream = null;
		try{
			
			File file = new File(path+fileName);
		    /**
		     * 文件已生成过
		     */
		    if(file.exists())    
		    { 
		    	Map map = new HashMap();
		    	map.put("url", realPath + fileName);
		    	rb.setResultData(map);
		    	return rb;
		    }
		    
			File fileDir = new File(path);
		    if (!fileDir.exists() && !fileDir.isDirectory()) {// 判断/download目录是否存在
		      log.debug("创建目录【+"+fileDir+"】标记==>"+fileDir.mkdirs());// 创建目录
		    }
		    
		    
		    
		    StringBuffer imgUrl = new StringBuffer();
		    imgUrl.append(this.wxConfig.getCodeUrl());
		    imgUrl.append("?");
		    imgUrl.append("&roomId="+roomId);
		    imgUrl.append("&entityId="+entityId);
		    
		    log.debug("realPath==>"+path + fileName);
		    
		    String content = imgUrl.toString();
		    log.debug("content:"+content);
		    outputStream = new FileOutputStream(new File(path + fileName));
		    
			flag = QrCodeCreateUtil.createQrCode(outputStream, content,900,"JPEG");
		    if(flag){
		    	Map map = new HashMap();
		    	map.put("url", realPath + fileName);
		    	rb.setResultData(map);
		    }
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}finally{
			if(null != outputStream)
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		rb.setResultCode(true == flag ? Constants.RESULT_SUCCESS : Constants.RESULT_FAIL);
		return rb;
	}

}
