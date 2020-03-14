package com.lettuce.air.service.device.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lettuce.air.common.constant.ServiceConstant;
import com.lettuce.air.mapper.device.DeviceStatusMapper;
import com.lettuce.air.pojo.enmus.StateTypeEnum;
import com.lettuce.air.pojo.entity.device.DeviceStatus;
import com.lettuce.air.pojo.entity.task.DeviceTask;
import com.lettuce.air.pojo.req.DeviceDataReq;
import com.lettuce.air.service.NorthInter.IssueCommandService;
import com.lettuce.air.service.device.DeviceStatusService;
import com.lettuce.air.utils.DateUtil;

import net.sf.json.JSONObject;

@Service
public class DeviceStatusServiceImpl extends ServiceImpl<DeviceStatusMapper, DeviceStatus> implements DeviceStatusService {
	
	@Autowired
	private IssueCommandService issueCommandService;
	
	
	@Override
	public DeviceStatus getDeviceStatusByDeviceId(String deviceId) {
		return baseMapper.selectOne(new QueryWrapper<DeviceStatus>().eq("device_id", deviceId));
	}
	
	@Override
	public DeviceStatus getDeviceStatusByImei(String imei) {
		return baseMapper.selectOne(new QueryWrapper<DeviceStatus>().eq("imei", imei));
	}
	
	@Override
	public void saveDeviceStatus(DeviceDataReq deviceDataReq) throws ParseException {
		if(deviceDataReq.getService().getData().containsKey("heartbeat")) {
			return;
		}
		DeviceStatus deviceStatus = getDeviceStatusByDeviceId(deviceDataReq.getDeviceId());
		
		DeviceStatus data = DeviceStatus.parseJSON(deviceDataReq.getService().getData());
		Date eventTime = DateUtil.parseDate(deviceDataReq.getService().getEventTime());
		if(deviceStatus != null) {
			deviceStatus.setBattery(data.getBattery());
			deviceStatus.setBatteryTime(eventTime);
			deviceStatus.setStateInterval(data.getStateInterval());
			deviceStatus.setPositionInterval(data.getPositionInterval());
			updateById(deviceStatus);
		} else {
			data.setBatteryTime(eventTime);
			baseMapper.insert(data);
		}
	}
	

	@Override
	public void updateDeviceStatus(DeviceStatus deviceStatus) {
		updateById(deviceStatus);
	}


	@Override
	public void getDeviceStateCommand(String imei) throws Exception {
		DeviceStatus deviceStatus = getDeviceStatusByImei(imei);
		
		DeviceTask deviceTask = new DeviceTask();
		deviceTask.setImei(imei);
		deviceTask.setDeviceId(deviceStatus.getDeviceId());
		deviceTask.setServiceId(ServiceConstant.GlobalState);
		deviceTask.setMethod(ServiceConstant.GET_DEVICE_STATE);
		
		JSONObject data = new JSONObject();
		data.put("reserve", 1);
		deviceTask.setData(data.toString());
		
		issueCommandService.sendCommand(deviceTask);
	}

	@Override
	public void setStateIntervalCommand(String imei, StateTypeEnum stateType, Integer interval) throws Exception {
		DeviceStatus deviceStatus = getDeviceStatusByImei(imei);
		
		DeviceTask deviceTask = new DeviceTask();
		deviceTask.setImei(imei);
		deviceTask.setDeviceId(deviceStatus.getDeviceId());
		deviceTask.setServiceId(ServiceConstant.GlobalState);
		deviceTask.setMethod(ServiceConstant.SET_STATE_INTERVAL);
		
		JSONObject data = new JSONObject();
		data.put("stateType", stateType.getVal());
		data.put("interval", interval);
		deviceTask.setData(data.toString());
		
		issueCommandService.sendCommand(deviceTask);
	}

	@Override
	public List<DeviceStatus> seleceDeviceList() throws Exception {
		return baseMapper.selectList(new QueryWrapper<DeviceStatus>());
	}

	
}
