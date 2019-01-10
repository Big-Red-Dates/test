package com.msy.controller.api.out;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msy.model.ActiveTime;
import com.msy.model.Order;
import com.msy.model.PlayRecord;
import com.msy.model.Projector;
import com.msy.model.RoomProjector;
import com.msy.service.GetActiveTimeService;
import com.msy.service.OrderService;
import com.msy.service.PlayRecordService;
import com.msy.service.ProjectorService;

@RestController
@RequestMapping(value = "/api/out")
public class RoomGetActiveController {
	private static final Logger logger = Logger.getLogger(RoomGetActiveController.class);

	final Base64 base64 = new Base64();

	@Autowired
	private GetActiveTimeService GaService;

	@Autowired
	private PlayRecordService playrecordService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProjectorService projectorService;

	TimeZone timeZone = TimeZone.getTimeZone("GMT+8:00");
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 获取有效播放时间信息
	 * 
	 * @param projectorId
	 * @return
	 */
	@RequestMapping(value = "/room/getActiveTime")
	public ActiveTime sendData(@RequestParam(value = "projectorId") String projectorId) {
		ActiveTime activetimeClass = new ActiveTime();

		simpleDateFormat.setTimeZone(timeZone);
		String currTime = simpleDateFormat.format(new Date());
		System.out.println(currTime);
		try {
			Projector projector = this.projectorService.getProjectorById(projectorId);
			if (projector == null) {
				Projector projectorA = new Projector();
				projectorA.setProjectorId(projectorId);
				projectorA.setProjectorDeviceId(projectorId);
				
				this.projectorService.add(projectorA);
			} else {
				RoomProjector rp = this.GaService.getroomIdByProjectorId(projectorId);
				if (rp != null) {
					PlayRecord playrecord = playrecordService.getPlayRecordByIdAndcurrTime(rp.getRoomId(),
							simpleDateFormat.parse(currTime));
					if (playrecord != null) {
						Order order = orderService.getOrderByIdAndStatus(playrecord.getOrderId());
						if (order != null) {
							// 可以开通播放
							// TODO 这里还区分用户有没有关注过微信公众号的情况, 还未关注就是 1, 已经关注过那就是 2
							activetimeClass.setInitCode(1);
							activetimeClass.setMessage("可以播放了, 但是还没关注过公众号, 请先关注");
						} else {
							activetimeClass.setInitCode(-2);
							activetimeClass.setMessage("该订单出现未支付情况异常...");
						}
					} else {
						activetimeClass.setInitCode(-1);
						activetimeClass.setMessage("温馨提示: 投影仪还没开通播放, 请和酒店前台人员联系开通...");
					}
				} else {
					activetimeClass.setInitCode(0);
					activetimeClass.setMessage("还没有初始化, 请和系统管理员联系...");

				}
			}
			return activetimeClass;
		} catch (Exception e) {
			e.printStackTrace();
			return activetimeClass;
		}
	}
}
