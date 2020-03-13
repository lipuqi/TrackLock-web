package com.lettuce.air.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lettuce.air.common.exception.BasicException;
import com.lettuce.air.core.controller.GenericResponse;
import com.lettuce.air.core.controller.ResponseFormat;
import com.lettuce.air.service.device.DeviceStatusService;

/**
 * app端对外接口
 * @author Lando
 *
 */
@RestController
@RequestMapping("/app")
public class AppController {
	
	
	
	@Autowired
	private DeviceStatusService deviceStatusService;
	
	/**
	 * 开锁
	 * @return
	 */
	@GetMapping(value = "/unlock/{imei}")
	public GenericResponse unlock(@PathVariable String imei){
		try {
			deviceStatusService.unLockCommand(imei);
		} catch (Exception e) {
			throw new BasicException(1000, e);
		}
		return ResponseFormat.retParam(200, null);
	}
	

}
