package com.msy.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msy.constant.Constants;
import com.msy.model.Entity;
import com.msy.model.EntityRoom;
import com.msy.model.Operator;
import com.msy.model.Result;
import com.msy.service.EntityRoomService;
import com.msy.service.EntityService;
import com.msy.utils.Pager;


@Controller
@RequestMapping(value = "/room/" )
public class RoomEntityController extends BaseController{
	private Log log = LogFactory.getLog(this.getClass().getName());
	
	@Autowired
	private EntityRoomService entityroomService;
	
	@Autowired
	private EntityService entityService;
	/**
	 * 查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/search", "" })
	public Result search(HttpServletRequest request, HttpServletResponse response,Pager<EntityRoom> pager, EntityRoom entityroom)
	{
		Operator operator = super.getLoginUser(request);
		Result rb = new Result();
		
		if(null == operator){
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("用户未登录或会话超时");
			return rb;
		}
		
		try {
			List<EntityRoom> entityrooms= this.entityroomService.getEntityRoomAll();
			rb.setResultData(entityrooms);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("业务处理异常",e);
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("业务处理异常");
			return rb;
		}
		return rb;
	}
	
	@ResponseBody
	@RequestMapping(value = { "/selentity", "" })
	public Result selentity(HttpServletRequest request, HttpServletResponse response)
	{
		
		Result rb = new Result();
						
		try {
			List<Entity> entitylist= this.entityService.getall();
			rb.setResultData(entitylist);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("业务处理异常",e);
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("业务处理异常");
			return rb;
		}
		return rb;
	}
	
	@ResponseBody
	@RequestMapping(value = { "/add", "" })
	public Result add(HttpServletRequest request, HttpServletResponse response)
	{
		EntityRoom entityroom = new EntityRoom();
		Result rb = new Result();
		
		String roomno = request.getParameter("roomno");
		String inentity = request.getParameter("inentity");
		String roomname  = request.getParameter("roomname");
		
		entityroom.setEntityId(inentity);
		entityroom.setRoomId(UUID.randomUUID().toString().replaceAll("-", ""));
		entityroom.setRoomName(roomname);
		entityroom.setRoomNo(roomno);
		try {
			int a = this.entityroomService.add(entityroom);
			if(a>0) {
				rb.setResultMsg("插入成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("业务处理异常",e);
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("业务处理异常");
			return rb;
		}
		return rb;
	}
}
