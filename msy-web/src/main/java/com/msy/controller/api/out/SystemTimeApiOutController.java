package com.msy.controller.api.out;



import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msy.constant.Constants;

import com.msy.model.Result;
import com.msy.model.SystemTime;
import com.msy.service.SystemTimeService;


@RestController
@RequestMapping(value = "/api/out/" )
public class SystemTimeApiOutController {
	private static final Logger logger = Logger.getLogger(SystemTimeApiOutController.class);
	
	final Base64 base64 = new Base64();
	
	@Autowired
	private SystemTimeService systemTimeService;
	/**
	 * 获取系统时间信息
	 * @param datetype
	 * @return
	 */
	@RequestMapping(value = "/system/getTime")
    public Result sendData(@RequestParam(value="dateType") String dateType ){
		Result result = new Result();
		try {
			SystemTime systemTime = systemTimeService.getTime();
			result.setResultData(systemTime);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(Constants.RESULT_FAIL);
			result.setResultMsg("获取时间异常");
			return result;
		}
    }
}
