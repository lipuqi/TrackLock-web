package com.lettuce.air.pojo.req;

public class DeviceDataReq {
	
	/**
	 * 通知类型，取值：deviceDataChanged。
	 */
	private String notifyType;
	
	/**
	 * 设备ID
	 */
	private String deviceId;
	
	/**
	 * 设备的服务数据
	 */
	private DeviceServiceDataReq service;
	
	@Override
	public String toString() {
		return "DeviceDataReq [notifyType=" + notifyType + ", deviceId=" + deviceId + ", service=" + service.toString() + "]";
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public DeviceServiceDataReq getService() {
		return service;
	}

	public void setService(DeviceServiceDataReq service) {
		this.service = service;
	}
	
}
