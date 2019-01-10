package com.msy.controller.api.out;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msy.constant.Constants;
import com.msy.model.EntityRoom;
import com.msy.model.Result;
import com.msy.service.EntityRoomService;

@RestController
@RequestMapping(value = "/api/out" )
public class EntityRoomApiOutController {
	@Autowired
	private EntityRoomService entityRoomService;
	/**
	 * 获取实体房间信息
	 * @param entityid
	 * @return
	 */
	@RequestMapping(value = "/entity/getRoomList")
    public Result sendData(@RequestParam(value="sendData") String sendData ){
		Result result = new Result();
		try {
			List<EntityRoom> p = this.entityRoomService.getEntityRoomListById(sendData);
			result.setResultData(p);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(Constants.RESULT_FAIL);
			result.setResultMsg("获取房间信息异常");
			return result;
		}
    }
}
