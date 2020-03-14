package com.lettuce.air.service.task.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lettuce.air.mapper.task.DeviceTaskMapper;
import com.lettuce.air.pojo.entity.task.DeviceTask;
import com.lettuce.air.service.task.DeviceTaskService;

@Service
public class DeviceTaskServiceImpl extends ServiceImpl<DeviceTaskMapper, DeviceTask> implements DeviceTaskService {

	@Override
	public DeviceTask getDeviceTask(String commandId) {
		return baseMapper.selectOne(new QueryWrapper<DeviceTask>().eq("command_id", commandId));
	}
	
	
	@Override
	public void addDeviceTask(DeviceTask deviceTask) {
		baseMapper.insert(deviceTask);
	}


	@Override
	public void updateDeviceTask(DeviceTask deviceTask) {
		updateById(deviceTask);
	}

	@Override
	public List<DeviceTask> seleceTaskList(String imei) {
		return baseMapper.selectList(new QueryWrapper<DeviceTask>().eq("imei", imei).orderByDesc("create_time"));
	}

}
