package com.lettuce.air.service.NorthInter;

import com.lettuce.air.pojo.req.DeviceDataReq;

public interface ReceiveDataService {

	/**
	 * 获取设备数据变化
	 * @param result
	 */
	public void getDeviceData(DeviceDataReq deviceDataReq) throws Exception;
	
}
