package com.lettuce.air.pojo.req;

public class CommandReq {

	/**
	 * 设备ID
	 */
	private String deviceId;
	
	/**
	 * 命令ID
	 */
	private String commandId;
	
	/**
	 * 命令状态信息
	 */
	private CommandStatusReq result;
	
	@Override
	public String toString() {
		return "CommandReq [deviceId=" + deviceId + ", commandId=" + commandId + ", result=" + result.toString() + "]";
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getCommandId() {
		return commandId;
	}

	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}

	public CommandStatusReq getResult() {
		return result;
	}

	public void setResult(CommandStatusReq result) {
		this.result = result;
	}
	
}
