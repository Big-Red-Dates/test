package com.msy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msy.constant.Constants;
import com.msy.model.EntityRoom;
import com.msy.model.EntityUser;
import com.msy.model.Operator;
import com.msy.model.Result;
import com.msy.service.EntityRoomService;
import com.msy.service.EntityUserService;

@Controller
@RequestMapping(value = "/room/" )
public class RoomController extends BaseController{

	private Log log = LogFactory.getLog(this.getClass().getName());
	
	@Autowired
	private EntityRoomService entityRoomService;
	
	@Autowired
	private EntityUserService entityUserService;
	
	/**
	 * 查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/getList", "" })
	public Result getList(HttpServletRequest request, HttpServletResponse response)
	{
		Operator operator = super.getLoginUser(request);
		Result rb = new Result();
		
		if(null == operator){
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("用户未登录或会话超时");
			return rb;
		}
		
		try {
			EntityUser entityUser = entityUserService.selectByPrimaryKey(operator.getOperatorId());
			List<EntityRoom> data = this.entityRoomService.getEntityRoomListById(entityUser.getEntityId());
			Map map = new HashMap();
			map.put("roomList", data);
			rb.setResultData(map);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询房间列表异常",e);
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("查询房间列表异常");
			return rb;
		}
		return rb;
	}
}
