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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msy.constant.Constants;
import com.msy.model.Entity;
import com.msy.model.EntityRoom;
import com.msy.model.Operator;
import com.msy.model.Projector;
import com.msy.model.Result;
import com.msy.model.RoomProjector;
import com.msy.service.EntityRoomService;
import com.msy.service.ProjectorService;
import com.msy.service.RoomProjactorService;
import com.msy.utils.Pager;



@Controller
@RequestMapping(value = "/roomprojector/" )
public class RoomProjectorController extends BaseController{
	private Log log = LogFactory.getLog(this.getClass().getName());
	
	@Autowired
	private RoomProjactorService roomprojectservice;
	
	@Autowired
	private ProjectorService projectservice;
	
	@Autowired
	private EntityRoomService entityRoomservice;
	
	@ResponseBody
	@RequestMapping(value = { "/search", "" })
	public Result search(HttpServletRequest request, HttpServletResponse response,Pager<Projector> pager, Projector projector)
	{
		Operator operator = super.getLoginUser(request);
		Result rb = new Result();
		
		if(null == operator){
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("用户未登录或会话超时");
			return rb;
		}
		
		try {
			List<Projector> projectorlsit= this.projectservice.getAllProjector();
			rb.setResultData(projectorlsit);
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
	@RequestMapping(value = { "/searchroom", "" })
	public Result searchroom(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", required = true) String id)
	{
		
		Result rb = new Result();
		
		try {
			List<EntityRoom> entityRoom = this.entityRoomservice.getEntityRoomListById(id);
			rb.setResultData(entityRoom);
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
		RoomProjector roomprojector = new RoomProjector();
		Result rb = new Result();
		
		String projectId = request.getParameter("projectId");
		String roomno  = request.getParameter("roomno");
		
		roomprojector.setProjectorId(projectId);
		roomprojector.setRoomId(roomno);
		
		
		
		try {
			RoomProjector roomprojectorA = this.roomprojectservice.getRoomProjectorById(projectId);
			if(roomprojectorA!=null) {
				rb.setResultMsg("该设备已被绑定");
			}else {
			int a = this.roomprojectservice.add(roomprojector);
			if(a>0) {
				rb.setResultMsg("绑定成功");
			}}
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
