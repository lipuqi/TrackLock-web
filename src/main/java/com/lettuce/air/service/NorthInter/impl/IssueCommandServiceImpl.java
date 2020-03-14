package com.lettuce.air.service.NorthInter.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lettuce.air.common.api.HuaweiIotApiUrl;
import com.lettuce.air.common.base.HuaweiIotProperties;
import com.lettuce.air.pojo.enmus.TackStateEnum;
import com.lettuce.air.pojo.entity.task.DeviceTask;
import com.lettuce.air.pojo.req.CommandReq;
import com.lettuce.air.service.NorthInter.IssueCommandService;
import com.lettuce.air.service.task.DeviceTaskService;
import com.lettuce.air.utils.TokenUtil;


@Service
public class IssueCommandServiceImpl implements IssueCommandService {
	
	private static final Logger logger = LoggerFactory.getLogger(IssueCommandServiceImpl.class);
	
	@Autowired
	private DeviceTaskService deviceTaskService;
	
	@Autowired
	private HuaweiIotProperties huaweiIotProperties;

	@Autowired
	private HuaweiIotApiUrl huaweiIotApiUrl;
	
	@Autowired
	private TokenUtil tokenUtil;

	@Override
	public void sendCommand(DeviceTask deviceTask) throws Exception{
		net.sf.json.JSONObject paramJson = deviceTask.unpack();
		paramJson.put("callbackUrl", huaweiIotProperties.getCommandCallbackUrl());
		String result = huaweiIotApiUrl.getDeviceCommandsUrl(huaweiIotProperties.getAppID(), tokenUtil.getToken(),
				paramJson);
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sendCommand result<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<," + result);
		net.sf.json.JSONObject resultJson = net.sf.json.JSONObject.fromObject(result);
		deviceTask.parseJSON(resultJson);
		deviceTaskService.addDeviceTask(deviceTask);
	}

	@Override
	public void getCommandStatus(CommandReq commandReq) {
		DeviceTask deviceTask = deviceTaskService.getDeviceTask(commandReq.getCommandId());
		
		TackStateEnum tackState = TackStateEnum.getObj(commandReq.getResult().getResultCode());
		deviceTask.setTaskStatus(tackState);
		
		JSONObject ResultDetail = commandReq.getResult().getResultDetail();
		if(ResultDetail != null) {
			deviceTask.setResultDetail(ResultDetail.toString());
		}
		
		Date nowDate = new Date();
		switch (tackState) {
		case DELIVERED:
			deviceTask.setAssignTime(nowDate);
			break;
		case FAILED:
		case TIMEOUT:
			deviceTask.setExecuteTime(nowDate);
			break;
		case SUCCESSFUL:
			deviceTask.setExecuteTime(nowDate);
			deviceTask.setSuccessTime(nowDate);
			break;
		default:
			break;
		}
		deviceTaskService.updateDeviceTask(deviceTask);
	}
}
