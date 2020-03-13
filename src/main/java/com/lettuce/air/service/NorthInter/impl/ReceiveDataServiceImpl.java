package com.lettuce.air.service.NorthInter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lettuce.air.common.constant.PushStatus;
import com.lettuce.air.common.constant.ServiceConstant;
import com.lettuce.air.pojo.req.DeviceDataReq;
import com.lettuce.air.service.NorthInter.ReceiveDataService;
import com.lettuce.air.service.device.DeviceStatusService;
import com.lettuce.air.service.position.PositionInfoService;

@Service
public class ReceiveDataServiceImpl implements ReceiveDataService {
	
	@Autowired
	private DeviceStatusService deviceStatusService;
	
	@Autowired
	private PositionInfoService positionInfoService;
	
	@Override
	public void getDeviceData(DeviceDataReq deviceDataReq) throws Exception {
		
		//判断通知类型是否为上报数据类型
		if (PushStatus.DEVICE_DATA_CHANGED.equals(deviceDataReq.getNotifyType())) {
			
			switch (deviceDataReq.getService().getServiceId()) {
			case ServiceConstant.Positioning:
				positionInfoService.addPositionInfo(deviceDataReq);
				break;
			case ServiceConstant.Unlock:
				deviceStatusService.saveLockStatus(deviceDataReq);
				break;
			case ServiceConstant.GlobalState:
				deviceStatusService.saveDeviceStatus(deviceDataReq);
				break;
			}
			
		}
	}

	
}
