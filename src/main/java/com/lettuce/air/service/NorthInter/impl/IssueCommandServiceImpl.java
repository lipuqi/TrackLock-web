package com.lettuce.air.service.NorthInter.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lettuce.air.common.api.HuaweiIotApiUrl;
import com.lettuce.air.common.base.HuaweiIotProperties;
import com.lettuce.air.pojo.enmus.TackStateEnum;
import com.lettuce.air.pojo.entity.task.DeviceTask;
import com.lettuce.air.pojo.req.CommandReq;
import com.lettuce.air.service.NorthInter.IssueCommandService;
import com.lettuce.air.service.task.DeviceTaskService;
import com.lettuce.air.utils.TokenUtil;

import net.sf.json.JSONObject;

@Service
public class IssueCommandServiceImpl implements IssueCommandService {
	
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
		String result = huaweiIotApiUrl.getDeviceCommandsUrl(huaweiIotProperties.getAppID(), tokenUtil.getToken(),
				deviceTask.unpack());
		JSONObject resultJson = JSONObject.fromObject(result);
		deviceTask.parseJSON(resultJson);
		deviceTaskService.addDeviceTask(deviceTask);
	}

	@Override
	public void getCommandStatus(CommandReq commandReq) {
		DeviceTask deviceTask = deviceTaskService.getDeviceTask(commandReq.getCommandId());
		
		TackStateEnum tackState = TackStateEnum.getObj(commandReq.getResult().getResultCode());
		deviceTask.setTaskStatus(tackState);
		deviceTask.setResultDetail(commandReq.getResult().getResultDetail());
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
