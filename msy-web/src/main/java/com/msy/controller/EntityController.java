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
import com.msy.model.Operator;
import com.msy.model.Order;
import com.msy.model.Result;
import com.msy.service.EntityService;
import com.msy.utils.Pager;

@Controller
@RequestMapping(value = "/entity/" )
public class EntityController extends BaseController{
	private Log log = LogFactory.getLog(this.getClass().getName());
	
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
	public Result search(HttpServletRequest request, HttpServletResponse response,Pager<Entity> pager, Entity entity)
	{
		Operator operator = super.getLoginUser(request);
		Result rb = new Result();
		
		if(null == operator){
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("用户未登录或会话超时");
			return rb;
		}
		
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
		Entity entity = new Entity();
		Result rb = new Result();
		
		String hotalname = request.getParameter("hotalname");
		String hotaltype = request.getParameter("hotaltype");
		String cityname  = request.getParameter("cityname");
		String province  = request.getParameter("province");
	
		entity.setEntityName(hotalname);
		entity.setEntityId(UUID.randomUUID().toString().replaceAll("-", ""));
		entity.setEntityType(new Byte(hotaltype));
		entity.setProvince(province);
		entity.setProductId("2");
		entity.setCity(cityname);
		
		try {
			int i =this.entityService.add(entity) ;
			if(i>1){
				rb.setResultMsg("添加成功");
				return rb;
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
	
	@ResponseBody
	@RequestMapping(value = { "/searchbyid", "" })
	public Result searchById(HttpServletRequest request, HttpServletResponse response,
			        @RequestParam(value = "id", required = true) String id)
	{
		Result rb = new Result();
		
		try {
			Entity entity = this.entityService.selectEntityByid(id);
			rb.setResultData(entity);
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
	@RequestMapping(value = { "/updatehotal","" })
	public Result updatehotal(HttpServletRequest request, HttpServletResponse response)
	{
		Result rb = new Result();
		Entity entity = new Entity();
		
		String code = request.getParameter("code");
		String hotalname = request.getParameter("hotalname");
		String hotaltype = request.getParameter("hotaltype");
		String cityname  = request.getParameter("cityname");
		String province  = request.getParameter("province");
		
		entity.setEntityName(hotalname);
		entity.setEntityType(new Byte(hotaltype));
		entity.setProvince(province);
		entity.setCity(cityname);
		entity.setEntityId(code);
		
		try {
		int i= this.entityService.updateEntityById(entity);
		if(i>1){
			rb.setResultMsg("修改成功");
			return rb;
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
