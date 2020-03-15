package com.lettuce.air.pojo.entity.position;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.lettuce.air.common.exception.CustomException;
import com.lettuce.air.pojo.enmus.PositionTypeEnum;
import com.lettuce.air.utils.PositionUtil;

import net.sf.json.JSONObject;

/**
 * 轨迹锁位置
 * @author Lando_Li
 *
 */
@TableName(value = "position_info")
public class PositionInfo extends Model<PositionInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6635990465806696705L;
	
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
     * GPS经度
     */
    private String longitude;

    /**
     * GPS纬度
     */
    private String latitude;

    /**
     *定位类型
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private PositionTypeEnum positionType;
    
    /**
     * 定位编号
     */
    private String positionCode;
    
    /**
     * 记录时间
     */
    private Date recordTime;
    
    /**
     * 创建时间
     */
    private Date createTime;

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

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public PositionTypeEnum getPositionType() {
		return positionType;
	}

	public void setPositionType(PositionTypeEnum positionType) {
		this.positionType = positionType;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static PositionInfo parseJSON(JSONObject obj) {
		PositionInfo positionInfo = null;
		try {
			if(obj.containsKey("longitude") && obj.containsKey("latitude")) {
				positionInfo = new PositionInfo();
				String latitude = obj.getString("latitude");
				String longitude = obj.getString("longitude");
				String[] latlon = PositionUtil.parseLonlan(latitude, longitude);
				
				positionInfo.setLatitude(latlon[0]);
				positionInfo.setLongitude(latlon[1]);
				if(obj.containsKey("positionDate")) {
					positionInfo.setRecordTime(PositionUtil.parseUTCtime(obj.getString("positionDate")));
				}
				if(obj.containsKey("positionType")) {
					PositionTypeEnum positionType = PositionTypeEnum.getObj(obj.getInt("positionType"));
					positionInfo.setPositionType(positionType);
				}
				if(obj.containsKey("code")) {
					String code = obj.getString("code");
					if(!"0000".equals(code)) {
						positionInfo.setPositionCode(code);
					}
				}
			}
		} catch (Exception e) {
			throw new CustomException(PositionInfo.class, "解析数据出现异常", e);
		}
		return positionInfo;
	}
    
}
