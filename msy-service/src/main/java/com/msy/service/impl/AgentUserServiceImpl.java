package com.msy.service.impl;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msy.mapper.AgentMapper;
import com.msy.mapper.AgentUserMapper;
import com.msy.model.Agent;
import com.msy.model.AgentUser;
import com.msy.model.AgentUserVo;
import com.msy.model.Order;
import com.msy.model.OrderVo;
import com.msy.model.Result;
import com.msy.service.AgentUserService;
import com.msy.utils.DateTimeUtil;
import com.msy.utils.Pager;
import com.msy.utils.PagerEntity;

@Service
@Transactional
public class AgentUserServiceImpl implements AgentUserService {

	private Log logger = LogFactory.getLog(this.getClass().getName());
	
	private Calendar cal = Calendar.getInstance();
	DecimalFormat df = new DecimalFormat("#.00");
	@Autowired
	private AgentUserMapper agentUserMapper;
	
	@Autowired
	private AgentMapper agentMapper;
	
	@Override
	public int deleteByPrimaryKey(String agentId) {
		return this.agentUserMapper.deleteByPrimaryKey(agentId);
	}

	@Override
	public int insert(AgentUser record) {
		return agentUserMapper.insert(record);
	}

	@Override
	public int insertSelective(AgentUser record) {
		return agentUserMapper.insertSelective(record);
	}

	@Override
	public AgentUser selectByPrimaryKey(String agentId) {
		return agentUserMapper.selectByPrimaryKey(agentId);
	}

	@Override
	public int updateByPrimaryKeySelective(AgentUser record) {
		return agentUserMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AgentUser record) {
		return agentUserMapper.updateByPrimaryKey(record);
	}

	@Override
	public Map selectAllByAgentId(String agentId) {

		Map map = new HashMap();

		List<Order> orders = agentUserMapper.selectAllByAgentId(agentId);
		
		AgentUser agentUser = this.agentUserMapper.selectByPrimaryKey(agentId);
		
		Agent agent = this.agentMapper.selectByPrimaryKey(agentId);
		/**
		 * 未存在代理商信息，直接返回
		 */
		if(null == agentUser || null == agent || null == agent.getAgentRate()){
			return map;
		}
		
		double lastDay = 0;
		double lastMonth = 0;
		double currentMonth = 0;
		double current = 0;
		Date date = new Date();
		String lastDayDate = DateTimeUtil.dateToString(DateTimeUtil.getDateNextDayT(new Date(), -1), "yyyyMMdd");
		for (Order o : orders) {

			if (null == o.getAllPrice()) {
				continue;
			}

			if (null == o.getUpdatedTime()) {
				continue;
			}
			
			double price = o.getAllPrice().doubleValue();
			current += price;

			logger.info("date==>" + DateTimeUtil.dateToString(o.getUpdatedTime(), "yyyy-MM-dd"));

			cal.setTime(o.getUpdatedTime());
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);

			cal.setTime(date);
			int current_month = cal.get(Calendar.MONTH);
			int current_year = cal.get(Calendar.YEAR);

			if (current_year != year) {
				continue;
			}

			if (month == current_month) {
				currentMonth += price;
			}

			if (month == current_month - 1) {
				lastMonth += price;
			}

			String dateStr = DateTimeUtil.dateToString(DateTimeUtil.getDateNextDayT(o.getUpdatedTime(), 0), "yyyyMMdd");

			if (lastDayDate.equals(dateStr))
				lastDay += price;

		}

		double rate = agent.getAgentRate() * 0.01;
		logger.info("rate : " + rate +" by agentId:"+agentUser.getAgentId());
		
		current = current * rate ;
		currentMonth = currentMonth * rate;
		lastMonth = lastMonth * rate;
		lastDay = lastDay * rate;
		
		map.put("current", df.format(current));
		map.put("currentMonth", df.format(currentMonth));
		map.put("lastMonth", df.format(lastMonth));
		map.put("lastDay", df.format(lastDay));
		return map;
	}

	public static void main(String[] args) {
		
		Calendar cal = Calendar.getInstance();
		 cal.setTime(new Date());
		 int month = cal.get(Calendar.MONTH);
		 int year = cal.get(Calendar.YEAR);
		 int DAY = cal.get(Calendar.DAY_OF_MONTH);
		 
	}

	@Override
	public Result search(Pager<OrderVo> pager, AgentUserVo agentUserVo) {
		Result rb = new Result();
		
		PagerEntity pagerEntity = new PagerEntity(agentUserVo, pager.getOffset(), pager.getPageSize());
		pager.setDataSize(this.agentUserMapper.count(pagerEntity));
		if(pager.getDataSize() < pagerEntity.getOffset()){
			pagerEntity.setOffset(0);
		}
		pager.setBeanList(agentUserMapper.search(pagerEntity));
		rb.setResultData(pager);
		return rb;
	}
}
