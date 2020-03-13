package com.lettuce.air.pojo.entity.device;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.lettuce.air.pojo.enmus.lockStateEnum;

import net.sf.json.JSONObject;

/**
 * 轨迹锁开锁记录
 * @author Lando_Li
 *
 */
@TableName(value = "device_unlock_info")
public class DeviceUnlockInfo extends Model<DeviceUnlockInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1351685454738592688L;

	private Long id;
	
    /**
     * 轨迹锁IMEI
     */
    private String imei;
    
    /**
     *锁状态(1:锁；0：开)
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private lockStateEnum lockState;
	
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 定位编号
     */
    private String positionCode;

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

	public lockStateEnum getLockState() {
		return lockState;
	}

	public void setLockState(lockStateEnum lockState) {
		this.lockState = lockState;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	
	public static DeviceUnlockInfo parseJSON(JSONObject obj) {
		DeviceUnlockInfo deviceUnlockInfo = null;
		if(obj.containsKey("lockState")) {
			deviceUnlockInfo = new DeviceUnlockInfo();
			deviceUnlockInfo.setLockState(lockStateEnum.getObj(obj.getInt("lockState")));
			if(obj.containsKey("code")) {
				deviceUnlockInfo.setPositionCode(obj.getString("code"));
			}
		}
		return deviceUnlockInfo;
	}
    
}
