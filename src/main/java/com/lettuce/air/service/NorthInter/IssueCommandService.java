package com.lettuce.air.service.NorthInter;

import com.lettuce.air.pojo.entity.task.DeviceTask;
import com.lettuce.air.pojo.req.CommandReq;

public interface IssueCommandService {
	
	/**
	 * 下发命令
	 * @param method
	 * @param value
	 * @throws Exception
	 */
	public void sendCommand(DeviceTask deviceTask)  throws Exception;
	
	/**
	 * 获取命令状态
	 * @param result
	 */
	public void getCommandStatus(CommandReq commandReq);

}
