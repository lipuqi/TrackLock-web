package com.lettuce.air.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lettuce.air.common.exception.BasicException;
import com.lettuce.air.common.exception.CustomException;
import com.lettuce.air.core.controller.GenericResponse;
import com.lettuce.air.core.controller.ResponseFormat;
import com.lettuce.air.pojo.req.CommandReq;
import com.lettuce.air.pojo.req.DeviceDataReq;
import com.lettuce.air.service.NorthInter.IssueCommandService;
import com.lettuce.air.service.NorthInter.ReceiveDataService;


/**
 * 设备端对接接口
 * @author Lando
 *
 */
@RestController
@RequestMapping("/device")
public class DeviceController {
	
	private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);
		
	@Autowired
	private ReceiveDataService receiveDataService;
	
	@Autowired
	private IssueCommandService issueCommandService;

	
	/**
	 * 上报数据
	 * @param result
	 * @return
	 */
	@PostMapping(value = "/deviceDataChanged", produces = { "application/json;charset=UTF-8" })
	public GenericResponse deviceDataChanged(@RequestBody DeviceDataReq deviceDataReq){
		
		logger.debug(">>>>>>>>>>>>deviceDataChanged<<<<<<<<<<<<<<<" + deviceDataReq.toString());
		try {
			receiveDataService.getDeviceData(deviceDataReq);
		} catch (CustomException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BasicException(1000, e);
		}
		return ResponseFormat.retParam(200, "OK");
	}
	
	/**
	 * 返回命令下发状态
	 * @param result
	 * @return
	 */
	@PostMapping(value = "/commandStatus", produces = { "application/json;charset=UTF-8" })
	public GenericResponse commandStatus(@RequestBody CommandReq commandReq){
		
		logger.debug(">>>>>>>>>>>>commandStatus<<<<<<<<<<<<<<<" + commandReq.toString());
		try {
			issueCommandService.getCommandStatus(commandReq);
		} catch (CustomException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BasicException(1000, e);
		}
		return ResponseFormat.retParam(200, "OK");
	}

}
