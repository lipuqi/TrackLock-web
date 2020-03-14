package com.lettuce.air.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lettuce.air.common.exception.BasicException;
import com.lettuce.air.core.controller.GenericResponse;
import com.lettuce.air.core.controller.ResponseFormat;
import com.lettuce.air.pojo.entity.device.DeviceStatus;
import com.lettuce.air.pojo.entity.device.DeviceUnlockInfo;
import com.lettuce.air.pojo.entity.position.PositionInfo;
import com.lettuce.air.pojo.entity.task.DeviceTask;
import com.lettuce.air.service.device.DeviceStatusService;
import com.lettuce.air.service.device.DeviceUnlockInfoService;
import com.lettuce.air.service.position.PositionInfoService;
import com.lettuce.air.service.task.DeviceTaskService;

/**
 * 监控接口
 * @author Lando
 *
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {
	
	@Autowired
	private DeviceStatusService deviceStatusService;
	
	@Autowired
	private DeviceTaskService deviceTaskService;
	
	@Autowired
	private DeviceUnlockInfoService deviceUnlockInfoService;
	
	@Autowired
	private PositionInfoService positionInfoService;
	
	/**
	 * 获取设备列表
	 * @return
	 */
	@GetMapping(value = "/getDeviceList")
	public GenericResponse getDeviceList(){
		List<DeviceStatus> deviceList = null;
		try {
			deviceList = deviceStatusService.seleceDeviceList();
		} catch (Exception e) {
			throw new BasicException(1000, e);
		}
		return ResponseFormat.retParam(200, deviceList);
	}
	
	/**
	 * 根据设备获取任务列表
	 * @return
	 */
	@GetMapping(value = "/getTaskList/{imei}")
	public GenericResponse getTaskList(@PathVariable String imei){
		List<DeviceTask> taskList = null;
		try {
			taskList = deviceTaskService.seleceTaskList(imei);
		} catch (Exception e) {
			throw new BasicException(1000, e);
		}
		return ResponseFormat.retParam(200, taskList);
	}
	
	/**
	 * 根据设备获取开锁列表
	 * @return
	 */
	@GetMapping(value = "/getUnlockList/{imei}")
	public GenericResponse getUnlockList(@PathVariable String imei){
		List<DeviceUnlockInfo> unlockList = null;
		try {
			unlockList = deviceUnlockInfoService.seleceUnlockInfoList(imei);
		} catch (Exception e) {
			throw new BasicException(1000, e);
		}
		return ResponseFormat.retParam(200, unlockList);
	}
	
	/**
	 * 获取定位列表
	 * @return
	 */
	@GetMapping(value = "/getPositionList")
	public GenericResponse getPositionList(){

		return ResponseFormat.retParam(200, null);
	}
	
	/**
	 * 根据设备获取定位列表
	 * @return
	 */
	@GetMapping(value = "/getPositionListByImei/{imei}")
	public GenericResponse getPositionListByImei(@PathVariable String imei){
		List<PositionInfo> positionList = null;
		try {
			positionList = positionInfoService.selecePositionListByImei(imei);
		} catch (Exception e) {
			throw new BasicException(1000, e);
		}
		return ResponseFormat.retParam(200, positionList);
	}

}
