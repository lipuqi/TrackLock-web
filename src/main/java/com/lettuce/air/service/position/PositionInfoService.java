package com.lettuce.air.service.position;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lettuce.air.pojo.entity.position.PositionInfo;
import com.lettuce.air.pojo.req.DeviceDataReq;

public interface PositionInfoService extends IService<PositionInfo> {
	
	
	/**
	 * 添加定位信息
	 */
	void addPositionInfo(DeviceDataReq deviceDataReq) throws Exception;
	
	/**
	 * 定位指令
	 */
	void getPositionCommand(String imei) throws Exception;
	
	/**
	 * 根据设备获取定位列表
	 * @param imei
	 * @return
	 * @throws Exception
	 */
	List<PositionInfo> selecePositionListByImei(String imei) throws Exception;
	
	/**
	 * 根据设备获取定位列表(百度)
	 * @param imei
	 * @return
	 * @throws Exception
	 */
	List<PositionInfo> selecePositionListByImeiBD(String imei) throws Exception;
	
}
