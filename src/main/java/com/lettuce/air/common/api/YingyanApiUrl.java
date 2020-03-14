package com.lettuce.air.common.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties(prefix = "yingyan.api")
public class YingyanApiUrl {

	// 添加轨迹
	private String addpointUrl;

	// 用户的AK
	private String ak;

	// service 的唯一标识
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
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addpoint(String imei, String latitude, String longitude, Long locTime, String positionType)
			throws Exception {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> param = new LinkedMultiValueMap<>();

		param.add("ak", ak);
		param.add("service_id", serviceId);
		param.add("entity_name", imei);
		param.add("latitude", latitude);
		param.add("longitude", longitude);
		param.add("loc_time", locTime/1000 + "");
		param.add("coord_type_input", "wgs84");
		param.add("position_type", positionType);

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(param,
				headers);
		String result = restTemplate.postForObject(addpointUrl, entity, String.class);

		return result;
	}

}
