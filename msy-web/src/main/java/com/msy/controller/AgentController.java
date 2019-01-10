package com.msy.controller;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.msy.constant.Constants;
import com.msy.model.AgentUserVo;
import com.msy.model.Operator;
import com.msy.model.OrderVo;
import com.msy.model.Result;
import com.msy.service.AgentUserService;
import com.msy.utils.DateTimeUtil;
import com.msy.utils.Pager;


@Controller
@RequestMapping(value = "/agent/" )
public class AgentController extends BaseController{
	private Log logger = LogFactory.getLog(this.getClass().getName());
	@Autowired
	private AgentUserService agentUserService;
	
	/**
	 * 查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/index", "" })
	public Result search(Model model,HttpServletRequest request, HttpServletResponse response){
		Result result = new Result();
		try {
			Operator operator = super.getLoginUser(request);
			Result rb = new Result();
			
			if(null == operator){
				rb.setResultCode(Constants.RESULT_FAIL);
				rb.setResultMsg("用户未登录或会话超时");
				return rb;
			}
			
			Map map = this.agentUserService.selectAllByAgentId(operator.getOperatorId());
			
			logger.info(JSON.toJSONString(map));
			result.setResultData(map);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(Constants.RESULT_FAIL);
			result.setResultMsg("查询酒店统计异常");
			return result;
		}
	
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(DateTimeUtil.parseDate("2018-04-28"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 查询分页
	 * 
	 * @param request
	 * @param response
	 * @param pager
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/search", "" })
	public Result search(HttpServletRequest request, HttpServletResponse response, Pager<OrderVo> pager, AgentUserVo agentUserVo)
	{
		Operator operator = super.getLoginUser(request);
		Result rb = new Result();

		if (null == operator) {
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("用户未登录或会话超时");
			return rb;
		}
		Result result = null;
		try {
			
			if(null != agentUserVo.getStartDtStr() && !"".equals(agentUserVo.getStartDtStr())){
				agentUserVo.setStartDt(DateTimeUtil.parseDate(agentUserVo.getStartDtStr()));
			}
			
			if(null != agentUserVo.getEndDtStr() && !"".equals(agentUserVo.getEndDtStr())){
				agentUserVo.setEndDt(DateTimeUtil.parseDate(agentUserVo.getEndDtStr()));
			}
			
			agentUserVo.setAgentId(operator.getOperatorId());
			result = agentUserService.search(pager, agentUserVo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询统计异常了", e);
		}
		return result;
	}
}
