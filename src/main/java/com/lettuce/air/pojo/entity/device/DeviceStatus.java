package com.lettuce.air.pojo.entity.device;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.lettuce.air.pojo.enmus.lockStateEnum;

import net.sf.json.JSONObject;

/**
 * 轨迹锁状态表
 * @author Lando_Li
 *
 */
@TableName(value = "device_status")
public class DeviceStatus extends Model<DeviceStatus>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7143485739612382301L;
	
	private Long id;
	
    /**
     * 轨迹锁IMEI
     */
    private String imei;
    
    /**
     * 轨迹锁编号
     */
    private String deviceId;
    
    /**
     * 电量
     */
    private Integer battery;

    /**
     * 电量更新时间
     */
    private Date batteryTime;
    
    /**
     *锁状态(1:锁；0：开)
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private lockStateEnum lockState;
    
    /**
     *锁状态更新时间
     */
    private Date lockTime;
    
    /**
     * 状态包上传时间间隔
     */
    private Integer stateInterval;
    
    /**
     * 定位上传间隔 分钟
     */
    private Integer positionInterval;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getBattery() {
		return battery;
	}

	public void setBattery(Integer battery) {
		this.battery = battery;
	}

	public Date getBatteryTime() {
		return batteryTime;
	}

	public void setBatteryTime(Date batteryTime) {
		this.batteryTime = batteryTime;
	}

	public lockStateEnum getLockState() {
		return lockState;
	}

	public void setLockState(lockStateEnum lockState) {
		this.lockState = lockState;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public Integer getStateInterval() {
		return stateInterval;
	}

	public void setStateInterval(Integer stateInterval) {
		this.stateInterval = stateInterval;
	}

	public Integer getPositionInterval() {
		return positionInterval;
	}

	public void setPositionInterval(Integer positionInterval) {
		this.positionInterval = positionInterval;
	}

	public static DeviceStatus parseJSON(JSONObject obj) {
		DeviceStatus deviceStatus = null;
		
		if(obj.containsKey("battery")) {
			deviceStatus = new DeviceStatus();
			deviceStatus.setBattery(obj.getInt("battery"));
			if(obj.containsKey("stateInterval")) {
				deviceStatus.setStateInterval(obj.getInt("stateInterval"));
			}
			if(obj.containsKey("positionInterval")) {
				deviceStatus.setPositionInterval(obj.getInt("positionInterval"));
			}
		}
		return deviceStatus;
	}
}
