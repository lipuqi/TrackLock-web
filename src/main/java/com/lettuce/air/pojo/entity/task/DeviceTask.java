package com.lettuce.air.pojo.entity.task;

import java.text.ParseException;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.lettuce.air.pojo.enmus.TackStateEnum;
import com.lettuce.air.utils.DateUtil;

import net.sf.json.JSONObject;

/**
 * 轨迹锁任务
 * @author Lando_Li
 *
 */
@TableName(value = "device_task")
public class DeviceTask extends Model<DeviceTask>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -53975772209242367L;
	
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
     * 服务ID
     */
    private String serviceId;
    
    /**
     * 命令ID
     */
    private String commandId;
    
    /**
     * 指令
     */
    private String method;
    
    /**
     * 设置值
     */
    private String data;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     *下达时间
     */
    private Date assignTime;
    
    /**
     *执行时间
     */
    private Date executeTime;
    
    /**
     *执行成功时间
     */
    private Date successTime;
    
    /**
     * 任务状态
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private TackStateEnum taskStatus;
    
	/**
	 * 命令结果详细信息
	 */
	private String resultDetail;

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

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Date getAssignTime() {
		return assignTime;
	}

	public void setAssignTime(Date assignTime) {
		this.assignTime = assignTime;
	}

	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	public TackStateEnum getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TackStateEnum taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	public String getCommandId() {
		return commandId;
	}

	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}
	
	public String getResultDetail() {
		return resultDetail;
	}

	public void setResultDetail(String resultDetail) {
		this.resultDetail = resultDetail;
	}

	public JSONObject unpack() {
		JSONObject commandData = new JSONObject();
		commandData.put("deviceId", this.getDeviceId());
		
		commandData.put("expireTime", 0);
		commandData.put("maxRetransmit", 3);
		
		JSONObject command = new JSONObject();
		command.put("serviceId", this.getServiceId());
		command.put("method", this.getMethod());	
		
		JSONObject paras = JSONObject.fromObject(this.getData());
		command.put("paras", paras);
		
		commandData.put("command", command);
		
		return commandData;
	}
	
	public void parseJSON(JSONObject obj) throws ParseException {
		if(obj.containsKey("commandId"))
			this.setCommandId(obj.getString("commandId"));
		if(obj.containsKey("status")) {}
			this.setTaskStatus(TackStateEnum.getObj(obj.getString("status")));
		if(obj.containsKey("creationTime"))
			this.setCreateTime(DateUtil.parseDate(obj.getString("creationTime")));
	}
}
