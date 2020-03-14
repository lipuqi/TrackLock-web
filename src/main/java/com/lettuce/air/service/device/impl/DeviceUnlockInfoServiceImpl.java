package com.lettuce.air.service.device.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lettuce.air.common.constant.ServiceConstant;
import com.lettuce.air.mapper.device.DeviceUnlockInfoMapper;
import com.lettuce.air.pojo.entity.device.DeviceStatus;
import com.lettuce.air.pojo.entity.device.DeviceUnlockInfo;
import com.lettuce.air.pojo.entity.task.DeviceTask;
import com.lettuce.air.pojo.req.DeviceDataReq;
import com.lettuce.air.service.NorthInter.IssueCommandService;
import com.lettuce.air.service.device.DeviceStatusService;
import com.lettuce.air.service.device.DeviceUnlockInfoService;
import com.lettuce.air.utils.DateUtil;

import net.sf.json.JSONObject;

@Service
public class DeviceUnlockInfoServiceImpl extends ServiceImpl<DeviceUnlockInfoMapper, DeviceUnlockInfo> implements DeviceUnlockInfoService {

	@Autowired
	private DeviceStatusService deviceStatusService;
	
	@Autowired
	private IssueCommandService issueCommandService;
	
	@Override
	public void saveLockStatus(DeviceDataReq deviceDataReq) throws ParseException {
		DeviceStatus deviceStatus = deviceStatusService.getDeviceStatusByDeviceId(deviceDataReq.getDeviceId());
		
		DeviceUnlockInfo deviceUnlockInfo = DeviceUnlockInfo.parseJSON(deviceDataReq.getService().getData());
		Date eventTime = DateUtil.parseDate(deviceDataReq.getService().getEventTime());
		deviceUnlockInfo.setCreateTime(eventTime);
		deviceUnlockInfo.setImei(deviceStatus.getImei());
		baseMapper.insert(deviceUnlockInfo);
		
		deviceStatus.setLockState(deviceUnlockInfo.getLockState());
		deviceStatus.setLockTime(eventTime);
		deviceStatusService.updateDeviceStatus(deviceStatus);
	}

	@Override
	public void unLockCommand(String imei) throws Exception {
		DeviceStatus deviceStatus = deviceStatusService.getDeviceStatusByImei(imei);
		
		DeviceTask deviceTask = new DeviceTask();
		deviceTask.setImei(imei);
		deviceTask.setDeviceId(deviceStatus.getDeviceId());
		deviceTask.setServiceId(ServiceConstant.Unlock);
		deviceTask.setMethod(ServiceConstant.UN_LOCK);
		
		JSONObject data = new JSONObject();
		data.put("reserve", 1);
		deviceTask.setData(data.toString());
		
		issueCommandService.sendCommand(deviceTask);
	}

	@Override
	public List<DeviceUnlockInfo> seleceUnlockInfoList(String imei) {
		return baseMapper.selectList(new QueryWrapper<DeviceUnlockInfo>().eq("imei", imei).orderByDesc("create_time"));
	}
}
