package com.lettuce.air.common.base;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix="huawei-iot.config")
public class HuaweiIotProperties {
	
	//appId
	private String appID;
	
	//app密钥
	private String appSecret;
	
	//命令过期时间
	private Long commandExecuteTime;
	
	//命令下发回调地址
	private String commandCallbackUrl;
	
	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public Long getCommandExecuteTime() {
		return commandExecuteTime;
	}

	public void setCommandExecuteTime(Long commandExecuteTime) {
		this.commandExecuteTime = commandExecuteTime;
	}

	public String getCommandCallbackUrl() {
		return commandCallbackUrl;
	}

	public void setCommandCallbackUrl(String commandCallbackUrl) {
		this.commandCallbackUrl = commandCallbackUrl;
	}
	
}
