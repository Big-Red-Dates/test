package com.msy.controller.api.out;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.msy.constant.Constants;
import com.msy.controller.BaseController;
import com.msy.model.EntityRoom;
import com.msy.model.EntityUser;
import com.msy.model.Operator;
import com.msy.model.Result;
import com.msy.service.CacheSessionService;
import com.msy.service.EntityRoomService;
import com.msy.service.EntityUserService;
import com.msy.service.OrderService;


@Controller
@RequestMapping(value = "/api/out/room/" )
public class RoomApiOutController extends BaseController {

	private Log log = LogFactory.getLog(this.getClass().getName());
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CacheSessionService cacheSessionService;
	
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
	@RequestMapping(value = { "/getList", "" })
	public String getList(Model model,HttpServletRequest request, HttpServletResponse response)
	{
		Operator operator = super.getLoginUser(request);
		Result rb = new Result();
		
		if(null == operator){
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("用户未登录或会话超时");
		}
		
		try {
			EntityUser entityUser = entityUserService.selectByPrimaryKey(operator.getOperatorId());
			List<EntityRoom> data = this.entityRoomService.getEntityRoomListById(entityUser.getEntityId());
			log.info(JSON.toJSON(rb));
			model.addAttribute("roomList", data);
			return "room/list";
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询房间列表异常",e);
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("查询房间列表异常");
		}
		return "room/list";
	}
	
}
