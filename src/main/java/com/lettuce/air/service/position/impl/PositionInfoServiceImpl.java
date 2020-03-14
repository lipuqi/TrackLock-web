package com.lettuce.air.service.position.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lettuce.air.common.api.YingyanApiUrl;
import com.lettuce.air.common.constant.ServiceConstant;
import com.lettuce.air.mapper.position.PositionInfoMapper;
import com.lettuce.air.pojo.entity.device.DeviceStatus;
import com.lettuce.air.pojo.entity.position.PositionInfo;
import com.lettuce.air.pojo.entity.task.DeviceTask;
import com.lettuce.air.pojo.req.DeviceDataReq;
import com.lettuce.air.service.NorthInter.IssueCommandService;
import com.lettuce.air.service.device.DeviceStatusService;
import com.lettuce.air.service.position.PositionInfoService;
import com.lettuce.air.utils.DateUtil;

import net.sf.json.JSONObject;

@Service
public class PositionInfoServiceImpl extends ServiceImpl<PositionInfoMapper, PositionInfo>
		implements PositionInfoService {
	
	private static final Logger logger = LoggerFactory.getLogger(PositionInfoServiceImpl.class);

	@Autowired
	private DeviceStatusService deviceStatusService;

	@Autowired
	private IssueCommandService issueCommandService;

	@Autowired
	private YingyanApiUrl yingyanApiUrl;

	@Override
	public void addPositionInfo(DeviceDataReq deviceDataReq) throws Exception {
		DeviceStatus deviceStatus = deviceStatusService.getDeviceStatusByDeviceId(deviceDataReq.getDeviceId());

		PositionInfo positionInfo = PositionInfo.parseJSON(deviceDataReq.getService().getData());
		Date eventTime = DateUtil.parseDate(deviceDataReq.getService().getEventTime());
		positionInfo.setDeviceId(deviceStatus.getDeviceId());
		positionInfo.setImei(deviceStatus.getImei());
		positionInfo.setCreateTime(eventTime);
		baseMapper.insert(positionInfo);

		String res = yingyanApiUrl.addpoint(positionInfo.getImei(), positionInfo.getLatitude(), positionInfo.getLongitude(),
				positionInfo.getRecordTime().getTime(), positionInfo.getPositionType().getDesc());
		
		logger.debug(">>>>>>>>>>>>>>>>>>yingyan addpoint<<<<<<<<<<<<<<<<<<<<<" + res);
	}

	@Override
	public void getPositionCommand(String imei) throws Exception {
		DeviceStatus deviceStatus = deviceStatusService.getDeviceStatusByImei(imei);

		DeviceTask deviceTask = new DeviceTask();
		deviceTask.setImei(imei);
		deviceTask.setDeviceId(deviceStatus.getDeviceId());
		deviceTask.setServiceId(ServiceConstant.Positioning);
		deviceTask.setMethod(ServiceConstant.GET_POSITION);

		JSONObject data = new JSONObject();
		data.put("reserve", 1);
		deviceTask.setData(data.toString());

		issueCommandService.sendCommand(deviceTask);
	}

	@Override
	public List<PositionInfo> selecePositionListByImei(String imei) throws Exception {
		return baseMapper.selectList(new QueryWrapper<PositionInfo>().eq("imei", imei).orderByDesc("record_time"));
	}

}
