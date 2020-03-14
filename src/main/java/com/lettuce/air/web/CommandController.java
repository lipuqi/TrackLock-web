package com.lettuce.air.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lettuce.air.common.exception.BasicException;
import com.lettuce.air.core.controller.GenericResponse;
import com.lettuce.air.core.controller.ResponseFormat;
import com.lettuce.air.pojo.enmus.StateTypeEnum;
import com.lettuce.air.service.device.DeviceStatusService;
import com.lettuce.air.service.device.DeviceUnlockInfoService;
import com.lettuce.air.service.position.PositionInfoService;

/**
 * app端对外接口
 * @author Lando
 *
 */
@RestController
@RequestMapping("/command")
public class CommandController {
	
	@Autowired
	private DeviceUnlockInfoService deviceUnlockInfoService;
	
	@Autowired
	private DeviceStatusService deviceStatusService;
	
	@Autowired
	private PositionInfoService positionInfoService;
	
	/**
	 * 开锁
	 * @return
	 */
	@GetMapping(value = "/unlock/{imei}")
	public GenericResponse unlock(@PathVariable String imei){
		try {
			deviceUnlockInfoService.unLockCommand(imei);
		} catch (Exception e) {
			throw new BasicException(1000, e);
		}
		return ResponseFormat.retParam(200, "开锁成功");
	}
	
	/**
	 * 获取设备状态
	 * @return
	 */
	@GetMapping(value = "/getDeviceState/{imei}")
	public GenericResponse getDeviceState(@PathVariable String imei){
		try {
			deviceStatusService.getDeviceStateCommand(imei);
		} catch (Exception e) {
			throw new BasicException(1000, e);
		}
		return ResponseFormat.retParam(200, "获取设备状态成功");
	}
	
	/**
	 * 设置设备配置
	 * @return
	 */
	@GetMapping(value = "/setStateInterval/{imei}")
	public GenericResponse setStateInterval(String imei, Integer stateType, Integer interval){
		try {
			StateTypeEnum stateTypeEnum = StateTypeEnum.getObj(stateType);
			deviceStatusService.setStateIntervalCommand(imei, stateTypeEnum, interval);
		} catch (Exception e) {
			throw new BasicException(1000, e);
		}
		return ResponseFormat.retParam(200, "设置设备成功");
	}
	
	/**
	 * 定位指令
	 * @return
	 */
	@GetMapping(value = "/getPosition/{imei}")
	public GenericResponse getPosition(@PathVariable String imei){
		try {
			positionInfoService.getPositionCommand(imei);
		} catch (Exception e) {
			throw new BasicException(1000, e);
		}
		return ResponseFormat.retParam(200, "定位成功");
	}
	

}
