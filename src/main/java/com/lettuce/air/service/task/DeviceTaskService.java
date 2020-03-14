package com.lettuce.air.service.task;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lettuce.air.pojo.entity.task.DeviceTask;

public interface DeviceTaskService extends IService<DeviceTask> {
	
	/**
	 * 获取任务信息
	 * @param deviceId
	 * @return
	 */
	DeviceTask getDeviceTask(String commandId);
	
	/**
	 * 添加任务
	 * @param deviceTask
	 */
	void addDeviceTask(DeviceTask deviceTask);
	
	/**
	 * 修改任务
	 * @param deviceTask
	 */
	void updateDeviceTask(DeviceTask deviceTask);
	
	/**
	 * 根据设备获取任务列表
	 * @return
	 */
	List<DeviceTask> seleceTaskList(String imei);
	
}
