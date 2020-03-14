package com.lettuce.air.service.device;

import java.text.ParseException;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lettuce.air.pojo.entity.device.DeviceUnlockInfo;
import com.lettuce.air.pojo.req.DeviceDataReq;

public interface DeviceUnlockInfoService extends IService<DeviceUnlockInfo> {
	
	/**
	 * 添加锁状态
	 */
	void saveLockStatus(DeviceDataReq deviceDataReq) throws ParseException ;
	
	/**
	 * 开锁指令
	 */
	void unLockCommand(String imei) throws Exception;
	
	/**
	 * 根据设备获取开锁列表
	 * @return
	 */
	List<DeviceUnlockInfo> seleceUnlockInfoList(String imei);
	
}
