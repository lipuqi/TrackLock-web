package com.lettuce.air.pojo.req;

import net.sf.json.JSONObject;

public class DeviceServiceDataReq {
	
	/**
	 * 服务ID
	 */
	private String serviceId;
	
	/**
	 * 服务的类型
	 */
	private String serviceType;
	
	/**
	 * 服务数据信息
	 */
	private JSONObject data;
	
	/**
	 * 事件发生时间，时间格式 yyyymmddThhmmssZ，例如20151212T121212Z。
	 */
	private String eventTime;
	

	@Override
	public String toString() {
		return "DeviceServiceDataReq [serviceId=" + serviceId + ", serviceType=" + serviceType + ", data=" + data.toString()
				+ ", eventTime=" + eventTime + "]";
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

}
