package com.lettuce.air.service.position;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lettuce.air.pojo.entity.position.PositionInfo;
import com.lettuce.air.pojo.req.DeviceDataReq;

public interface PositionInfoService extends IService<PositionInfo> {
	
	
	/**
	 * 添加定位信息
	 */
	void addPositionInfo(DeviceDataReq deviceDataReq) throws Exception;
	
	/**
	 * 定位指令
	 */
	void getPositionCommand(String imei) throws Exception;
}
