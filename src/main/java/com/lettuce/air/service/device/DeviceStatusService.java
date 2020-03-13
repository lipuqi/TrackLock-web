package com.lettuce.air.service.device;

import java.text.ParseException;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lettuce.air.pojo.enmus.StateTypeEnum;
import com.lettuce.air.pojo.entity.device.DeviceStatus;
import com.lettuce.air.pojo.req.DeviceDataReq;

public interface DeviceStatusService extends IService<DeviceStatus> {
	
	/**
	 * 获取设备信息
	 * @param deviceId
	 * @return
	 */
	DeviceStatus getDeviceStatusByDeviceId(String deviceId);
	
	/**
	 * 获取设备信息
	 * @param deviceId
	 * @return
	 */
	DeviceStatus getDeviceStatusByImei(String imei);

	/**
	 * 添加设备状态
	 */
	void saveDeviceStatus(DeviceDataReq deviceDataReq) throws ParseException ;
	
	/**
	 * 添加锁状态
	 */
	void saveLockStatus(DeviceDataReq deviceDataReq) throws ParseException ;
	
	/**
	 * 开锁指令
	 */
	void unLockCommand(String imei) throws Exception;
	
	/**
	 * 获取设备状态
	 */
	void getDeviceStateCommand(String imei) throws Exception;
	
	/**
	 * 设置设备配置
	 */
	void setStateIntervalCommand(String imei, StateTypeEnum stateType, Integer interval) throws Exception;
	
}
