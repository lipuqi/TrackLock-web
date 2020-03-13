package com.lettuce.air.common.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.lettuce.air.utils.https.HttpsUtil;
import com.lettuce.air.utils.https.StreamClosedHttpResponse;

@Component
@ConfigurationProperties(prefix="yingyan.api")
public class YingyanApiUrl {
	
	//添加轨迹
	private String addpointUrl;
	
	//用户的AK
	private String ak;
	
	//service 的唯一标识
	private String serviceId;
	
	/**
	 * @return the addpointUrl
	 */
	public String getAddpointUrl() {
		return addpointUrl;
	}
	/**
	 * @param addpointUrl the addpointUrl to set
	 */
	public void setAddpointUrl(String addpointUrl) {
		this.addpointUrl = addpointUrl;
	}
	/**
	 * @return the ak
	 */
	public String getAk() {
		return ak;
	}
	/**
	 * @param ak the ak to set
	 */
	public void setAk(String ak) {
		this.ak = ak;
	}
	/**
	 * @return the serviceId
	 */
	public String getServiceId() {
		return serviceId;
	}
	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * 上传轨迹
	 * @return
	 * @throws Exception
	 */
	public String addpoint(String imei, String latitude, String longitude, Long locTime, String positionType) throws Exception {
		HttpsUtil httpsUtil = new HttpsUtil();
		
		Map<String, String> paramLogin = new HashMap<>();
        paramLogin.put("ak", ak);
        paramLogin.put("service_id", serviceId);
        paramLogin.put("entity_name", imei);
        paramLogin.put("latitude", latitude);
        paramLogin.put("longitude", longitude);
        paramLogin.put("loc_time", locTime + "");
        paramLogin.put("coord_type_input", "wgs84");
        paramLogin.put("position_type", positionType);

        StreamClosedHttpResponse response = httpsUtil.doPostFormUrlEncodedGetStatusLine(addpointUrl, paramLogin);
        return response.getContent();
	}

}
